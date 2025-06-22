package org.example.simple_pos_mvc.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.example.simple_pos_mvc.Model.CustomerModel;
import org.example.simple_pos_mvc.Model.ItemModel;
import org.example.simple_pos_mvc.Model.SignUpModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private CategoryAxis chartXAxis;

    @FXML
    private NumberAxis chartYAxis;

    @FXML
    private Button customerManageBtn;

    @FXML
    private Button customerReportBtn;

    @FXML
    private LineChart<?, ?> dailyOrdersChart;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Label dateTimeLabel;

    @FXML
    private Button financialReportBtn;

    @FXML
    private Button inventoryReportBtn;

    @FXML
    private Button itemManageBtn;

    @FXML
    private AnchorPane loadPane;

    @FXML
    private Button logoutBtn;

    @FXML
    private VBox navigationBar;

    @FXML
    private Button orderManageBtn;

    @FXML
    private Button salesReportBtn;

    @FXML
    private Label totalCustomersLabel;

    @FXML
    private Label totalItemsLabel;

    @FXML
    private Label totalOrdersLabel;

    @FXML
    private Label totalUsersLabel;

    @FXML
    private AnchorPane dashAnc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            int totalCustomers = new CustomerModel().getTotalCustomers();
            totalCustomersLabel.setText(String.valueOf(totalCustomers));

            int totalUsers = new SignUpModel().getTotalUsers();
            totalUsersLabel.setText(String.valueOf(totalUsers));

            int totalItems = new ItemModel().getTotalItems();
            totalItemsLabel.setText(String.valueOf(totalItems));

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleCustomerManageAction(ActionEvent event) throws IOException {
        System.out.println("customer manage btn clicked");

        loadPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/customerManage.fxml"));
        loadPane.getChildren().add(load);
    }

    @FXML
    void handleCustomerReportAction(ActionEvent event) {

    }

    @FXML
    void handleDashboardAction(ActionEvent event) throws IOException {
        System.out.println("dashboard btn clicked");

//        loadPane.getChildren().clear();
//        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
//        loadPane.getChildren().add(load);
    }

    @FXML
    void handleFinancialReportAction(ActionEvent event) {

    }

    @FXML
    void handleInventoryReportAction(ActionEvent event) {

    }

    @FXML
    void handleItemManageAction(ActionEvent event) throws IOException {
        System.out.println("item manage btn clicked");

        loadPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/itemManage.fxml"));
        loadPane.getChildren().add(load);
    }

    @FXML
    void handleLogoutAction(ActionEvent event) throws IOException {
        System.out.println("logout btn clicked");

        dashAnc.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        dashAnc.getChildren().add(load);
    }

    @FXML
    void handleOrderManageAction(ActionEvent event) {
        System.out.println("order manage btn clicked");
    }

    @FXML
    void handleSalesReportAction(ActionEvent event) {

    }

}
