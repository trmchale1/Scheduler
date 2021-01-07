package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/*
1.) Share country and division data across controllers
check
2.) When the user picks a country show only those divisions
check
2.1) run sql script
check
2.2) Write code for save customer and exit screen in AddCustomer
2.3) Write code for edit, delete customer in ViewCustomer
2.4) Do we write to the database?
5.) Start writing the scheduling portion of the app
6.) Delete Search Feature in View Cust
*/
public class AddCustomer implements Initializable {
    @FXML
    private ObservableList<Divisions> divisions = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> uk = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> can = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> us = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Customers> customers = FXCollections.observableArrayList();


    @FXML
    private ChoiceBox choice_div;
    @FXML
    private ChoiceBox choice_count;

    @FXML
    private TextField customer_id;
    @FXML
    private TextField customer_name;
    @FXML
    private TextField address;
    @FXML
    private TextField postal_code;
    @FXML
    private TextField phone;



    public AddCustomer(ObservableList<Divisions> divisions,ObservableList<Customers> customers){
        this.divisions = divisions;
        this.customers = customers;
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

    @FXML
    private void cancelAddCustomer(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Are you sure you want to cancel?");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        CustomerTable(event);
    }

    private void resetFieldsStyle() {
        customer_name.setStyle("-fx-border-color: lightgray");
        address.setStyle("-fx-border-color: lightgray");
        postal_code.setStyle("-fx-border-color: lightgray");
        phone.setStyle("-fx-border-color: lightgray");
    }

    private void resetFields() {
        customer_name.setText("Customer Name");
        address.setText("Address");
        postal_code.setText("Address");
        phone.setText("Phone");

    }

    private void CustomerTable(MouseEvent event){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerTable.fxml"));
        CustomerTable controller = new CustomerTable();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    } }

}

