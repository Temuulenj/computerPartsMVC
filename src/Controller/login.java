package Controller;

import Model.Main;
import Model.Person;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class login extends Controller{

    public TextField UserName;
    public PasswordField Password;
    public Button Login;
    public void Login(ActionEvent actionEvent) {
        Person p=new Person();
        if (UserName.getText().length()<1||Password.getText().length()<1){
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("错 误");
            a.setHeaderText("输入为空");
            a.setContentText("请输入账号和密码！");
            a.show();
            return;
        }
        p.setUserName(UserName.getText());;
        p.setPassword(Password.getText());
        //u.Login
        //1->管理员
        //0->用户
        //-1->失败
        int login=p.Login();
        if(login==1){//管理员
            new AdminController().init();
        } else if (login==0){//用户
            new UserController().init();
        } else if(login==-1){
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("错 误");
            a.setHeaderText("密码错误");
            a.show();
        }
        else{
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("错 误");
            a.setHeaderText("用户名不存在");
            a.show();
        }
    }
}
