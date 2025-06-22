package org.example.simple_pos_mvc.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class DashboardController {

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
    void handleCustomerManageAction(ActionEvent event) {

    }

    @FXML
    void handleCustomerReportAction(ActionEvent event) {

    }

    @FXML
    void handleDashboardAction(ActionEvent event) {

    }

    @FXML
    void handleFinancialReportAction(ActionEvent event) {

    }

    @FXML
    void handleInventoryReportAction(ActionEvent event) {

    }

    @FXML
    void handleItemManageAction(ActionEvent event) {

    }

    @FXML
    void handleLogoutAction(ActionEvent event) {

    }

    @FXML
    void handleOrderManageAction(ActionEvent event) {

    }

    @FXML
    void handleSalesReportAction(ActionEvent event) {

    }

}
