package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Contact;
import model.ContactManager;

public class ContactManagerView {
    private Stage stage;
    private ContactManager contactManager;
    private ListView<Contact> listView;

    public ContactManagerView(ContactManager contactManager) {
        this.contactManager = contactManager;
        stage = new Stage();
        stage.setTitle("Danh bạ");

        BorderPane root = new BorderPane();
        listView = new ListView<>();
        
        // Cập nhật danh sách liên hệ
        updateContactList();

        Button addButton = new Button("Thêm danh bạ");
        addButton.setOnAction(event -> showAddContactDialog());

        root.setCenter(listView);
        root.setBottom(addButton);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

    private void showAddContactDialog() {
        // Tạo một hộp thoại để nhập thông tin liên hệ mới
        Stage dialog = new Stage();
        dialog.setTitle("Thêm liên hệ");
    
        GridPane grid = new GridPane();
        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField additionalField = new TextField();
        TextField groupField = new TextField();
    
        grid.addRow(0, new javafx.scene.control.Label("Tên đầy đủ:"), nameField);
        grid.addRow(1, new javafx.scene.control.Label("Số điện thoại:"), phoneField);
        grid.addRow(2, new javafx.scene.control.Label("Số điện thoại khác:"), additionalField);
        grid.addRow(3, new javafx.scene.control.Label("Nhóm:"), groupField);
    
        Button saveButton = new Button("Lưu");
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String additionalNumbers = additionalField.getText();
            String group = groupField.getText();
    
            // Kiểm tra xem thông tin đã được nhập đầy đủ chưa
            if (name.isEmpty() || phone.isEmpty()) {
                showAlert("Thông báo", "Tên và số điện thoại không được để trống.");
                return;
            }
    
            // Tạo một liên hệ mới và thêm vào quản lý
            Contact newContact = new Contact(name, phone, additionalNumbers, group);
    
            // Kiểm tra số điện thoại trùng
            if (!contactManager.checkPhoneNumberExists(phone)) {
                contactManager.addContact(newContact);
                updateContactList(); // Cập nhật danh sách liên hệ
                dialog.close(); // Đóng hộp thoại
            } else {
                // Hiển thị thông báo trong ứng dụng khi số điện thoại đã tồn tại
                showAlert("Thông báo", "Số điện thoại đã tồn tại trong danh bạ.");
            }
        });
    
        HBox hBox = new HBox(10, saveButton);
        grid.addRow(4, hBox);
    
        Scene dialogScene = new Scene(grid, 400, 250);
        dialog.setScene(dialogScene);
        dialog.setMinWidth(400);
        dialog.setMinHeight(250);
        dialog.show();
    }
    
    
    private void updateContactList() {
        listView.getItems().clear(); // Xóa danh sách hiện tại
        listView.getItems().addAll(contactManager.getAllContacts()); // Thêm các liên hệ hiện có
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
