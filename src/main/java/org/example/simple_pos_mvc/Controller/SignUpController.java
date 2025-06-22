package org.example.simple_pos_mvc.Controller;

import com.sun.jdi.InconsistentDebugInfoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.example.simple_pos_mvc.DTO.SignUpDto;
import org.example.simple_pos_mvc.Model.SignUpModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private AnchorPane ancSignUp;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String id = signUpModel.generateUserId();
            userIdFeild.setText(id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleLoginLinkAction(ActionEvent event) throws IOException {
        System.out.println("login btn link clicked");

        ancSignUp.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        ancSignUp.getChildren().add(load);
    }

    @FXML
    void handleSignUp(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("sign up btn clicked");

        String id = userIdFeild.getText();
        String username = usernameFeild.getText();
        String email = emailFeild.getText();
        String password = passwordField.getText();

        SignUpDto signUpDto = new SignUpDto(
                id,
                username,
                password,
                email
        );

        boolean isSaved = signUpModel.saveUser(signUpDto);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "User Saved Successfully...!").show();

            ancSignUp.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            ancSignUp.getChildren().add(load);
        }else {
            new Alert(Alert.AlertType.ERROR, "User Not Saved...!").show();
        }
    }

}
