package c_login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DB;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {

  @FXML
  TextField txtUsername;
  @FXML
  PasswordField txtPassword;
  @FXML
  Label lblError;

  @FXML
  Stage primaryStage;



  public void test() {
    System.out.println("test");
  }

  public void tryLogin() {
    String username = txtUsername.getText();
    String password = txtPassword.getText();

    try {
      String query = String.format("SELECT * FROM users WHERE username LIKE '%s' AND password LIKE '%s'", username, password);
      ResultSet rs = DB.query(query, false);
      if (rs.next()) {
        System.out.println("Login Successs!!");
        lblError.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent sc = loader.load();
        txtUsername.getScene().setRoot(sc);
        MainWindowController mwc = loader.getController();
        mwc.setName(username);


      } else {
        System.out.println("Login Failed");
        lblError.setVisible(true);
      }
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }

  }

}
