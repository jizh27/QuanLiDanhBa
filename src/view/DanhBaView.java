package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DanhBaView {
    public void showDanhBaView(Stage primaryStage) {
        primaryStage.setTitle("Danh Bạ");

        Label label = new Label("Danh sách liên hệ");

        VBox vbox = new VBox(label);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
