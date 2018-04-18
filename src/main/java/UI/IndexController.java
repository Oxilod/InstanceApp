package UI;


import instanceCreator.Credentials;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class IndexController {
    @FXML
    public TextField awsacc;
    public TextField awssec;
    public Button next;
    public static Label errorDisplay;


    private void setAwsSecureKey() {
        Credentials.AWS_SECRET_ACCESS_KEY = awssec.getText();
    }


    private void setAwsAccessKey() {
        Credentials.AWS_ACCESS_KEY_ID = awsacc.getText();
    }


    public void changeScene(ActionEvent event) throws IOException {
        setAwsAccessKey();
        setAwsSecureKey();
        Parent configLoader = FXMLLoader.load(getClass().getClassLoader().getResource("config.fxml"));
        Scene config = new Scene(configLoader);
        Stage primaryStage = (Stage) next.getScene().getWindow();
        primaryStage.setScene(config);
        primaryStage.show();
    }
}

