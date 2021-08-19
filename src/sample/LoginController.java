package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button cancelBtn;
    @FXML
    private Label invalidLogin;
    @FXML
    private ImageView mainImage;
    @FXML
    private ImageView lockImage;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File imageFile = new File("Images/loginPic.jpeg");
        Image mainImagePic = new Image(imageFile.toURI().toString());
        mainImage.setImage(mainImagePic);

        File lockFile = new File("Images/lock.jpeg");
        Image lockImagePic = new Image(lockFile.toURI().toString());
        lockImage.setImage(lockImagePic);
    }

    public void invalidLoginMessage(ActionEvent event){

        if (usernameTextField.getText().isEmpty() == false && passwordTextField.getText().isEmpty() == false){

            validateLogin();

        }else {
            invalidLogin.setText("Please enter Username and Password.");
        }
    }
    

    public void cancelBtnOnAction(ActionEvent event){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        dbConnection connectNow = new dbConnection();
        Connection connectDB = connectNow.getDbConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText() +
                "' AND password = '" + passwordTextField.getText() + "'";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet querryResult = statement.executeQuery(verifyLogin);

            while (querryResult.next()){
                if (querryResult.getInt(1) == 1){
                    //invalidLogin.setText("Welcome");
                    createAccForm();
                }else {
                    invalidLogin.setText("Invalid login. Please try again.");
                }
            }


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void createAccForm(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 500));
            registerStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
