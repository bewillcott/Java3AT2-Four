/*
 *  File Name:    RootLayoutController.java
 *  Project Name: GUIApp
 *
 *  Copyright (c) 2021 Bradley Willcott
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ****************************************************************
 * Name: Bradley Willcott
 * ID:   M198449
 * Date: 1 Oct 2021
 * ****************************************************************
 */

package com.bewsoftware.tafe.java3.at2.four.gui.view;

import com.bewsoftware.tafe.java3.at2.four.gui.App;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.bewsoftware.tafe.java3.at2.four.gui.Views.BLANK;
import static com.bewsoftware.tafe.java3.at2.four.gui.Views.CHAT;
import static com.bewsoftware.tafe.java3.at2.four.gui.Views.LOGIN;
import static com.bewsoftware.tafe.java3.at2.four.gui.Views.NEW_ACCOUNT;

/**
 * FXML Controller class for the 'RootLayout.fxml
 * import static com.bewsoftware.tafe.java3.at2.four.gui.Views.LOGIN;
 * ' file.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class RootLayoutController
{
    @FXML
    private MenuItem aboutMenuItem;

    private App app;

    @FXML
    private MenuItem chatCloseMenuItem;

    @FXML
    private MenuItem chatOpenMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem loginMenuItem;

    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    private MenuItem newAccountMenuItem;

    @FXML
    private Label statusLabel;

    @FXML
    private Image statusImage;

    /**
     * Instantiate a new copy of RootLayoutController class.
     */
    public RootLayoutController()
    {
        // Currently: NoOp.
    }

    /**
     * Store a reference to the App instance.
     *
     * @param app
     */
    public void setApp(App app)
    {
        this.app = app;
    }

    /**
     * Opens the About dialog.
     */
    public void showAboutDialog()
    {
        try
        {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/About.fxml"));
            GridPane page = (GridPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("About Java3 AT2 Four");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(app.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Handle the Help/About menu item event.
     *
     * @param event
     */
    @FXML
    private void handleAboutMenuItem(ActionEvent event)
    {
        showAboutDialog();
    }

    /**
     * Handle the Chat/Close menu item event.
     *
     * @param event
     */
    @FXML
    private void handleChatCloseMenuItem(ActionEvent event)
    {
        app.showView(BLANK);
    }

    /**
     * Handle the Chat/Open menu item event.
     *
     * @param event
     */
    @FXML
    private void handleChatOpenMenuItem(ActionEvent event)
    {
        app.showView(CHAT);
    }

    /**
     * Handle the File/Close menu item event.
     *
     * @param event
     */
    @FXML
    private void handleCloseMenuItem(ActionEvent event)
    {
        Platform.exit();
    }

    /**
     * Handle the User/Login menu item event.
     *
     * @param event
     */
    @FXML
    private void handleLoginMenuItem(ActionEvent event)
    {
        app.showView(LOGIN);
    }

    /**
     * Handle the User/Logout menu item event.
     *
     * @param event
     */
    @FXML
    private void handleLogoutMenuItem(ActionEvent event)
    {
        app.showView(BLANK);
    }

    /**
     * Handle the User/New Account menu item event.
     *
     * @param event
     */
    @FXML
    private void handleNewAccountMenuItem(ActionEvent event)
    {
        app.showView(NEW_ACCOUNT);
    }

    @FXML
    private void initialize()
    {
        closeMenuItem.setOnAction(this::handleCloseMenuItem);
        aboutMenuItem.setOnAction(this::handleAboutMenuItem);
    }

}
