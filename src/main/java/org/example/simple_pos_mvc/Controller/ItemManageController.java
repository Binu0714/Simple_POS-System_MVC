package org.example.simple_pos_mvc.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.simple_pos_mvc.DTO.ItemDto;
import org.example.simple_pos_mvc.DTO.TM.ItemTM;
import org.example.simple_pos_mvc.Model.ItemModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemManageController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<ItemTM, String> itemIdColumn;

    @FXML
    private Label itemIdLabel;

    @FXML
    private TableView<ItemTM> itemTable;

    @FXML
    private TableColumn<ItemTM, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<ItemTM, String> qtyColumn;

    @FXML
    private TextField qtyField;

    @FXML
    private TableColumn<ItemTM, String> statusColumn;

    @FXML
    private TableColumn<ItemTM, String> unitPriceColumn;

    @FXML
    private TextField unitPriceField;

    @FXML
    private Button updateButton;

    ItemModel itemModel = new ItemModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unit_price"));

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        try {
            String generatedItemId = itemModel.generateItemId();
            itemIdLabel.setText(generatedItemId);
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        nameField.setText("");
        qtyField.setText("");
        unitPriceField.setText("");
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> itemDtos = itemModel.getAllItems();

        ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();

        for (ItemDto itemDto : itemDtos) {
            ItemTM itemTM = new ItemTM(
                    itemDto.getItem_id(),
                    itemDto.getName(),
                    itemDto.getQty(),
                    itemDto.getUnit_price()
            );
            itemTMS.add(itemTM);
        }
        itemTable.setItems(itemTMS);
    }

    @FXML
    void handleAddAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("save btn clicked");

        String id = itemIdLabel.getText();
        String name = nameField.getText();
        String qty = qtyField.getText();
        String unitPrice = unitPriceField.getText();

        if (id.isEmpty() || name.isEmpty() || qty.isEmpty() || unitPrice.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all fields!").show();
            return;
        }

        ItemDto itemDto = new ItemDto(
                id,
                name,
                Integer.parseInt(qty),
                Double.parseDouble(unitPrice)
        );

        boolean isSaved = itemModel.saveItem(itemDto);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Item saved...!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Item...!").show();
        }
    }

    @FXML
    void handleClearAction(ActionEvent event) {
        System.out.println("clear btn clicked");
    }

    @FXML
    void handleDeleteAction(ActionEvent event) {
        System.out.println("delete btn clicked");
    }

    @FXML
    void handleUpdateAction(ActionEvent event) {
        System.out.println("update btn clicked");
    }

}
