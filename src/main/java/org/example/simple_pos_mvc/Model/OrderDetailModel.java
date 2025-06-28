package org.example.simple_pos_mvc.Model;

import org.example.simple_pos_mvc.DTO.OrderDetailDto;
import org.example.simple_pos_mvc.Util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailModel {

    public ItemModel itemModel = new ItemModel();

    public boolean saveOrderDetail(ArrayList<OrderDetailDto> orderDetailDtos) throws SQLException, ClassNotFoundException {
        for (OrderDetailDto orderDetailDto : orderDetailDtos) {

            boolean isSaved = saveOrderDetail(orderDetailDto);
            if (!isSaved) {
                return false;
            }

            boolean isItemUpdated = itemModel.reduceItemQty(orderDetailDto);
            if (!isItemUpdated) {
                return false;
            }
        }
        return true;
    }

    private boolean saveOrderDetail(OrderDetailDto orderDetailDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orderdetails VALUES (?,?,?,?)",
                orderDetailDto.getOrder_id(),
                orderDetailDto.getItem_id(),
                orderDetailDto.getQty(),
                orderDetailDto.getPrice()
        );
    }

}
