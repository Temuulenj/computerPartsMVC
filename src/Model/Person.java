package Model;

import DAO.UserDAO;
import javafx.collections.ObservableList;

public class Person {
    String userName,password;
    int identity;

    public Person(){}

    public Person(String userName, String password, int identity) {
        this.userName = userName;
        this.password = password;
        this.identity = identity;
    }

    public ObservableList<Person> getData(){
        try {
            return new UserDAO().getData();
        } catch (NullPointerException e){
            return null;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(){
        return new UserDAO().addUser(this);
    }

    public boolean Delete(){
        return new UserDAO().Delete(this.userName);
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

    public void setIdentity(int identity) {
        this.identity = identity;
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


