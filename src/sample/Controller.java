package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controller {

  static int COUNT_OF_ATTEMPTS = 3;
  final static int SIZE = 10;
  private static int hiddenNumber;


  /*@FXML
  private Button myButton;*/

  @FXML
  private TextField numberInputField;

  @FXML
  private Label messageToUser;

  @FXML
  public void initialize() {
    createHiddenNumber();
    messageToUser.setText("Привет! Угадай число от 0 до " + String.valueOf(SIZE - 1) + "\nУ тебя есть " + String.valueOf(COUNT_OF_ATTEMPTS) + " попытки!");
  }

  //Обработчик события
  @FXML
  void guess() {

    StringBuilder sb = new StringBuilder();
    sb.append("Число от 0 до " + String.valueOf(SIZE - 1)+"\n");
    //for (int i = 0; i < COUNT_OF_ATTEMPTS; i++) {
    String userMessage = numberInputField.getText();
    numberInputField.clear();
    messageToUser.setText(sb.toString());
    if (!userMessage.isBlank()) {

      try {
        int userNumber = Integer.parseInt(userMessage);

        if (userNumber == hiddenNumber) {
          sb.append("Вы выиграли! Это действительно - " + hiddenNumber + "\nЯ загадал новое число");
          createHiddenNumber();
        } else if (userNumber >= SIZE) {
          sb.append("Эй, это число должно быть \nбольше 0 и меньше " + SIZE);
        } else {
          sb.append("Ваше число " + (userNumber > hiddenNumber ? "больше" : "меньше"));
        }
        messageToUser.setText(sb.toString());

      } catch (NumberFormatException e) {
        e.printStackTrace();
        var alert = new Alert(Alert.AlertType.ERROR, "Введите число!");
        alert.setTitle("Ошибка ввода данных");
        alert.show();
      }

    }

  }


  private static void createHiddenNumber() {
    hiddenNumber = (int) (Math.random() * SIZE);
  }


}

