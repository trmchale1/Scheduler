package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.sql.*;
import java.io.IOException;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class CustomerTable implements Initializable {

    @FXML
    private ObservableList<Divisions> divisions = FXCollections.observableArrayList();

    @FXML
    private BorderPane mainBorderPane;

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        updateModel();

    }

    @FXML
    private void handleShowView(ActionEvent e) {
        String view = (String) ((Node)e.getSource()).getUserData();
        loadFXML(getClass().getResource(view));
    }

    @FXML
    private void acHandleShowView(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
            AddCustomer controller = new AddCustomer(this.divisions);

            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch(IOException exception){
            exception.printStackTrace();
        }
    }

    private void loadFXML(URL url) {
        try {
            FXMLLoader loader = new FXMLLoader(url);
            mainBorderPane.setCenter(loader.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateModel() {
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.makeConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * From countries c join first_level_divisions d on c.Country_ID = d.Country_ID");
            if (rs.next() == false) {
                System.out.println("Table empty.");
            } else {
                System.out.println("Table not empty.");
                while(rs.next()) {
                    String division = rs.getString("Division");
                    int division_id = rs.getInt("Division_ID");
                    int country_id = rs.getInt("Country_ID");
                    Divisions d = new Divisions(division_id,division,country_id);
                    divisions.add(d);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception x) {

        }
    }
}
