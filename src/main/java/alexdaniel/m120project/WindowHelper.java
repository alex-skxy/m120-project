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


    public void openCreateStage(Stage previousStage) throws IOException {
        Stage stage = FXMLLoader.load(getClass().getResource("view/createdisplay.fxml"));
        stage.setY(previousStage.getY());
        stage.setX(previousStage.getX());
        stage.show();
    }

    public void openTableStage(Stage previousStage) throws IOException {
        Stage stage = FXMLLoader.load(getClass().getResource("view/tabledisplay.fxml"));
        stage.setY(previousStage.getY());
        stage.setX(previousStage.getX());
        stage.show();
    }

    public void openEditStage(Stage previousStage) throws IOException {
        Stage stage = FXMLLoader.load(getClass().getResource("view/editdisplay.fxml"));
        stage.setY(previousStage.getY());
        stage.setX(previousStage.getX());
        stage.show();
    }
}
