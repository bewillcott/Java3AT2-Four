/*
 *  File Name:    CSVFile.java
 *  Project Name: Common
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
 * Date: 8 Oct 2021
 * ****************************************************************
 */

package com.bewsoftware.tafe.java3.at2.four.common;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;

import static com.bewsoftware.tafe.java3.at2.four.common.CSVRow.parseArray;

/**
 * Reads, writes, and holds CSV data - into and out from an external file.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class CSVFile
{
    private final String fileName;

    private CSVRow header;

    private final LinkedList<CSVRow> rows;

    /**
     * Create a new instance of the CSVFile class with the supplied file name.
     *
     * @param fileName name of CSV file
     */
    public CSVFile(String fileName)
    {
        this.fileName = fileName;
        this.rows = new LinkedList<>();
    }

    /**
     * Get the header text as a CSV String.
     *
     * @return CSV String
     */
    public String getHeader()
    {
        return header != null ? "" + header : "";
    }

    /**
     * Set the header to the contents of the supplied array.
     *
     * @param array new header data
     */
    public void setHeader(String[] array)
    {
        if (Objects.requireNonNull(array).length > 0)
        {
            header = parseArray(array);
        }
    }

    /**
     * Get the CSV rows.
     *
     * @return iterator
     */
    public Iterator<CSVRow> getRows()
    {
        return rows.iterator();
    }

    /**
     * Returns {@code true} if a header exists.
     *
     * @return {@code true} if a header exists
     */
    public boolean hasHeader()
    {
        return header != null;
    }

    public boolean readData(boolean hasHeader)
    {
        boolean rtn = false;

        if (fileName != null && !fileName.isBlank())
        {
            File file = new File(fileName);

            if (file.exists())
            {

                try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
                {
                    String next;

                    while ((next = br.readLine()) != null)
                    {
                        CSVRow csvRow = CSVRow.parseCSV(next);

                        if (hasHeader)
                        {
                            header = csvRow;
                            hasHeader = false;
                        } else
                        {
                            rows.add(csvRow);
                        }
                    }

                    rtn = true;
                } catch (IOException ex)
                {
                    System.out.format("ERROR: Failed to read from CSV file!\n%s\n", ex.getMessage());
                }
            }
        }

        return rtn;
    }

    /**
     * Writes the CSV data.
     *
     * @return {@code true} if successful.
     */
    public boolean writeData()
    {
        boolean rtn = false;

        if (rows.size() > 0 && fileName != null && !fileName.isBlank())
        {
            File file = new File(fileName);

            if (!file.exists())
            {
                try (FileWriter fw = new FileWriter(fileName))
                {
                    fw.write(!(header == null || header.isEmpty()) ? header + "\n" : "");
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }

            try (FileWriter sw = new FileWriter(fileName, true))
            {
                for (CSVRow row : rows)
                {
                    sw.write(row.toString() + "\n");
                }

                rtn = true;
            } catch (IOException ex)
            {
                System.out.format("ERROR: Failed to write to CSV file!\n%s\n", ex.getMessage());
            }
        }

        return rtn;
    }

}
