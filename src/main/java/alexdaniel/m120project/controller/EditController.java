package alexdaniel.m120project.controller;

import alexdaniel.m120project.WindowHelper;
import alexdaniel.m120project.model.entity.Loan;
import alexdaniel.m120project.model.entity.Membership;
import alexdaniel.m120project.model.repository.MembershipRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class EditController {
    public Loan loan;

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
        membershipComboBox.setItems(FXCollections.observableList(MembershipRepository.getAll()));
        membershipComboBox.setCellFactory(new Callback<ListView<Membership>, ListCell<Membership>>() {

            @Override
            public ListCell<Membership> call(ListView<Membership> l) {
                return new ListCell<Membership>() {

                    @Override
                    protected void updateItem(Membership item, boolean empty) {
                        super.updateItem(item, empty);
                        if (loan != null) {
                            loan.setMembership(item);
                        }

                        setText(item == null ? "" : item.title);
                    }
                };
            }
        });
        membershipComboBox.setButtonCell(membershipComboBox.getCellFactory().call(null));
        membershipComboBox.getSelectionModel().select(MembershipRepository.getMembership("Default"));
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
        System.out.println("" + nameField);
        nameField.setText(loan.getName());
        movieField.setText(loan.getMovie().getTitle());
        membershipComboBox.getSelectionModel().select(loan.getMembership());
    }

    public void calculateLoanUntilDate(ActionEvent actionEvent) {
        var returnDate = loan.calculateReturnDate();
        loanUntilField.setValue(LocalDateTime.ofInstant(returnDate.toInstant(), returnDate.getTimeZone().toZoneId()).toLocalDate());
    }

    public void save(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        if (validateInput(stage)) {

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

    public void cancel(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        WindowHelper.getWindowHelper().openTableStage(stage);
        stage.hide();
    }
}

