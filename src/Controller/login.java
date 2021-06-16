package Controller;

import Model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class login extends Controller{

    public TextField UserName;
    public PasswordField Password;
    public Button Login;
    public Button logup;
    public Button back;

    @FXML
    private void initialize(){
        logup.setOnAction(event -> Logup());
        Login.setOnAction(event -> {
            if(Login.getText().equals("登            录")) {
                Login();
            }
            else {
                Logup();
            }
        });
        Password.setOnAction(event -> {
            if(Login.getText().equals("登            录")) {
                Login();
            }
            else {
                Logup();
            }
        });
        logup.setOnAction(e->{
            Login.setText("注            册");
            logup.setVisible(false);
            logup.setDisable(true);
            back.setDisable(false);
            back.setVisible(true);
        });
        back.setOnAction(event -> {
            Login.setText("登            录");
            back.setDisable(true);
            back.setVisible(false);
            logup.setVisible(true);
            logup.setDisable(false);
        });
    }

    public void Logup(){
        if (UserName.getText().length()<1&& Password.getText().length()<1) {
            Alert a=new Alert(Alert.AlertType.WARNING);
            a.setTitle("系统消息");
            a.setHeaderText("输入为空");
            a.show();
        } else {
            if (new Person(UserName.getText(), Password.getText(), 0).add()) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("系统消息");
                a.setHeaderText("注册成功！");
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("系统消息");
                a.setHeaderText("注册失败");
                a.setContentText("该用户名已被注册");
                a.show();
            }
        }
    }

    public void Login() {
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
