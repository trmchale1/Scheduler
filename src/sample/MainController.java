package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {
    @FXML
    private ObservableList<Customers> customerCollection = FXCollections.observableArrayList();
    @FXML
    private Button mainButton;
    @FXML
    private TextField user_field;
    @FXML
    private TextField pw_field;
    @FXML
    private AnchorPane ap;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextField user, pw;
    }
    @FXML
    public void buttonClicked() {
        System.out.println("New user logged in");
        String u = user_field.getText().trim();
        String p = pw_field.getText().trim();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/CustomerTable.fxml"));
            CustomerTable controller = new CustomerTable();
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) user_field.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
