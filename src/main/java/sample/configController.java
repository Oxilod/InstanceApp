package sample;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class configController implements Initializable{
    public ChoiceBox<String> dropDown;
    public void initialize(URL url, ResourceBundle rb) {
        dropDown.setItems(FXCollections.observableArrayList("ami-0fc85a60", "ami-f573e19a", "ami-7215851d", "ami-194cdc76", "ami-af79ebc0"));
    }
}
