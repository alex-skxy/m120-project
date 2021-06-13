package alexdaniel.m120project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowHelper {
    private static final WindowHelper windowHelper = new WindowHelper();

    private WindowHelper() {
    }

    public static WindowHelper getWindowHelper() {
        return windowHelper;
    }


    public void openCreateStage() throws IOException {
        Stage stage = FXMLLoader.load(getClass().getResource("view/createdisplay.fxml"));
        stage.show();
    }

    public void openTableStage() throws IOException {
        Stage stage = FXMLLoader.load(getClass().getResource("view/tabledisplay.fxml"));
        stage.show();
    }
}
