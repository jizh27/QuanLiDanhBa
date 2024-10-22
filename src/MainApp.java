import javafx.application.Application;
import javafx.stage.Stage;
import controler.Controler;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Controler controler = new Controler();
        controler.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
