package Controller;

import Model.Main;
import Model.Parts;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Objects;

public class UserController extends Controller{
    public TableView<Parts> table;
    public  TableColumn<Parts,String> c_id;
    public TableColumn<Parts,String> c_name;
    public TableColumn<Parts,String> c_unitPrise;
    public TableColumn<Parts,String> c_amount;
    public TableColumn<Parts,String> c_sort;
    public Button Refresh;

    ObservableList<Parts> data=null;
    void init(){
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/user.fxml")));
            Stage main=new Stage();
            Scene scene=new Scene(root);
            main.setTitle("User");
            main.setScene(scene);
            Main.closeStage();
            main.show();
            table.refresh();
        }catch (NullPointerException e){ }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize(){
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
        // 设置数据源
        c_sort.setCellValueFactory(new PropertyValueFactory<>("sort"));
        table.setItems(data);
        Refresh.setOnAction(event -> initialize());
    }
}
