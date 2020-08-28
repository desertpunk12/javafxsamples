package c_login;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowController {
  private String name;

  @FXML
  private Label lblMessage;

  public void setName(String name){
    this.name = name;
    lblMessage.setText("Welcome " + name);
  }

}
