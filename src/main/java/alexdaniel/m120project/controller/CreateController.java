package alexdaniel.m120project.controller;

import alexdaniel.m120project.WindowHelper;
import alexdaniel.m120project.model.entity.Loan;
import alexdaniel.m120project.model.entity.Membership;
import alexdaniel.m120project.model.repository.LoanRepository;
import alexdaniel.m120project.model.repository.MembershipRepository;
import alexdaniel.m120project.model.repository.MovieRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class CreateController {
    private Loan loan;

    @FXML
    public TextField nameField;
    @FXML
    public TextField movieField;
    @FXML
    public ComboBox<Membership> membershipComboBox;
    @FXML
    public DatePicker loanUntilField;
    @FXML
    public Button createButton;
    @FXML
    public Button cancelButton;

    @FXML
    public void initialize() {
        loan = Loan.builder().date(new Date()).returned(false).build();

        membershipComboBox.setItems(FXCollections.observableList(MembershipRepository.getAll()));
        membershipComboBox.setCellFactory(new Callback<ListView<Membership>, ListCell<Membership>>() {

            @Override
            public ListCell<Membership> call(ListView<Membership> l) {
                return new ListCell<Membership>() {

                    @Override
                    protected void updateItem(Membership item, boolean empty) {
                        super.updateItem(item, empty);
                        loan.setMembership(item);
                        setText(item == null ? "" : item.title);
                    }
                };
            }
        });
        membershipComboBox.setButtonCell(membershipComboBox.getCellFactory().call(null));
        var defaultMembership = MembershipRepository.getMembership("Default");
        membershipComboBox.getSelectionModel().select(defaultMembership);
        loan.setMembership(defaultMembership);
        calculateLoanUntilDate(new ActionEvent());
    }

    public void create(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        if (validateInput(stage)) {
            loan.setName(nameField.getText());
            loan.setMovie(MovieRepository.getMovie(movieField.getText()));
            loan.setMembership(membershipComboBox.getSelectionModel().getSelectedItem());
            LoanRepository.createLoan(loan);
            WindowHelper.getWindowHelper().openTableStage(stage);
            stage.hide();
        }
    }

    private boolean validateInput(Stage stage) {
        var warningMessage = "";
        if (Objects.equals(nameField.getText(), "")) {
            warningMessage += "Name must not be empty.\n";
        }
        if (Objects.equals(movieField.getText(), "")) {
            warningMessage += "Movie must not be empty.\n";
        }
        boolean valid = Objects.equals(warningMessage, "");
        if (!valid) {
            WindowHelper.showAlert(Alert.AlertType.ERROR, stage, "Invalid Input", warningMessage);
        }
        return valid;
    }

    public void calculateLoanUntilDate(ActionEvent actionEvent) {
        var returnDate = loan.calculateReturnDate();
        loanUntilField.setValue(LocalDateTime.ofInstant(returnDate.toInstant(), returnDate.getTimeZone().toZoneId()).toLocalDate());
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        WindowHelper.getWindowHelper().openTableStage(stage);
        stage.hide();
    }
}
