package org.example.simple_pos_mvc.Model;

import org.example.simple_pos_mvc.Util.CrudUtil;

import java.sql.ResultSet;

public class LoginModel {
    public boolean checkLogin(String username, String password) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM user WHERE username=? AND password=?", username, password);

            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUsername(String username) {
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM user WHERE username=?", username);

            if (result.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPassword(String password) {
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM user WHERE password=?", password);

            if (result.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
