package Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
public class Main extends Application {
    public static Stage s=new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/login.fxml")));
        s.setTitle("库存管理");
        s.setScene(new Scene(root));
        s.show();
    }
    public static void closeStage(){
        s.close();
    }
    public static void main(String[] args) {
        launch(args);
    }
}