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
import java.util.Calendar;
import java.util.Date;
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

        // TODO: return date calculation
        expectedReturnDateColumn.setCellValueFactory(
                cellDataFeature -> new SimpleStringProperty(
                        dateFormatter.format(calculateReturnDate(
                                cellDataFeature.getValue().date,
                                cellDataFeature.getValue().membership.extraDays + 30).getTime()
                        )
                ));
        // TODO: isLate calculation
        isLateColumn.setCellValueFactory(
                cellDataFeature -> new SimpleStringProperty(isLate(calculateReturnDate(
                        cellDataFeature.getValue().date,
                        cellDataFeature.getValue().membership.extraDays + 30)) ? "late" : "not late")
        );
    }

    private Boolean isLate(Calendar returnDate) {
        var today = Calendar.getInstance();
        today.setTime(new Date());

        return returnDate.compareTo(today) < 0;
    }

    private Calendar calculateReturnDate(Date loanDate, int extraDays) {
        var calendar = Calendar.getInstance();
        calendar.setTime(loanDate);
        calendar.add(Calendar.DAY_OF_MONTH, extraDays);

        return calendar;
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

