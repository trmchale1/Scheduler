package sample;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    primaryStage.setTitle("Scheduler");
    FXMLLoader loader = new FXMLLoader();
    URL xmlUrl = getClass().getResource("sample.fxml");
    loader.setLocation(xmlUrl);
    MainController controller = loader.getController();
    loader.setController(controller);
    Parent root = loader.load();
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
