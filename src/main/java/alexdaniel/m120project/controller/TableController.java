package alexdaniel.m120project.controller;

import alexdaniel.m120project.WindowHelper;
import alexdaniel.m120project.model.entity.Loan;
import alexdaniel.m120project.model.repository.LoanRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    public TextField searchBar;

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

    public void fillTable(String searchString) {
        var loans = LoanRepository.getNotReturned();
        List<Loan> foundLoans = new ArrayList<>();
        var dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        for (Loan loan : loans) {
            var date = dateFormatter.format(loan.calculateReturnDate().getTime()).toLowerCase();
            var name = loan.getName().toLowerCase();
            var movie = loan.getMovie().getTitle().toLowerCase();
            var isLate = (loan.isLate() ? "late" : "not late").toLowerCase();
            searchString = searchString.toLowerCase();
            if (name.contains(searchString) || movie.contains(searchString) || date.contains(searchString) || isLate.contains(searchString)) {
                foundLoans.add(loan);
            }
        }
        loanTable.getItems().addAll(foundLoans);
    }

    public void search() {
        loanTable.getItems().clear();
        fillTable(searchBar.getText());
    }

    public void edit(ActionEvent actionEvent) throws IOException {
        var selectedLoan = loanTable.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        if (selectedLoan != null) {
            var editController = WindowHelper.getWindowHelper().openEditStage(stage);
            editController.setLoan(selectedLoan);
            stage.hide();
        } else {
            WindowHelper.showAlert(Alert.AlertType.WARNING, stage, "Warning", "Must select a loan on the table to edit!");
        }
    }

    public void create(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        WindowHelper.getWindowHelper().openCreateStage(stage);
        stage.hide();
    }
}

