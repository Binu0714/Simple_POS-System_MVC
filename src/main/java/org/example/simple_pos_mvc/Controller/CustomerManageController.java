package org.example.simple_pos_mvc.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerManageController {

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<?, ?> addressColumn;

    @FXML
    private TextField addressField;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<?, ?> customerIdColumn;

    @FXML
    private Label customerIdLabel;

    @FXML
    private TableView<?> customerTable;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TextField emailField;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<?, ?> nicColumn;

    @FXML
    private TextField nicField;

    @FXML
    private TableColumn<?, ?> phoneColumn;

    @FXML
    private TextField phoneField;

    @FXML
    private Label totalCustomersLabel;

    @FXML
    private Button updateButton;

    @FXML
    void handleAddAction(ActionEvent event) {

    }

    @FXML
    void handleClearAction(ActionEvent event) {

    }

    @FXML
    void handleDeleteAction(ActionEvent event) {

    }

    @FXML
    void handleUpdateAction(ActionEvent event) {

    }

}
