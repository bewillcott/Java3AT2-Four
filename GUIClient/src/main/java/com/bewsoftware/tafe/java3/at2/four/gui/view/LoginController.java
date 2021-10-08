/*
 *  File Name:    LoginController.java
 *  Project Name: GUIClient
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

package com.bewsoftware.tafe.java3.at2.four.gui.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class for the 'Login.fxml' file.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class LoginController
{
    @FXML
    private Label errorMessageLabel;

    @FXML
    private Button resetButton;

    @FXML
    private Button submitButton;

    @FXML
    private PasswordField thePasswordField;

    @FXML
    private TextField usernameTextField;

    /**
     * Instantiate a new copy of NewAccountController class.
     */
    public LoginController()
    {
        // NoOp
    }

    /**
     * Handle the Reset Button event.
     *
     * @param event
     */
    @FXML
    private void handleResetButton(ActionEvent event)
    {
        // todo
    }

    /**
     * Handle the Submit Button event.
     *
     * @param event
     */
    @FXML
    private void handleSubmitButton(ActionEvent event)
    {
        // todo
    }

}
