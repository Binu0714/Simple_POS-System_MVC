package org.example.simple_pos_mvc.Model;

import org.example.simple_pos_mvc.DTO.CustomerDto;
import org.example.simple_pos_mvc.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?,?)",
                customerDto.getCus_id(),
                customerDto.getCus_name(),
                customerDto.getCus_nic(),
                customerDto.getCus_email(),
                customerDto.getCus_phone(),
                customerDto.getCus_address()
        );
    }

    public String generateCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT cus_id FROM customer ORDER BY cus_id DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String numericPart = lastId.substring(1);
            int nextIdNumber = Integer.parseInt(numericPart) + 1;
            return String.format("C%03d", nextIdNumber);
        }
        return "C001";
    }

    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
    ResultSet rst = CrudUtil.execute("SELECT * FROM customer");

    ArrayList<CustomerDto> customerDtos = new ArrayList<>();

    while (rst.next()) {
       CustomerDto customerDto = new CustomerDto(
            rst.getString(1),
            rst.getString(2),
            rst.getString(3),
            rst.getString(4),
            rst.getString(5),
            rst.getString(6)
       );
       customerDtos.add(customerDto);
    }
    return customerDtos;
    }

    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE customer SET name=?, nic=?, email=?, phone=?, address=? WHERE cus_id=?",
                    customerDto.getCus_name(),
                    customerDto.getCus_nic(),
                    customerDto.getCus_email(),
                    customerDto.getCus_phone(),
                    customerDto.getCus_address(),
                    customerDto.getCus_id()
                );
    }

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE cus_id=?", id);
    }

    public int getTotalCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT COUNT(*) FROM customer");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    public CustomerDto findById(String selectedId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE cus_id=?", selectedId);
        if (rst.next()) {
            return new CustomerDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        }
        return null;
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT cus_id FROM customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }
        return customerIds;
    }

}
