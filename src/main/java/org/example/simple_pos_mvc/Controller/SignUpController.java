package org.example.simple_pos_mvc.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.example.simple_pos_mvc.DTO.SignUpDto;
import org.example.simple_pos_mvc.Model.SignUpModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private TextField emailFeild;

    @FXML
    private Label errorLabel;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Hyperlink loginLink;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private StackPane signUpLoad;

    @FXML
    private TextField userIdFeild;

    @FXML
    private TextField usernameFeild;

    SignUpModel signUpModel = new SignUpModel();


    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        signUpLoad.getChildren().clear();
        StackPane load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        signUpLoad.getChildren().add(load);
    }

    @FXML
    void handleSignUp(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("sign up btn clicked");

        String id = signUpModel.generateUserId();
        userIdFeild.setText(id);
        String username = usernameFeild.getText();
        String password = passwordField.getText();
        String email = emailFeild.getText();

        SignUpDto signUpDto = new SignUpDto(
                id,
                username,
                password,
                email
        );
        boolean isSaved = signUpModel.saveUser(signUpDto);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Registration Complete.Please login...!").show();

            signUpLoad.getChildren().clear();
            StackPane load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            signUpLoad.getChildren().add(load);
        }else {
            new Alert(Alert.AlertType.ERROR, "Registration Failed...!").show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String id = signUpModel.generateUserId();
            userIdFeild.setText(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
