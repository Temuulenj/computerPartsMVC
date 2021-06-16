package Controller;

import DAO.PartsDAO;
import Model.Parts;
import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminController extends Controller{
    public TextField p1_id;
    public TextField p1_name;
    public TextField p1_num;
    public TextField p1_unitPrise;
    public TableView<Parts> table;
    public  TableColumn c_id;
    public TableColumn c_name;
    public TableColumn c_unitPrise;
    public TableColumn c_amount;
    public TableColumn c_sort;
    public TableColumn Del;
    public ComboBox p1_sort;
    public ComboBox o_sort;
    public TextField o_value;
    public TextField o_amount;
    public TableView u_table;
    public TableColumn u_user;
    public TableColumn u_password;
    public TableColumn u_delete;
    public TableColumn u_identity;
    final String jc="^[1-9]\\d*$";
    //消息队列
    public ListView<String> lv=new ListView<>();
    public TextField a_username;
    public TextField a_password;
    public ComboBox a_identity;
    public ListView u_lv;
    ObservableList<String> uResult=FXCollections.observableArrayList();
    ObservableList<Parts> data=null;
    ObservableList<String> result = FXCollections.observableArrayList();
    ObservableList<Person> user=new Person().getData();
    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

    //初始化
    @FXML
    private void initialize(){
        //入库分类
        p1_sort.setItems(FXCollections.observableArrayList("CPU", "显卡", "内存","主板","硬盘"));
        //出库类型
        o_sort.setItems(FXCollections.observableArrayList("编号"));
        //身份选择
        a_identity.setItems(FXCollections.observableArrayList("管理员","用户"));
        //消息
        lv.setItems(result);
        //用户消息
        u_lv.setItems(uResult);
        data = new Parts().getData();
        if(data==null || data.size() == 0) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("系统消息");
            a.setHeaderText("表内无数据");
            a.setContentText("无库存");
            a.show();
            return;
        }
        // 设置分箱的类下面的属性名
        c_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        c_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        c_unitPrise.setCellValueFactory(new PropertyValueFactory<>("unitPrise"));
        c_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        c_sort.setCellValueFactory(new PropertyValueFactory<>("sort"));
        // 设置数据源
        table.setItems(data);
        table.setEditable(true);
        c_name.setCellFactory(TextFieldTableCell.forTableColumn());
        c_sort.setCellFactory(TextFieldTableCell.forTableColumn());
        Del.setCellFactory((col)->{
                    TableCell<Parts, String> cell = new TableCell<Parts, String>(){
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            //按钮显示文字
                            Button button2 = new Button("删除");
                            Del.setStyle("-fx-text-align-:center");
                            //按钮点击事件
                            button2.setOnMouseClicked((col) -> {
                                //获取list列表中的位置，进而获取列表对应的信息数据
                                Parts p = data.get(getIndex());
                                if(p.Delete()) {
                                    result.add(ft.format(new Date())+"\n删除成功！\n");
                                    data.remove(getIndex());
                                }
                            });
                            if (empty) {
                                //如果此列为空默认不添加元素
                                setText(null);
                                setGraphic(null);
                            } else {
                                //加载按钮
                                this.setGraphic(button2);
                            }
                        }
                    };
                    return cell;
                }
        );

        //用户表
        u_user.setCellValueFactory(new PropertyValueFactory<>("userName"));
        u_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        u_identity.setCellValueFactory(new PropertyValueFactory<>("identity"));
        u_delete.setCellFactory(col-> {
                    TableCell<Person, String> cell = new TableCell<Person, String>() {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            //按钮显示文字
                            Button button2 = new Button("删除");
                            Del.setStyle("-fx-text-align-:center");
                            //设置按钮颜色
                            //按钮点击事件
                            button2.setOnMouseClicked((col) -> {
                                //获取list列表中的位置，进而获取列表对应的信息数据
                                Person p = user.get(getIndex());
                                p.Delete();
                                user.remove(getIndex());
                                uResult.add(ft.format(new Date())+" 删除成功！");
                            });
                            if (empty) {
                                //如果此列为空默认不添加元素
                                setText(null);
                                setGraphic(null);
                            } else {
                                //加载按钮
                                this.setGraphic(button2);
                            }
                        }
                    };
                    return cell;
                }
        );
        u_table.setItems(user);
    }
    //刷新
    public void Refresh() {
        data = new Parts().getData();
        table.setItems(data);
    }

    public void uRefresh(){
        user=new Person().getData();
        u_table.setItems(user);
    }
    void init() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/admin.fxml")));
            Stage main = new Stage();
            Scene scene = new Scene(root);
            main.setTitle("Admin");
            main.setScene(scene);
            main.getIcons().add(new Image("View/img/icon.png"));
            Main.closeStage();
            main.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //是否数字
    public boolean isMatches(String bot){
        try{
            String regex="^[1-9]+[0-9]*$";
            //^[1-9]+\\d*$
            Pattern p=Pattern.compile(regex);
            Matcher m=p.matcher(bot);
            if(m.find()){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void insert(ActionEvent actionEvent) {
        if(p1_id.getText().length()<1||p1_name.getText().length()<1||p1_unitPrise.getText().length()<1||p1_num.getText().length()<1||p1_sort.getValue()==null||!isMatches(p1_id.getText())||!isMatches(p1_unitPrise.getText())||!isMatches(p1_num.getText())){
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("错 误");
            a.setHeaderText("输入不合法");
            a.show();
            return;
        }
        Parts p=new Parts(Integer.parseInt(p1_id.getText()),p1_name.getText(),Integer.parseInt(p1_unitPrise.getText()),Integer.parseInt(p1_num.getText()),p1_sort.getValue().toString());
        String inResult=p.Insert();
        if(inResult.equals("入库成功！\n")){
            p=new PartsDAO().getParts(p.getId());
            result.add(ft.format(new Date())+"\n编号："+p.getId()+" 名称："+p.getName()+" 单价："+p.getUnitPrise()+"\n"+"入库成功!\n");
        }
        else{
            result.add(ft.format(new Date())+"\n"+inResult);
        }
        Refresh();
    }

    public void update(TableColumn.CellEditEvent cellEditEvent) {
        Parts p= data.get(cellEditEvent.getTablePosition().getRow());
        p.setName(cellEditEvent.getNewValue().toString());
        System.out.println(new PartsDAO().Update(p));
    }

    public void Out(ActionEvent actionEvent) {
        if (o_sort.getValue()==null||o_amount.getText().length()<1||o_value.getText().length()<1||!isMatches(o_amount.getText())){
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("错 误");
            a.setHeaderText("输入不合法");
            a.show();
            return;
        }
        Parts p=new Parts();
        p.setId(Integer.parseInt(o_value.getText()));
        if(p.Out(Integer.parseInt(o_amount.getText()))){
            Alert a=new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("消息");
            a.setTitle("出库成功！");
            a.show();
            result.add(ft.format(new Date())+"\n出库成功！\n"+"商品编号："+p.getId()+" 商品数量："+o_amount.getText());
        }
        Refresh();
    }

    public void addUser(ActionEvent actionEvent) {
        if(a_username.getText()!=null||a_password.getText()!=null||a_identity.getValue()!=null){
            int identity=a_identity.getValue().toString().equals("管理员")?1:0;
            Person p=new Person(a_username.getText(),a_password.getText(),identity);
            if(p.add()){
                uResult.add(ft.format(new Date())+"\n用户名："+p.getUserName()+"\n添加成功");
            }else {
                uResult.add("添加失败-->用户名已存在！");
            }
            uRefresh();
        }
        else {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("系统消息");
            a.setHeaderText("输入不合法");
            a.show();
        }
    }
}
