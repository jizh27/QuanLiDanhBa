package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import controler.Controler;
import model.UserDao;

public class LoginDialog {
    private boolean loggedIn = false;
    private UserDao userDao; // Thêm thuộc tính UserDao

    public LoginDialog(Stage owner, Controler controller) {
        userDao = new UserDao(); // Khởi tạo UserDao
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setTitle("Đăng nhập");

        // Tạo giao diện cho hộp thoại đăng nhập
        Label userLabel = new Label("Tên đăng nhập:");
        TextField userField = new TextField();
        Label passwordLabel = new Label("Mật khẩu:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Đăng nhập");
        Button cancelButton = new Button("Hủy");

        loginButton.setOnAction(e -> {
            // Sử dụng UserDao để kiểm tra đăng nhập
            if (userDao.checkLogin(userField.getText(), passwordField.getText())) {
                loggedIn = true;
                dialog.close();
            } else {
                // Xử lý đăng nhập không thành công
                System.out.println("Đăng nhập thất bại");
            }
        });

        cancelButton.setOnAction(e -> {
            dialog.close();
        });

        VBox layout = new VBox(10, userLabel, userField, passwordLabel, passwordField, loginButton, cancelButton);
        Scene scene = new Scene(layout, 300, 200);
        dialog.setScene(scene);
        dialog.showAndWait(); // Chờ cho đến khi người dùng đóng hộp thoại
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
