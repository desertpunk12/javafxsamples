package b_inputtext;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InputTextController {

  @FXML
  private Label lblAnswer;
  @FXML
  private TextField txtNum1;
  @FXML
  private TextField txtNum2;

  @FXML
  private void addTwoNums(){
    try {
      String fistNum = txtNum1.getText();
      long num1 = Long.parseLong(fistNum);
      String secNum = txtNum2.getText();
      long num2 = Long.parseLong(secNum);
      long answer = num1 + num2;

      lblAnswer.setText(answer+"");
      System.out.printf("Adding num1:%d & num2:%d is %d\n",num1,num2,answer) ;
    }catch (NumberFormatException e){}
  }
}
