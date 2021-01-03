package sample;


import javafx.fxml.Initializable;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import java.sql.*;
import java.io.IOException;
public class CustomerTable implements Initializable {

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
            System.out.println("***** Begin Update Customer Table *****");
            DBConnection db = new DBConnection();
            Connection conn = db.makeConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * From countries c join first_level_divisions d on c.Country_ID = d.Country_ID");

            if (rs.next() == false) {
                System.out.println("Table empty.");
            } else {
                System.out.println("Table not empty.");
                while(rs.next()) {
                    int country_id = rs.getInt("Country_ID");
                    String country = rs.getString("Country");
                    int division_id = rs.getInt("Division_ID");
                    String division = rs.getString("Division");

                    System.out.println("Country ID: " + country_id + " Country: " + country );
                    System.out.println("Division ID: " + division_id + " Division: " + division);
                }
            }

            System.out.println("***** End Update Customer Table *****");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception x) {

        }
    }

}
