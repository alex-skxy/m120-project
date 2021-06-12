package alexdaniel.m120project.controller;

import alexdaniel.m120project.model.entity.Loan;
import alexdaniel.m120project.model.repository.LoanRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController {
    @FXML
    public TableView<Loan> loanTable;

    public TableController() {
        TableColumn<Loan, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //TODO make columns
        /*TableColumn<Loan, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Loan, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        */
        /*TableColumn<Loan, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        */
    }

    public void fillTable() {
        var loans = LoanRepository.getNotReturned();
        loanTable.getItems().addAll(loans);
    }

    public void doStuff(ActionEvent actionEvent) {

    }
}

