/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bewsoftware.tafe.java3.at2.four.server.rmi;

import com.bewsoftware.tafe.java3.at2.four.common.PBKDF2.CannotPerformOperationException;
import com.bewsoftware.tafe.java3.at2.four.common.PBKDF2.InvalidHashException;
import com.bewsoftware.tafe.java3.at2.four.common.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The RMI Server class.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class Server implements UserAccount
{

    /**
     * File to persist user account details.
     */
    private static final String DATASTORE = "user_accounts.csv";

    private static Registry registry;

    /**
     * CSV file header text.
     */
    private static final String[] HEADER =
    {
        "Username", "PasswordHash"
    };

    private static final long serialVersionUID = 7163705544013025860L;

    /**
     * @param args the command line arguments
     *
     * @throws java.io.IOException if any
     */
    public static void main(String[] args) throws IOException
    {
        try
        {
            String name = "UserAccounts";
            UserAccount engine = new Server();
            UserAccount stub = (UserAccount) UnicastRemoteObject.exportObject(engine, 0);

            registry = LocateRegistry.createRegistry(0);
            registry.rebind(name, stub);
            System.out.println("Server bound");
        } catch (RemoteException e)
        {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }

    /**
     * The data from/for the CSV file.
     */
    private CSVFile userCSVFile;

    /**
     * The sorted list of registered users.
     */
    private AvlTree<User> users;

    /**
     * Instantiate a new copy of the Server class.
     *
     * @throws java.io.IOException if any
     */
    public Server() throws IOException
    {
        users = new AvlTree<>();
        userCSVFile = new CSVFile(DATASTORE);

        if (userCSVFile.readData(true))
        {
            for (CSVRow row : userCSVFile)
            {
                users.add(new User(row.get(0), row.get(1)));
            }
        } else
        {
            userCSVFile.setHeader(HEADER);
        }
    }

    @Override
    public boolean create(String username, String password) throws RemoteException
    {
        boolean rtn = false;
        User user = new User(username, null);

        if (!users.contains(user))
        {
            try
            {
                user.passwordHash = PBKDF2.createHash(password);
                users.add(user);

                if (userCSVFile.add(CSVRow.parse(user.username, user.passwordHash)))
                {
                    userCSVFile.writeData();
                }

                rtn = true;
            } catch (CannotPerformOperationException | IOException ex)
            {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rtn;
    }

    @Override
    public boolean login(String username, String password) throws RemoteException
    {
        boolean rtn = false;
        User user = new User(username, null);

        if (users.contains(user))
        {
            String pwd = users.get(users.indexOf(user)).passwordHash;

            try
            {
                rtn = PBKDF2.verifyPassword(password, pwd);
            } catch (CannotPerformOperationException | InvalidHashException ex)
            {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rtn;
    }

    /**
     * Holds user account details.
     */
    private static class User implements Comparable<User>
    {
        /**
         * The hashed password.
         */
        public String passwordHash;

        /**
         * The username - key.
         */
        public final String username;

        /**
         * Instantiates a new copy of the User class.
         *
         * @param username     to store
         * @param passwordHash to store
         */
        public User(String username, String passwordHash)
        {
            this.username = username;
            this.passwordHash = passwordHash;
        }

        @Override
        public int compareTo(User o)
        {
            return this.equals(o) ? 0 : this.username.compareTo(o.username);
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }

            if (obj == null)
            {
                return false;
            }

            if (getClass() != obj.getClass())
            {
                return false;
            }

            final User other = (User) obj;
            return Objects.equals(this.username, other.username);
        }

        @Override
        public int hashCode()
        {
            int hash = 7;
            hash = 61 * hash + Objects.hashCode(this.username);
            return hash;
        }
    }
}
