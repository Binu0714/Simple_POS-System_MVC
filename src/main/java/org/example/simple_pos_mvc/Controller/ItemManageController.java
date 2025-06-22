package org.example.simple_pos_mvc.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ItemManageController {

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> itemIdColumn;

    @FXML
    private Label itemIdLabel;

    @FXML
    private TableView<?> itemTable;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<?, ?> qtyColumn;

    @FXML
    private TextField qtyField;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> unitPriceColumn;

    @FXML
    private TextField unitPriceField;

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
