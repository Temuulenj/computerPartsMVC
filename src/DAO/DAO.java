package DAO;

import java.sql.*;

public class  DAO {

    static final private String USER = "root";
    static final private String PASSWORD = "024019";
    static final private String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final private String URL = "jdbc:mysql://localhost:3306/partsdata";
    PreparedStatement preStr=null;
    String SQL=null;
    ResultSet rs=null;
    private static Connection conn = null;
    public static Connection getConn() {
        try {
            //注册驱动
            Class.forName(DRIVER);
            //连接数据库
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
