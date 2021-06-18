package alexdaniel.m120project.controller;

import alexdaniel.m120project.WindowHelper;
import alexdaniel.m120project.model.entity.Loan;
import alexdaniel.m120project.model.repository.LoanRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class TableController {
    @FXML
    public TableView<Loan> loanTable;
    @FXML
    public TableColumn<Loan, String> nameColumn;
    @FXML
    public TableColumn<Loan, String> movieTitleColumn;
    @FXML
    public TableColumn<Loan, String> expectedReturnDateColumn;
    @FXML
    public TableColumn<Loan, String> isLateColumn;

    @FXML
    public void initialize() {
        fillTable();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        movieTitleColumn.setCellValueFactory(
                cellDataFeature -> new SimpleStringProperty(Optional.of(cellDataFeature.getValue().movie.title).orElse(""))
        );

        var dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

        expectedReturnDateColumn.setCellValueFactory(
                cellDataFeature -> new SimpleStringProperty(
                        dateFormatter.format(cellDataFeature.getValue().calculateReturnDate().getTime()
                        )
                ));
        isLateColumn.setCellValueFactory(
                cellDataFeature -> new SimpleStringProperty(cellDataFeature.getValue().isLate() ? "late" : "not late")
        );
    }

    public void fillTable() {
        var loans = LoanRepository.getNotReturned();
        loanTable.getItems().addAll(loans);
    }

    public void doStuff(ActionEvent actionEvent) {

    }

    public void create(ActionEvent actionEvent) throws IOException {
        WindowHelper.getWindowHelper().openCreateStage();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
    }
}

