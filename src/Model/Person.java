package Model;

import DAO.UserDAO;

public class Person {
    String userName,password;
    int identity;

    public Person(){}

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

    public int  Login() {
        UserDAO ud=new UserDAO();
        try {
            if(ud.getPassword(this.userName).equals(this.password)){
                return ud.getIdentity(this.userName);
            }
        } catch (NullPointerException e){
            return -2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}


