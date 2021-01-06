package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;

/*
1.) Share country and division data across controllers
check
2.) When the user picks a country show only those divisions
check
3.) Write a customer to Database

4.) Populate the table view in view customers
5.) Start writing the scheduling portion of the app
*/
public class AddCustomer implements Initializable {
    @FXML
    private ObservableList<Divisions> divisions = FXCollections.observableArrayList();
    private ObservableList<String> uk = FXCollections.observableArrayList();
    private ObservableList<String> can = FXCollections.observableArrayList();
    private ObservableList<String> us = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox choice_div;
    @FXML
    private ChoiceBox choice_count;


    public AddCustomer(ObservableList<Divisions> divisions){
        this.divisions = divisions;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRegions();
    }

    public void setRegions(){
        for(Divisions d : divisions){
           int div_id =  d.getDivision_id();
           String div = d.getDivision();
           int c_id = d.getCountry_id();
           System.out.println("Division ID: " + div_id + " Division: " + div + " Country ID " + c_id);
           if(c_id == 38){
               can.add(div);
           } else if (c_id == 230){
               uk.add(div);
           } else{
               us.add(div);
           }
        }

        choice_count.setItems(FXCollections.observableArrayList("Canada","United Kingdom","United States"));
        choice_count.setOnAction((Event) -> {
            int index = choice_count.getSelectionModel().getSelectedIndex();
            if(index == 1){
                choice_div.setItems(uk);
            } else if (index == 2) {
                choice_div.setItems(us);
            } else {
                choice_div.setItems(can);
            }
        });

    }


}

