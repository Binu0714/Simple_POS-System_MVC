package org.example.simple_pos_mvc.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.simple_pos_mvc.DTO.CustomerDto;
import org.example.simple_pos_mvc.DTO.ItemDto;
import org.example.simple_pos_mvc.DTO.OrderDetailDto;
import org.example.simple_pos_mvc.DTO.OrderDto;
import org.example.simple_pos_mvc.DTO.TM.CartTM;
import org.example.simple_pos_mvc.Model.CustomerModel;
import org.example.simple_pos_mvc.Model.ItemModel;
import org.example.simple_pos_mvc.Model.OrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderManageController implements Initializable {

    @FXML
    private Button addToCartButton;

    @FXML
    private TableColumn<?, ?> cartActionColumn;

    @FXML
    private Label cartItemCountLabel;

    @FXML
    private TableColumn<?, ?> cartItemIdColumn;

    @FXML
    private TableColumn<?, ?> cartItemNameColumn;

    @FXML
    private TableColumn<?, ?> cartQuantityColumn;

    @FXML
    private TableView<CartTM> cartTable;

    @FXML
    private TableColumn<?, ?> cartTotalPriceColumn;

    @FXML
    private TableColumn<?, ?> cartUnitPriceColumn;

    @FXML
    private Button confirmOrderButton;

    @FXML
    private ComboBox<String> customerIdComboBox;

    @FXML
    private Label customerNameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TextField discountField;

    @FXML
    private ComboBox<String> itemIdComboBox;

    @FXML
    private Label itemNameLabel;

    @FXML
    private Label orderIdLabel;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Label qtyOnHandLabel;

    @FXML
    private Spinner<?> quantitySpinner;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label totalItemsLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private TextField qtyFeild;

    @FXML
    private Label unitPriceLabel;


    CustomerModel customerModel = new CustomerModel();
    ItemModel itemModel = new ItemModel();
    OrderModel orderModel = new OrderModel();

    private ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cartItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        cartItemNameColumn.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        cartUnitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        cartQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("buying_Qty"));
        cartTotalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        cartActionColumn.setCellValueFactory(new PropertyValueFactory<>("remove"));

        try {
            refreshPage();

            LocalDate currentDate = LocalDate.now();
            dateLabel.setText(currentDate.toString());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void calculateTotal(KeyEvent event) {

    }

    @FXML
    void handleAddToCart(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTable();
        refreshTable();
    }

    public void refreshTable() throws SQLException, ClassNotFoundException {
        orderIdLabel.setText(orderModel.generateOrderId());

        customerNameLabel.setText("");
        itemIdComboBox.setValue(null);
        itemNameLabel.setText("");
        unitPriceLabel.setText("");
        qtyOnHandLabel.setText("");
        qtyFeild.setText("");
    }

    public void  loadTable(){
        String item_id = itemIdComboBox.getValue();
        String item_name = itemNameLabel.getText();
        double price = Double.parseDouble(unitPriceLabel.getText());
        int buying_Qty = Integer.parseInt(qtyFeild.getText());
        double total = buying_Qty * price;

        Button remove = new Button("remove");
        remove.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        int qty_on_hand = Integer.parseInt(qtyOnHandLabel.getText());

        if (buying_Qty > qty_on_hand) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Quantity Error");
            alert.setHeaderText("Insufficient Stock");
            alert.setContentText("Requested quantity exceeds the available stock!");
            alert.showAndWait();
            return;
        }

        CartTM cartTM = new CartTM(
                item_id,
                item_name,
                price,
                buying_Qty,
                total,
                remove
        );
        cartTMS.add(cartTM);
        removeCart(remove, cartTM);

        cartTable.setItems(cartTMS);
    }

    private void removeCart(Button button, CartTM cartTM) {
        button.setOnAction(actionEvent -> {
            CartTM selectedItem = cartTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem);
            cartTMS.remove(cartTM);
        });
    }

    @FXML
    void handleConfirmOrder(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (cartTable.getItems().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please add items to cart..!").show();
            return;
        }

        if (customerIdComboBox.getValue() == null || customerIdComboBox.getValue().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a customer to place the order..!").show();
            return;
        }

        String order_id = orderIdLabel.getText();
        String customer_id = customerIdComboBox.getValue();
        String order_date = dateLabel.getText();

        ArrayList<OrderDetailDto> orderDetailDtos = new ArrayList<>();

        for (CartTM cartTM : cartTMS) {
            OrderDetailDto orderDetailDto = new OrderDetailDto(
                    order_id,
                    cartTM.getItem_id(),
                    cartTM.getBuying_Qty(),
                    cartTM.getTotal()
            );
            orderDetailDtos.add(orderDetailDto);
        }

        OrderDto orderDto = new OrderDto(
                order_id,
                customer_id,
                order_date,
                orderDetailDtos
        );

        boolean isSaved = orderModel.saveOrder(orderDto);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Order saved..!").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR, "Order fail..!").show();
        }
    }

    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadCustomerId();
        loadItemId();
        refreshTable();

        cartTMS.clear();
        customerIdComboBox.setValue(null);
        cartTable.refresh();
    }

    @FXML
    void handleCustomerSelection(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedId = (String) customerIdComboBox.getSelectionModel().getSelectedItem();
        CustomerDto customerDto = customerModel.findById(selectedId);

        if (customerDto != null) {
            customerNameLabel.setText(customerDto.getCus_name());
        }
    }

    public void loadCustomerId() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIds = customerModel.getAllCustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerIds);
        customerIdComboBox.setItems(observableList);
    }

    @FXML
    void handleItemSelection(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedId = (String) itemIdComboBox.getSelectionModel().getSelectedItem();
        ItemDto itemDto = itemModel.findById(selectedId);

        if (itemDto != null) {
            itemNameLabel.setText(itemDto.getName());
            qtyOnHandLabel.setText(String.valueOf(itemDto.getQty()));
            unitPriceLabel.setText(String.valueOf(itemDto.getUnit_price()));
        }
    }

    public void loadItemId() throws SQLException, ClassNotFoundException {
        ArrayList<String> itemIds = itemModel.getAllItemIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(itemIds);
        itemIdComboBox.setItems(observableList);
    }

    @FXML
    void handlePlaceOrder(ActionEvent event) {

    }

    @FXML
    void onCartTableClick(MouseEvent event) {

    }

}
