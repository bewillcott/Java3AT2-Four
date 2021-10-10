/*
 *  File Name:    ChatController.java
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

import com.bewsoftware.tafe.java3.at2.four.gui.App;
import com.bewsoftware.tafe.java3.at2.four.gui.ViewController;
import com.bewsoftware.tafe.java3.at2.four.gui.Views;
import java.beans.PropertyChangeEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static com.bewsoftware.tafe.java3.at2.four.gui.Views.CHAT;

/**
 * ChatController class description.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class ChatController implements ViewController
{
    private App app;

    @FXML
    private TextArea incomingMessagesTextArea;

    @FXML
    private TextField outgoingMessageTextField;

    @FXML
    private TextArea outgoingMessagesTextArea;

    @FXML
    private Button sendButton;

    public ChatController()
    {
        // NoOp
    }

    @Override
    public void setApp(App app)
    {
        this.app = app;
        app.setStatusText("");
        app.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        switch (evt.getPropertyName())
        {
            case App.PROP_ACTIVEVIEW:
            {
                if ((Views) evt.getOldValue() == CHAT)
                {
                    app.removePropertyChangeListener(this);
                }

                break;
            }

            case App.PROP_LOGGEDIN:
            {
                sendButton.setDisable(!(boolean) evt.getNewValue());

                break;
            }

            default:
            {
                break;
            }
        }
    }

    @Override
    public void setFocus()
    {
        outgoingMessageTextField.requestFocus();
    }

    /**
     * Handle the Send Button event.
     *
     * @param event
     */
    @FXML
    private void handleSendButton(ActionEvent event)
    {
        String text = outgoingMessageTextField.getText();
        outgoingMessageTextField.clear();

        outgoingMessagesTextArea.appendText(text + "\n");

        // TODO: Send text to Socket Server
        incomingMessagesTextArea.appendText(text + "\n");
        outgoingMessageTextField.requestFocus();

        event.consume();
    }
}
