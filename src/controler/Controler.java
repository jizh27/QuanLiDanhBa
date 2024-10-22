package controler;

import javafx.stage.Stage;
import model.ContactManager;
import view.LoginDialog;
import view.MainAppView;

public class Controler {
    public void start(Stage primaryStage) {
        ContactManager contactManager = new ContactManager();
        LoginDialog loginDialog = new LoginDialog(primaryStage, this);

        if (loginDialog.isLoggedIn()) {
            MainAppView mainAppView = new MainAppView(contactManager);
            mainAppView.show();
        } else {
            primaryStage.close();
        }
    }
}
