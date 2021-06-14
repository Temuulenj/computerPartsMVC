package Model;

import DAO.UserDAO;

public class User extends Person {
    final int identity=0;
    public User(){};

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getIdentity() {
        return identity;
    }

    public void showAll() {
    }
}