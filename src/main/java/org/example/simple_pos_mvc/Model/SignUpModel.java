package org.example.simple_pos_mvc.Model;

import org.example.simple_pos_mvc.DTO.SignUpDto;
import org.example.simple_pos_mvc.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpModel {
    public boolean saveUser(SignUpDto signUpDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO user VALUES (?,?,?,?)",
                signUpDto.getUser_id(),
                signUpDto.getUsername(),
                signUpDto.getPassword(),
                signUpDto.getEmail()
        );
    }

    public String generateUserId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1");
        if (rst.next()) {
            String lastId = rst.getString(1); // e.g., "U09"
            String numericPart = lastId.substring(1); // Extract "09"
            int nextIdNumber = Integer.parseInt(numericPart) + 1; // 10
            return String.format("U%02d", nextIdNumber); // "U10"
        }
        return "U01"; // Default first ID
    }

    public int getTotalUsers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT COUNT(*) FROM user");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

}
