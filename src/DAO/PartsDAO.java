package DAO;

import Model.Parts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class PartsDAO extends DAO {
    Connection conn=getConn();
    public ObservableList<Parts> getObsData() throws Exception{
        ObservableList<Parts> data= FXCollections.observableArrayList();
        SQL="SELECT ID, NAME, UNIT_PRISE, AMOUNT,SORT FROM parts";
        preStr=conn.prepareStatement(SQL);
        rs=preStr.executeQuery();
        while (rs.next()){
            Parts c=new Parts(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5));
            data.add(c);
        }
        return data;
    }

    public boolean Delete(int id){
        if(haveId(id)==false){
            System.out.println("数据不存在");
            return false;
        }
        Connection conn=getConn();
        SQL="DELETE FROM parts WHERE ID=?";
        try {
            preStr=conn.prepareStatement(SQL);
            preStr.setInt(1,id);
            return !preStr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean Insert(Parts p) {
        Connection conn = getConn();
        SQL = "INSERT INTO parts VALUES (?,?,?,?,?)";
        try {
            preStr = conn.prepareStatement(SQL);
            preStr.setInt(1, p.getId());
            preStr.setString(2, p.getName());
            preStr.setInt(3, p.getUnitPrise());
            preStr.setInt(4, p.getAmount());
            preStr.setString(5, p.getSort());
            preStr.execute();
            return haveId(p.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean Update(Parts p){
        SQL="UPDATE parts set NAME=?,UNIT_PRISE=?,AMOUNT=?,SORT=? WHERE ID=?";
        try {
            preStr=conn.prepareStatement(SQL);
            preStr.setString(1,p.getName());
            preStr.setInt(2,p.getUnitPrise());
            preStr.setInt(3,p.getAmount());
            preStr.setString(4,p.getSort());
            preStr.setInt(5,p.getId());
            return !preStr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Parts getParts(int id){
        SQL="SELECT ID, NAME, UNIT_PRISE, AMOUNT,SORT FROM parts where ID=?";
        try {
            preStr=conn.prepareStatement(SQL);
            preStr.setInt(1,id);
            rs=preStr.executeQuery();
            while (rs.next()) {
                return new Parts(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean haveId(int id){
        SQL="SELECT ID FROM parts WHERE ID=?";
        try {
            preStr=conn.prepareStatement(SQL);
            preStr.setInt(1,id);
            rs= preStr.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean haveName(String name){
        SQL="SELECT NAME FROM parts WHERE ID=?";
        try {
            preStr=conn.prepareStatement(SQL);
            preStr.setString(1,name);
            rs= preStr.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
