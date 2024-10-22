package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Contact;
import model.ContactManager;

public class ContactView {
    private Contact contact;
    private ContactManager contactManager;
    private MainAppView mainAppView;
    private String dialogTitle;

    public ContactView(Contact contact, ContactManager contactManager, MainAppView mainAppView, String dialogTitle) {
        this.contact = contact;
        this.contactManager = contactManager;
        this.mainAppView = mainAppView;
        this.dialogTitle = dialogTitle;
    }

    public void show() {
        Stage dialog = new Stage();
        dialog.setTitle(dialogTitle);

        GridPane grid = new GridPane();
        TextField nameField = new TextField(contact.getFullName());
        TextField phoneField = new TextField(contact.getPhoneNumber());
        TextField additionalField = new TextField(contact.getAdditionalNumbers());
        TextField groupField = new TextField(contact.getGroup());

        grid.addRow(0, new Label("Họ và tên:"), nameField);
        grid.addRow(1, new Label("Số điện thoại:"), phoneField);
        grid.addRow(2, new Label("Thông tin khác:"), additionalField);
        grid.addRow(3, new Label("Nhóm:"), groupField);

        Button saveButton = new Button("Lưu");
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String additionalNumbers = additionalField.getText();
            String group = groupField.getText();

            if (dialogTitle.equals("Thêm liên hệ")) {
                Contact newContact = new Contact(name, phone, additionalNumbers, group);
                contactManager.addContact(newContact);
            } else {
                Contact updatedContact = new Contact(name, phone, additionalNumbers, group);
                contactManager.updateContact(contact, updatedContact);
            }

            mainAppView.refreshContactList();
            dialog.close();
        });

        grid.addRow(4, saveButton);

        Scene scene = new Scene(grid, 400, 250);
        dialog.setScene(scene);
        dialog.show();
    }
}
