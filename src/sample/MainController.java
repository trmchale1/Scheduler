package sample;

import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {

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
        // Test the input
        System.out.println(u);
        System.out.println(p);

        // Transition to the next screen

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
        // Yay!! We see the Address Book
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
