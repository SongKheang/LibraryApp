import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./pages/homepage.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Welcome To Library App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}