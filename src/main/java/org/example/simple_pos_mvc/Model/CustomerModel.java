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

}
