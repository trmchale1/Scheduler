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
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.sql.*;
import java.io.IOException;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class CustomerTable implements Initializable {

    @FXML
    private ObservableList<Divisions> divisionsCollection = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Customers> customerCollection = FXCollections.observableArrayList();
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private TableView<Customers> customerTable = new TableView<Customers>();

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        try {
            popCustomers();
            popTable();
            updateModel();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void popTable(){
        customerTable.setItems(customerCollection);
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
            AddCustomer controller = new AddCustomer(this.divisionsCollection,this.customerCollection);

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
            System.out.println(rs);
            while (rs.next()) {
                String division = rs.getString("Division");
                int division_id = rs.getInt("Division_ID");
                int country_id = rs.getInt("Country_ID");
                Divisions d = new Divisions(division_id, division, country_id);
                divisionsCollection.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }


    public void popCustomers() {
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.makeConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * From customers c join first_level_divisions d On c.Division_ID = d.Division_ID join countries co On co.Country_ID = d.Country_ID");
            while(rs.next()){
                    int id = rs.getInt("Customer_ID");
                    String name = rs.getString("Customer_Name");
                    String address = rs.getString("Address");
                    String postal_code = rs.getString("Postal_Code");
                    String phone = rs.getString("Phone");
                    String division = rs.getString("Division");
                    String country = rs.getString("Country");
                    System.out.println(id + " " + name + " " + address + " " + postal_code + " " + phone + " " + division + " " + country);
                    Customers custObject = new Customers(id,name,address,postal_code,phone,division,country);
                    customerCollection.add(custObject);
                }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void dbScript() throws Exception {
        try {
            DBConnection db = new DBConnection();
            Connection conn = db.makeConnection();
            PreparedStatement stmt12 = conn.prepareStatement("INSERT INTO countries VALUES(1,\t'U.S',\tNOW(), 'script', NOW(), 'script');");
/*
            PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM appointments WHERE Appointment_ID < 10000;");
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM customers WHERE Customer_ID < 10000;");
            PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM first_level_divisions where Division_ID < 10000;");
            PreparedStatement stmt4 = conn.prepareStatement("DELETE FROM countries where Country_ID < 10000;");
            PreparedStatement stmt5 = conn.prepareStatement("DELETE FROM users WHERE User_ID < 10000;");
            PreparedStatement stmt6 = conn.prepareStatement("DELETE FROM contacts where Contact_ID < 10000;");
            PreparedStatement stmt7 = conn.prepareStatement("INSERT INTO users VALUES(1, 'test', 'test', NOW(), 'script', NOW(), 'script');");
            PreparedStatement stmt8 = conn.prepareStatement("INSERT INTO users VALUES(2, 'admin', 'admin', NOW(), 'script', NOW(), 'script');");
            PreparedStatement stmt9 = conn.prepareStatement("INSERT INTO contacts VALUES(1,\t'Anika Costa', 'acoasta@company.com');");
            PreparedStatement stmt10 = conn.prepareStatement("INSERT INTO contacts VALUES(2,\t'Daniel Garcia',\t'dgarcia@company.com');");
            PreparedStatement stmt11 = conn.prepareStatement("INSERT INTO contacts VALUES(3,\t'Li Lee',\t'llee@company.com');");
            PreparedStatement stmt12 = conn.prepareStatement("INSERT INTO countries VALUES(1,\t'U.S',\tNOW(), 'script', NOW(), 'script');");
            PreparedStatement stmt13 = conn.prepareStatement("INSERT INTO countries VALUES(2,\t'UK',\tNOW(), 'script', NOW(), 'script');");
            PreparedStatement stmt14 = conn.prepareStatement("INSERT INTO countries VALUES(3,\t'Canada',\tNOW(), 'script', NOW(), 'script');");
            PreparedStatement stmt15 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Alabama', 1, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt16 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Alaska', 54, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt17 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Arizona', 02, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt18 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Arkansas', 03, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt19 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('California', 04, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt20 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Colorado', 05, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt21 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Connecticut', 06, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt22 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Delaware', 07, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt23 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('District of Columbia', 08, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt24 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Florida', 09, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt25 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Georgia', 10, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt26 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Hawaii', 52, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt27 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Idaho', 11, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt28 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Illinois', 12, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt29 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Indiana', 13, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt30 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Iowa', 14, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt31 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Kansas', 15, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt32 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Kentucky', 16, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt33 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Louisiana', 17, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt34 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Maine', 18, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt35 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Maryland', 19, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt36 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Massachusetts', 20, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt37 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Michigan', 21, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt38 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Minnesota', 22, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt39 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Mississippi', 23, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt40 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Missouri', 24, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt41 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Montana', 25, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt42 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Nebraska', 26, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt43 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Nevada', 27, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt44 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('New Hampshire', 28, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt45 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('New Jersey', 29, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt46 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('New Mexico', 30, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt47 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('New York', 31, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt48 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('North Carolina', 32, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt49 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('North Dakota', 33, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt50 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Ohio', 34, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt51 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Oklahoma', 35, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt52 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Oregon', 36, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt53 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Pennsylvania', 37, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt54 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Rhode Island', 38, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt55 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('South Carolina', 39, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt56 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('South Dakota', 40, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt57 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Tennessee', 41, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt58 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Texas', 42, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt59 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Utah', 43, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt60 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Vermont', 44, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt61 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Virginia', 45, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt62 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Washington', 46, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt63 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('West Virginia', 47, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt64 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Wisconsin', 48, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt65 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Wyoming', 49, NOW(), 'script', NOW(), 'script', 1 );");
            PreparedStatement stmt66 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Alberta', 61, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt67 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('British Columbia', 62, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt68 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Manitoba', 63, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt69 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('New Brunswick', 64, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt70 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Newfoundland and Labrador', 72, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt71 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Northwest Territories', 60, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt72 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Nova Scotia', 65, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt73 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Nunavut', 70, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt74 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Ontario', 67, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt75 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Prince Edward Island', 66, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt76 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Québec', 68, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt77 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Saskatchewan', 69, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt78 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Yukon', 71, NOW(), 'script', NOW(), 'script', 3 );");
            PreparedStatement stmt79 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('England', 101, NOW(), 'script', NOW(), 'script', 2 );");
            PreparedStatement stmt80 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Wales', 102, NOW(), 'script', NOW(), 'script', 2 );");
            PreparedStatement stmt81 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Scotland',103, NOW(), 'script', NOW(), 'script', 2 );");
            PreparedStatement stmt82 = conn.prepareStatement("INSERT INTO first_level_divisions(Division, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, COUNTRY_ID) VALUES('Northern Ireland', 104, NOW(), 'script', NOW(), 'script', 2 );");
            PreparedStatement stmt83 = conn.prepareStatement("INSERT INTO customers VALUES(1, 'Daddy Warbucks', '1919 Boardwalk', '01291', '869-908-1875', NOW(), 'script', NOW(), 'script', 29);");
            PreparedStatement stmt84 = conn.prepareStatement("INSERT INTO customers VALUES(2, 'Lady McAnderson', '2 Wonder Way', 'AF19B', '11-445-910-2135', NOW(), 'script', NOW(), 'script', 103);");
            PreparedStatement stmt85 = conn.prepareStatement("INSERT INTO customers VALUES(3, 'Dudley Do-Right', '48 Horse Manor ', '28198', '874-916-2671', NOW(), 'script', NOW(), 'script', 60);");
            PreparedStatement stmt86 = conn.prepareStatement("INSERT INTO appointments VALUES(1, 'title', 'description', 'location', 'Planning Session', '2020-05-28 12:00:00', '2020-05-28 13:00:00', NOW(), 'script', NOW(), 'script', 1, 1, 3);");
            PreparedStatement stmt87 = conn.prepareStatement("INSERT INTO appointments VALUES(2, 'title', 'description', 'location', 'De-Briefing', '2020-05-29 12:00:00', '2020-05-29 13:00:00', NOW(), 'script', NOW(), 'script', 2, 2, 2);");

            stmt1.execute();
            stmt1.close();
            stmt2.execute();
            stmt2.close();
            stmt3.execute();
            stmt3.close();
            stmt4.execute();
            stmt4.close();
            stmt5.execute();
            stmt5.close();
            stmt6.execute();
            stmt6.close();
            stmt7.execute();
            stmt7.close();
            stmt8.execute();
            stmt8.close();
            stmt9.execute();
            stmt9.close();
            stmt10.execute();
            stmt10.close();
            stmt11.execute();
            stmt11.close();
            stmt12.execute();
            stmt12.close();
            stmt13.execute();
            stmt13.close();
            stmt14.execute();
            stmt14.close();
            stmt15.execute();
            stmt15.close();
            stmt16.execute();
            stmt16.close();
            stmt17.execute();
            stmt17.close();
            stmt18.execute();
            stmt18.close();
            stmt19.execute();
            stmt19.close();
            stmt20.execute();
            stmt20.close();
            stmt21.execute();
            stmt21.close();
            stmt22.execute();
            stmt22.close();
            stmt23.execute();
            stmt23.close();
            stmt24.execute();
            stmt24.close();
            stmt25.execute();
            stmt25.close();
            stmt26.execute();
            stmt26.close();
            stmt27.execute();
            stmt27.close();
            stmt28.execute();
            stmt28.close();
            stmt29.execute();
            stmt29.close();
            stmt30.execute();
            stmt30.close();
            stmt31.execute();
            stmt31.close();
            stmt32.execute();
            stmt32.close();
            stmt33.execute();
            stmt33.close();
            stmt34.execute();
            stmt34.close();
            stmt35.execute();
            stmt35.close();
            stmt36.execute();
            stmt36.close();
            stmt37.execute();
            stmt37.close();
            stmt38.execute();
            stmt38.close();
            stmt39.execute();
            stmt39.close();
            stmt40.execute();
            stmt40.close();
            stmt41.execute();
            stmt41.close();
            stmt42.execute();
            stmt42.close();
            stmt43.execute();
            stmt43.close();
            stmt44.execute();
            stmt44.close();
            stmt45.execute();
            stmt45.close();
            stmt46.execute();
            stmt46.close();
            stmt47.execute();
            stmt47.close();
            stmt48.execute();
            stmt48.close();
            stmt49.execute();
            stmt49.close();
            stmt50.execute();
            stmt50.close();
            stmt51.execute();
            stmt51.close();
            stmt52.execute();
            stmt52.close();
            stmt53.execute();
            stmt53.close();
            stmt54.execute();
            stmt54.close();
            stmt55.execute();
            stmt55.close();
            stmt56.execute();
            stmt56.close();
            stmt57.execute();
            stmt57.close();
            stmt58.execute();
            stmt58.close();
            stmt59.execute();
            stmt59.close();
            stmt60.execute();
            stmt60.close();
            stmt61.execute();
            stmt61.close();
            stmt62.execute();
            stmt62.close();
            stmt63.execute();
            stmt63.close();
            stmt64.execute();
            stmt64.close();
            stmt65.execute();
            stmt65.close();
            stmt66.execute();
            stmt66.close();
            stmt67.execute();
            stmt67.close();
            stmt68.execute();
            stmt68.close();
            stmt69.execute();
            stmt69.close();
            stmt70.execute();
            stmt70.close();
            stmt71.execute();
            stmt71.close();
            stmt72.execute();
            stmt72.close();
            stmt73.execute();
            stmt73.close();
            stmt74.execute();
            stmt74.close();
            stmt75.execute();
            stmt75.close();
            stmt76.execute();
            stmt76.close();
            stmt77.execute();
            stmt77.close();
            stmt78.execute();
            stmt78.close();
            stmt79.execute();
            stmt79.close();
            stmt80.execute();
            stmt80.close();
            stmt81.execute();
            stmt81.close();
            stmt82.execute();
            stmt82.close();
            stmt83.execute();
            stmt83.close();
            stmt84.execute();
            stmt84.close();
            stmt85.execute();
            stmt85.close();
            stmt86.execute();
            stmt86.close();
            stmt87.execute();
            stmt87.close();
*/
            stmt12.execute();
            stmt12.close();
            System.out.println("Inserts Successful");
        } catch(Exception e){
            e.printStackTrace();;
        }
    }
}
