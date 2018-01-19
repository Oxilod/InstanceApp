package UI;

import instanceCreator.InstanceHandler;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Details implements Initializable {
    private static List listHandler = InstanceHandler.getRunningInstances();
    public ListView instanceList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instanceList.setItems(FXCollections.observableList(listHandler));
    }
}
