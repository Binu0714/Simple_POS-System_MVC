package org.example.simple_pos_mvc.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.simple_pos_mvc.Model.LoginModel;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label errorMessageLabel;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameField;

    @FXML
    private AnchorPane ancLogin;

    LoginModel loginModel = new LoginModel();

    @FXML
    void handleLoginAction(ActionEvent event) {
        System.out.println("login btn clicked");

        String EnterUsername = usernameField.getText();
        String EnterPassword = passwordField.getText();

        if (loginModel.checkLogin(EnterUsername, EnterPassword)) {
            new Alert(Alert.AlertType.INFORMATION, "Login Successfull...!").show();

            System.out.println("dashboard loading..");
        }else {
            if (loginModel.checkUsername(EnterUsername) && !(loginModel.checkPassword(EnterPassword))) {
                usernameField.setStyle("-fx-text-box-border: blue; ");
                passwordField.setStyle("-fx-text-box-border: red; ");
            } else {
                passwordField.setStyle("-fx-text-box-border: red; ");
                usernameField.setStyle("-fx-text-box-border: red; ");

                passwordField.setText("");
                usernameField.setText("");
            }
        }
    }

    @FXML
    void handleSignUpAction(ActionEvent event) throws IOException {
        System.out.println("sign up btn clicked");

        ancLogin.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/signUp.fxml"));
        ancLogin.getChildren().add(load);

    }

}
