package sample;


import instanceCreator.Credentials;
import instanceCreator.InstanceHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class indexController {
    @FXML
    public TextField awsacc;
    public TextField awssec;
    public Button next;
    public indexController() throws IOException {
    }

    public String getAwsSecureKey() {
        return Credentials.AWS_SECRET_ACCESS_KEY;
    }

    public void setAwsSecureKey() {
        Credentials.AWS_SECRET_ACCESS_KEY = awssec.getText();
    }

    public String getAwsAccessKey() {
        return Credentials.AWS_ACCESS_KEY_ID;
    }

    public void setAwsAccessKey() {
        Credentials.AWS_ACCESS_KEY_ID = awsacc.getText();
    }

    public void startInstance(ActionEvent event) throws IOException{
        setAwsAccessKey();
        setAwsSecureKey();
        InstanceHandler.instance();
    }


    public void changeScene(ActionEvent event) throws IOException {
        Parent configLoader = FXMLLoader.load(getClass().getClassLoader().getResource("index.fxml"));
        Scene config = new Scene(configLoader);
        Stage primaryStage = (Stage) next.getScene().getWindow();
        primaryStage.setScene(config);
        primaryStage.show();
    }
}

