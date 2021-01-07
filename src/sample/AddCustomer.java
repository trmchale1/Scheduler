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
import javafx.event.Event;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import java.util.Optional;



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
    private ObservableList<Divisions> divisionCollection = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> uk = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> can = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> us = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Customers> customerCollection = FXCollections.observableArrayList();


    @FXML
    private ChoiceBox choice_div;
    @FXML
    private ChoiceBox choice_count;

    @FXML
    private TextField customer_id = new TextField();
    @FXML
    private TextField customer_name = new TextField();
    @FXML
    private TextField address = new TextField();
    @FXML
    private TextField postal_code = new TextField();
    @FXML
    private TextField phone = new TextField();



    public AddCustomer(ObservableList<Divisions> divisions,ObservableList<Customers> customers){
        this.divisionCollection = divisions;
        this.customerCollection = customers;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRegions();
        resetFields();
    }

    public void setRegions(){
        for(Divisions d : divisionCollection){
           int div_id =  d.getDivision_id();
           String div = d.getDivision();
           int c_id = d.getCountry_id();
           if(c_id == 1){
               us.add(div);
           } else if (c_id == 2){
               uk.add(div);
           } else if (c_id == 3){
               can.add(div);
           }
        }
        for(String u: can)
        choice_count.setItems(FXCollections.observableArrayList("Canada","United Kingdom","United States"));
        choice_count.setOnAction((Event) -> {
            int index = choice_count.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            if(index == 0){
                choice_div.setItems(can);
            } else if (index == 1) {
                choice_div.setItems(uk);
            } else if(index == 2){
                choice_div.setItems(us);
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

    private void resetFields() {
        try {
            customer_name.setText("Customer Name");
            address.setText("Address");
            postal_code.setText("Postal Code");
            phone.setText("Phone");
            int size = customerCollection.size() + 1;
            customer_id.setText(String.valueOf(size));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void CustomerTable(Event event){
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

    @FXML
    private void saveCustomer(MouseEvent event){
        System.out.println(choice_div.getSelectionModel().getSelectedItem());
        try {
            int id = Integer.parseInt(customer_id.getText().trim());
            String name = customer_name.getText().trim();
            String add_temp = address.getText().trim();
            String postal_code_temp = postal_code.getText().trim();
            String phone_temp = phone.getText().trim();
            String div_temp = (String) choice_div.getValue();
            String country_temp = (String) choice_count.getValue();
            Customers custObj = new Customers(id,name,add_temp,postal_code_temp,phone_temp,div_temp,country_temp);
            customerCollection.add(custObj);
        } catch (Exception e){
            e.printStackTrace();
        }
        CustomerTable(event);
    }

}

