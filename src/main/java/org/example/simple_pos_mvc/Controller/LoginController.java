package org.example.simple_pos_mvc.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label errorLabel;

    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberMeCheckbox;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private TextField usernameField; 

    @FXML
    void handleLogin(ActionEvent event) {
        System.out.println("login btn clicked");
    }

    @FXML
    void handleSignUp(ActionEvent event) {
        System.out.println("sign up btn clicked");
    }

}
