package org.example.simple_pos_mvc.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.example.simple_pos_mvc.Model.LoginModel;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label errorLabel;

    @FXML
    private StackPane ancLogin;

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

    LoginModel loginModel = new LoginModel();

    @FXML
    void handleLogin(ActionEvent event) {
        System.out.println("login btn clicked");

        String enteredUsername = usernameField.getText();
        String enteredPassword = passwordField.getText();

        if (loginModel.checkLogin(enteredUsername,enteredPassword)){
            System.out.println("login successful..");

        }else{
            if (loginModel.checkUsername(enteredUsername) && ! (loginModel.checkPassword(enteredPassword))){
                usernameField.setStyle("-fx-text-box-border: blue; ");
                passwordField.setStyle("-fx-text-box-border: red; ");
            } else if (!(loginModel.checkUsername(enteredUsername)) && loginModel.checkPassword(enteredPassword)) {
                usernameField.setStyle("-fx-text-box-border: red; ");
                passwordField.setStyle("-fx-text-box-border: blue; ");
            } else {
                passwordField.setStyle("-fx-text-box-border: red; ");
                usernameField.setStyle("-fx-text-box-border: red; ");

                passwordField.setText("");
                usernameField.setText("");
            }
        }
    }

    @FXML
    void handleSignUp(ActionEvent event) throws IOException {
        System.out.println("sign up btn clicked");

        ancLogin.getChildren().clear();
        StackPane load = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
        ancLogin.getChildren().add(load);
    }

}
