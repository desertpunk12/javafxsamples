import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("a_helloworld/HelloWorld.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("b_inputtext/InputText.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("c_login/Login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("d_crud/BasicCrud.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        Scene sc = new Scene(root);
        sc.getStylesheets().add(getClass().getResource("d_crud/Style.css").toString());


//        Parent root = new Pane();

//        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
