package Controller;

import DAO.PartsDAO;
import Model.Main;
import Model.Parts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.util.Objects;

public class UserController extends Controller{
    public TableView table;
    public  TableColumn c_id;
    public TableColumn c_name;
    public TableColumn c_unitPrise;
    public TableColumn c_amount;
    public TableColumn c_sort;
    public Button editable;

    ObservableList<Parts> data=null;
    void init(){
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/user.fxml")));
            Stage main=new Stage();
            Scene scene=new Scene(root);
            main.setTitle("User");
            main.setScene(scene);
            Main.closeStage();
            main.show();
            table.refresh();
        } catch (NullPointerException e){
            System.out.println("空");
        }catch (Exception e) {
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
    }

    public void Editable(ActionEvent actionEvent) {
        table.setEditable(!table.isEditable());
        c_name.setCellFactory(TextFieldTableCell.forTableColumn());
        editable.setText(table.isEditable()?"可编辑":"不可编辑");
    }

    public void Refresh(ActionEvent actionEvent) {
        ObservableList<Parts> data = new Parts().getData();
        table.setItems(data);
        // 设置分箱的类下面的属性名
        c_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        c_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        c_unitPrise.setCellValueFactory(new PropertyValueFactory<>("unitPrise"));

        c_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        // 设置数据源
        c_sort.setCellValueFactory(new PropertyValueFactory<>("sort"));
        table.setItems(data);
    }

    public void Save(ActionEvent actionEvent) {
        for(int i=0;i<data.size();i++){
            Parts p=data.get(i);
            System.out.println(p.getName());
        }
    }
}
