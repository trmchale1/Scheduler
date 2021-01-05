package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {
    @FXML
    private ObservableList<String> divisions = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox choice_div;
    @FXML
    private ChoiceBox choice_count;

    public AddCustomer(ObservableList<String> divisions){
        this.divisions = divisions;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        test();
    }

    public void test(){
        choice_div.setItems(divisions);
        choice_count.setItems(FXCollections.observableArrayList("Canada","United Kingdom","United States"));
    }
}
