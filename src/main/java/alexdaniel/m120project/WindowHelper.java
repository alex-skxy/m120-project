package alexdaniel.m120project;

import alexdaniel.m120project.controller.EditController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;

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

    public EditController openEditStage(Stage previousStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/editdisplay.fxml"));
        Stage stage = loader.load();
        EditController editController = loader.<EditController>getController();
        stage.setY(previousStage.getY());
        stage.setX(previousStage.getX());
        stage.show();
        return editController;
    }

    public static boolean showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.initOwner(owner);
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }
}
