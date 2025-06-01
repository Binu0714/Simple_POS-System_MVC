package org.example.simple_pos_mvc.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private Label errorLabel;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Hyperlink loginLink;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameField;

    @FXML
    void handleSignUp(ActionEvent event) {

    }

    @FXML
    void handleLogin(ActionEvent event) {

    }

}
