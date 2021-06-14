package DAO;

import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO{
    Connection conn=getConn();
    User u=new User();

    public String getPassword(String username) throws Exception{
        SQL = "SELECT Password FROM user  WHERE UserName= ? ";
        preStr = conn.prepareStatement(SQL);
        preStr.setString(1,username);
        rs= preStr.executeQuery();
        while (!rs.next()){
            return null;
        }
        return rs.getString("password");
    }

    public int getIdentity(String username) throws Exception{
        SQL = "SELECT Identity FROM User WHERE UserName= ? ";
        preStr = conn.prepareStatement(SQL);
        preStr.setString(1,username);
        rs= preStr.executeQuery();
        while (!rs.next()){
            return -1;
        }
        return rs.getInt("Identity");
    }
}
