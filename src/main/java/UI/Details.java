package UI;

import instanceCreator.InstanceHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Details implements Initializable {
    private List listHandler = InstanceHandler.getRunningInstances();
    public ListView instanceList;
    public Button newInstance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instanceList.setItems(FXCollections.observableList(listHandler));
    }

    public void configLoader(ActionEvent event) throws IOException {
        Parent configLoader = FXMLLoader.load(getClass().getClassLoader().getResource("config.fxml"));
        Scene config = new Scene(configLoader);
        Stage primaryStage = (Stage) newInstance.getScene().getWindow();
        primaryStage.setScene(config);
        primaryStage.show();
    }
    public void refreshList(ActionEvent event){
        listHandler = InstanceHandler.getRunningInstances();
        instanceList.setItems(FXCollections.observableList(listHandler));
    }
    public String getInstanceName(){
        return instanceList.getAccessibleText();
    }
    public void stopInstance(){
        System.out.println(instanceList.getAccessibleText());
    }
}
