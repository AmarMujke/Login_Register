package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
public class RegisterController {

    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confirmTextField;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Label registerLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Label regLabel;

    public void registerOnAction(ActionEvent event){

        if (passwordTextField.getText().equals(confirmTextField.getText())){
            registerUser();
            passLabel.setText("");

        }else{
            passLabel.setText("Password Does not match");
        }


    }

    public void cancelBtnOnAction(ActionEvent event){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser(){
        dbConnection connectNow = new dbConnection();
        Connection connectDB = connectNow.getDbConnection();

        String firstName = firstnameTextField.getText();
        String lastName = lastNameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        String insertFields = "INSERT INTO user_account (lastname,firstname,username,password) VALUES ('";
        String insertValues = firstName + "','" + lastName + "','" + username + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            regLabel.setText("User Registered Successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

}
