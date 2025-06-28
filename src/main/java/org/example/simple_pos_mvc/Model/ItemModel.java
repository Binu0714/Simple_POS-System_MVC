package org.example.simple_pos_mvc.Model;

import org.example.simple_pos_mvc.DTO.CustomerDto;
import org.example.simple_pos_mvc.DTO.ItemDto;
import org.example.simple_pos_mvc.DTO.OrderDetailDto;
import org.example.simple_pos_mvc.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public boolean saveItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO item VALUES (?,?,?,?)",
                    itemDto.getItem_id(),
                    itemDto.getName(),
                    itemDto.getQty(),
                    itemDto.getUnit_price()
        );
    }

    public String generateItemId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT item_id FROM item ORDER BY item_id DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String numericPart = lastId.substring(1);
            int nextIdNumber = Integer.parseInt(numericPart) + 1;
            return String.format("I%03d", nextIdNumber);
        }
        return "I001";
    }

    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item");

        ArrayList<ItemDto> itemDtos = new ArrayList<>();

        while (rst.next()) {
            ItemDto itemDto = new ItemDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    public boolean updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE item SET name=?, qty=?, unit_price=? WHERE item_id=?",
                itemDto.getName(),
                itemDto.getQty(),
                itemDto.getUnit_price(),
                itemDto.getItem_id()
        );
    }

    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE item_id=?", id);
    }

    public int getTotalItems() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT COUNT(*) FROM item");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    public ItemDto findById(String selectedId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE item_id=?", selectedId);
        if (rst.next()) {
            return new ItemDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
        }
        return null;
    }

    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT item_id FROM item");

        ArrayList<String> itemIds = new ArrayList<>();

        while (rst.next()) {
            itemIds.add(rst.getString(1));
        }
        return itemIds;
    }

    public boolean reduceItemQty(OrderDetailDto orderDetailDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE item SET qty=qty-? WHERE item_id=?",
                orderDetailDto.getQty(),
                orderDetailDto.getItem_id()
        );
    }
}
