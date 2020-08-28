package a_helloworld;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloWorldController {

  private final String helloWorld = "Hello World";

  @FXML
  private Label lblHelloWorld;

  public void printHelloWorld(){
    System.out.println(helloWorld);

    lblHelloWorld.setText(helloWorld);
    lblHelloWorld.setVisible(true);
  }
}
