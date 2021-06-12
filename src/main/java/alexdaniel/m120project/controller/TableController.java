package alexdaniel.m120project.controller;

import alexdaniel.m120project.model.entity.Loan;
import alexdaniel.m120project.model.repository.LoanRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        movieTitleColumn.setCellValueFactory(cellDataFeature -> new SimpleStringProperty(Optional.of(cellDataFeature.getValue().movie.title).orElse("")));
        // TODO: return date calculation
        expectedReturnDateColumn.setCellValueFactory(cellDataFeature -> new SimpleStringProperty(""));
        // TODO: isLate calculation
        isLateColumn.setCellValueFactory(cellDataFeature -> new SimpleStringProperty(""));
    }

    public void fillTable() {
        var loans = LoanRepository.getNotReturned();
        loanTable.getItems().addAll(loans);
    }

    public void doStuff(ActionEvent actionEvent) {

    }
}

