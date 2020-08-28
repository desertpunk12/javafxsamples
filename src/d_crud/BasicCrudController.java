package d_crud;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.DB;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BasicCrudController implements Initializable {

  private Screen screen;
  private ObservableList<User> users;

  private boolean maximized = false;
  private double lastX, lastY, lastWidth, lastHeight;
  private double sceneX, sceneY;

  @FXML
  private Label lblTitle;

  @FXML
  private BorderPane barTitle;

  @FXML
  private TableView<User> tblMainTable;


  public BasicCrudController(){
  }

  @FXML
  private void close() {
    Platform.exit();
  }


  @FXML
  private void maximize() {
    Window window = lblTitle.getScene().getWindow();
    Rectangle2D bounds = screen.getVisualBounds();
    if (maximized) {
      window.setX(lastX);
      window.setY(lastY);
      window.setWidth(lastWidth);
      window.setHeight(lastHeight);
      maximized = false;
    } else {

      lastX = window.getX();
      lastY = window.getY();
      lastWidth = window.getWidth();
      lastHeight = window.getHeight();

      window.setX(bounds.getMinX());
      window.setY(bounds.getMinY());
      window.setWidth(bounds.getWidth());
      window.setHeight(bounds.getHeight());
      maximized = true;
    }
  }

  @FXML
  private void minimize() {
    Stage stage = (Stage)lblTitle.getScene().getWindow();
    stage.setIconified(true);
  }

  @FXML
  private void windowDragged(MouseEvent e) {
    Stage stage = (Stage)lblTitle.getScene().getWindow();
    double x = e.getScreenX() - sceneX;
    double y = e.getScreenY() - sceneY;
    stage.setX(x);
    stage.setY(y);
  }

  @FXML
  private void windowDragStart(MouseEvent e){
    this.sceneX = e.getSceneX();
    this.sceneY = e.getSceneY();
  }

  private void updateUserList() {
    try {
      ResultSet rs = DB.query("SELECT * FROM users");
      users.clear();
      while(rs.next()){
        int id = rs.getInt(1);
        String username = rs.getString(2);
        String password = rs.getString(3);
        String firstname = rs.getString(4);
        String lastname = rs.getString(5);
        String middlename = rs.getString(6);
        String email = rs.getString(7);
        String gender = rs.getString(8);
        Date birthday = rs.getDate(9);
        users.add(new User(id,username,password,firstname,lastname,middlename,email,gender,birthday));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  private void updateTable(){
    updateUserList();
    TableColumn<User, Integer> colId = new TableColumn<>("ID");
    TableColumn<User, String> colUsername = new TableColumn<>("Username");
    TableColumn<User, String> colPassword = new TableColumn<>("Password");
    TableColumn<User, String> colFirstname = new TableColumn<>("Firstname");
    TableColumn<User, String> colLastname = new TableColumn<>("Lastname");
    TableColumn<User, String> colMiddlename = new TableColumn<>("Middlename");
    TableColumn<User, String> colEmail = new TableColumn<>("Email");
    TableColumn<User, String> colGender = new TableColumn<>("Gender");
    TableColumn<User, Date> colBirthday = new TableColumn<>("Birthday");


    colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
    colLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    colMiddlename.setCellValueFactory(new PropertyValueFactory<>("middlename"));
    colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    colBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));

    tblMainTable.setItems(users);
    tblMainTable.getColumns().clear();
    tblMainTable.getColumns().addAll(colId,colUsername,colPassword,colFirstname,colLastname,colMiddlename,colEmail,colGender,colBirthday);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    screen = Screen.getPrimary();
    users = FXCollections.observableArrayList();

    updateTable();
  }
}
