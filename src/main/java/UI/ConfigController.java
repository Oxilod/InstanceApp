package UI;

import com.amazonaws.services.ec2.model.AmazonEC2Exception;
import instanceCreator.InstanceHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {
    public static String selectedImageID;
    public static String selectedInstanceType;
    public static String selectedKeyName;
    public static String selectedGroupName;
    public ChoiceBox<String> imageIDD;
    public ChoiceBox<String> instanceTypeDD;
    public Button runInstance;
    public TextField keyName;
    public TextField groupName;
    public Label detailsCheck;

    public void initialize(URL url, ResourceBundle rb) {
        imageIDD.setItems(FXCollections.observableArrayList("ami-0fc85a60",
                "ami-f573e19a",
                "ami-7215851d",
                "ami-194cdc76",
                "ami-af79ebc0"));
        instanceTypeDD.setItems(FXCollections.observableArrayList("t2.nano",
                "t2.micro",
                "t2.small",
                "t2.medium",
                "t2.large",
                "t2.xlarge",
                "t2.2xlarge"));
        imageIDD.getSelectionModel().selectFirst();
        instanceTypeDD.getSelectionModel().selectFirst();
    }

    private void setSelectedImageId() {
        selectedImageID = imageIDD.getValue();
    }

    private void setSelectedInstanceType() {
        selectedInstanceType = instanceTypeDD.getValue();
    }

    private void setSelectedKeyName() {
        selectedKeyName = keyName.getText();
    }

    private void setSelectedGroupName() {
        selectedGroupName = groupName.getText();
    }

    public void runInstance(ActionEvent event) throws IOException {
        setSelectedImageId();
        setSelectedInstanceType();
        setSelectedKeyName();
        setSelectedGroupName();
        try {
            InstanceHandler.instance();
            Parent configLoader = FXMLLoader.load(getClass().getClassLoader().getResource("details.fxml"));
            Scene config = new Scene(configLoader);
            Stage primaryStage = (Stage) runInstance.getScene().getWindow();
            primaryStage.setScene(config);
            primaryStage.show();
        } catch (AmazonEC2Exception e) {
            IndexController.errorDisplay.setText("Please check your credentials and configuration and try again");
            Parent goBack = FXMLLoader.load(getClass().getClassLoader().getResource("index.fxml"));
            Scene goBackScene = new Scene(goBack);
            Stage primaryStage = (Stage) runInstance.getScene().getWindow();
            primaryStage.setScene(goBackScene);
            primaryStage.show();
        }
    }
}
