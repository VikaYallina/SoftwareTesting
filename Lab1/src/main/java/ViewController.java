import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ViewController {
    @FXML
    Button button;
    @FXML
    TextField loginTextField;
    @FXML
    PasswordField passwordField;

    public static boolean isTextFieldEmpty(String text){
        return text.trim().isEmpty();
    }

    public static boolean validate(String login, String password){
        return (login.equals("loginEX")) && (password.equals("pass123"));
    }

    @FXML
    public void btnAction(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (isTextFieldEmpty(loginTextField.getText()) || isTextFieldEmpty(passwordField.getText()))
            alert.setContentText("Field is empty");
        else{
            if (validate(loginTextField.getText(), passwordField.getText())) {
                alert.setContentText("Correct");
            }else{
                alert.setContentText("Fail");
            }
        }
        alert.showAndWait();
    }
}
