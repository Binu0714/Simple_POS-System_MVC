package org.example.simple_pos_mvc.Model;

import org.example.simple_pos_mvc.DB.DBConnection;
import org.example.simple_pos_mvc.DTO.OrderDto;
import org.example.simple_pos_mvc.Util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public OrderDetailModel orderDetailModel = new OrderDetailModel();

    public String generateOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String numericPart = lastId.substring(1);
            int nextIdNumber = Integer.parseInt(numericPart) + 1;
            return String.format("O%03d", nextIdNumber);
        }
        return "O001";
    }

    public boolean saveOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isSaved = CrudUtil.execute("INSERT INTO orders VALUES (?,?,?)",
                    orderDto.getOrder_id(),
                    orderDto.getCustomer_id(),
                    orderDto.getOrder_date()
            );

            if (isSaved) {
                boolean isOrderDetailSaved = orderDetailModel.saveOrderDetail(orderDto.getOrderDetailsDTOS());

                if (isOrderDetailSaved) {
                    connection.commit();
                    return true;
                }
            }

            connection.rollback();
            return false;

        } catch (Exception e) {
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

}
