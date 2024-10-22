package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Contact;
import model.ContactManager;

public class MainAppView {
    private Stage stage;
    private ContactManager contactManager;
    private ListView<Contact> contactListView;
    private TextField searchField;

    public MainAppView(ContactManager contactManager) {
        this.contactManager = contactManager;
        stage = new Stage();
        stage.setTitle("Quản lý danh bạ điện thoại");

        contactListView = new ListView<>();
        searchField = new TextField();
        searchField.setPromptText("Tìm kiếm theo tên hoặc số điện thoại...");

        Button addButton = new Button("Thêm liên hệ");
        Button editButton = new Button("Sửa liên hệ");
        Button deleteButton = new Button("Xóa liên hệ");
        Button viewButton = new Button("Xem chi tiết");

        addButton.setOnAction(e -> showAddContactDialog());
        editButton.setOnAction(e -> showEditContactDialog());
        deleteButton.setOnAction(e -> deleteSelectedContact());
        viewButton.setOnAction(e -> showContactDetail());
        searchField.setOnKeyReleased(e -> searchContacts());

        VBox buttonLayout = new VBox(10, searchField, addButton, editButton, deleteButton, viewButton);
        BorderPane root = new BorderPane();
        root.setCenter(contactListView);
        root.setRight(buttonLayout);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        refreshContactList();
    }

    public void show() {
        stage.show();
    }

    private void showAddContactDialog() {
        ContactView contactView = new ContactView(new Contact("", "", "", ""), contactManager, this, "Thêm liên hệ");
        contactView.show();
    }

    private void showEditContactDialog() {
        Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            ContactView contactView = new ContactView(selectedContact, contactManager, this, "Sửa liên hệ");
            contactView.show();
        }
    }

    private void deleteSelectedContact() {
        Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            contactManager.removeContact(selectedContact);
            refreshContactList();
        }
    }

    private void showContactDetail() {
        Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            ContactDetailView contactDetailView = new ContactDetailView(selectedContact);
            contactDetailView.show();
        }
    }

    private void searchContacts() {
        String query = searchField.getText();
        contactListView.getItems().clear();
        if (query.isEmpty()) {
            contactListView.getItems().addAll(contactManager.getAllContacts());
        } else {
            contactListView.getItems().addAll(contactManager.searchByName(query));
        }
    }

    public void refreshContactList() {
        contactListView.getItems().clear();
        contactListView.getItems().addAll(contactManager.getAllContacts());
    }
}
