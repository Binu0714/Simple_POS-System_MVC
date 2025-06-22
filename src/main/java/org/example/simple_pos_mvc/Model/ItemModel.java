package org.example.simple_pos_mvc.Model;

import org.example.simple_pos_mvc.DTO.ItemDto;
import org.example.simple_pos_mvc.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
