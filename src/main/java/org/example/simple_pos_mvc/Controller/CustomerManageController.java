package org.example.simple_pos_mvc.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.simple_pos_mvc.DTO.CustomerDto;
import org.example.simple_pos_mvc.DTO.TM.CustomerTM;
import org.example.simple_pos_mvc.Model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerManageController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<CustomerTM, String> addressColumn;

    @FXML
    private TextField addressField;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<CustomerTM, String> customerIdColumn;

    @FXML
    private Label customerIdLabel;

    @FXML
    private TableView<CustomerTM> customerTable;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<CustomerTM, String> emailColumn;

    @FXML
    private TextField emailField;

    @FXML
    private TableColumn<CustomerTM, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<CustomerTM, String> nicColumn;

    @FXML
    private TextField nicField;

    @FXML
    private TableColumn<CustomerTM, String> phoneColumn;

    @FXML
    private TextField phoneField;

    @FXML
    private Label totalCustomersLabel;

    @FXML
    private Button updateButton;

    CustomerModel customerModel = new CustomerModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("cus_id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("cus_name"));
        nicColumn.setCellValueFactory(new PropertyValueFactory<>("cus_nic"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("cus_email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("cus_phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("cus_address"));

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadTbaleData();

        try {
            String generatedCustomerId = customerModel.generateCustomerId();
            customerIdLabel.setText(generatedCustomerId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        nameField.setText("");
        nicField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
    }

    public void loadTbaleData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> customerDtos = customerModel.getAllCustomers();

        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDto customerDto : customerDtos) {
            CustomerTM customerTM = new CustomerTM(
                    customerDto.getCus_id(),
                    customerDto.getCus_name(),
                    customerDto.getCus_nic(),
                    customerDto.getCus_email(),
                    customerDto.getCus_phone(),
                    customerDto.getCus_address()
            );
            customerTMS.add(customerTM);
        }
        customerTable.setItems(customerTMS);
    }

    @FXML
    void handleAddAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("save btn clicked");

        String id = customerIdLabel.getText();
        String name = nameField.getText();
        String nic = nicField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        CustomerDto customerDto = new CustomerDto(
                id,
                name,
                nic,
                email,
                phone,
                address
        );

        boolean isSaved = customerModel.saveCustomer(customerDto);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
        }
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
