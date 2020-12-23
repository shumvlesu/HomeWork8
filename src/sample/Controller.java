package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controller {

  final static int COUNT_OF_ATTEMPTS = 3;
  static int count;
  final static int SIZE = 10;
  private static int hiddenNumber;

  @FXML
  private TextField numberInputField;

  @FXML
  private Label messageToUser;

  @FXML
  public void initialize() {
    createHiddenNumber();
    messageToUser.setText("Привет! Угадай число от 0 до " + (SIZE - 1) + "\nУ тебя есть " + (COUNT_OF_ATTEMPTS) + " попытки!");
  }

  //Обработчик события
  @FXML
  void guess() {

    StringBuilder sb = new StringBuilder();
    sb.append("Число от 0 до ").append((SIZE - 1)).append("\n");
    //for (int i = 0; i < COUNT_OF_ATTEMPTS; i++) {
    String userMessage = numberInputField.getText();
    numberInputField.clear();
    messageToUser.setText(sb.toString());
    if (!userMessage.isBlank()) {

      try {
        int userNumber = Integer.parseInt(userMessage);

        if (userNumber == hiddenNumber) {
          sb.append("Вы выиграли! Это действительно - ").append(hiddenNumber).append("\nЯ загадал новое число");
          createHiddenNumber();
          count = 0;
          messageToUser.setText(sb.toString());
          return;
        } else if (userNumber >= SIZE) {
          sb.append("Эй, это число должно быть \nбольше 0 и меньше " + SIZE);
        } else {
          sb.append("Ваше число ").append(userNumber > hiddenNumber ? "больше" : "меньше");
        }
        messageToUser.setText(sb.toString());
        count++;
      } catch (NumberFormatException e) {
        e.printStackTrace();
        var alert = new Alert(Alert.AlertType.ERROR, "Введите число!");
        alert.setTitle("Ошибка ввода данных");
        alert.show();
      }


    }

    if (count == COUNT_OF_ATTEMPTS) {
      messageToUser.setText("Вы проиграли :(. Но я заново загадал число! :)");
      createHiddenNumber();
      count = 0;
    }

  }


  private static void createHiddenNumber() {
    hiddenNumber = (int) (Math.random() * SIZE);
  }


}

