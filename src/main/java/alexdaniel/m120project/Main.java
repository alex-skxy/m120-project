package alexdaniel.m120project;

import alexdaniel.m120project.model.entity.Loan;
import alexdaniel.m120project.model.entity.Movie;
import alexdaniel.m120project.model.repository.LoanRepository;
import alexdaniel.m120project.model.repository.MembershipRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // TODO: remove test data after testing
        LoanRepository.createLoan(new Loan(null, "alex", new Date(),
                new Movie(null, "A wild ride to down to hell on a stripper pole while being sexy and gay, fuck yeah, this is lil nas x"),
                MembershipRepository.getMembership("Piss"), false));
        LoanRepository.createLoan(new Loan(null, "daniel", new Date(),
                new Movie(null, "Kumo desu ga nani ka: into the spiderverse"),
                MembershipRepository.getMembership("Silver"), false));

        Stage root = FXMLLoader.load(getClass().getResource("view/tabledisplay.fxml"));

        stage = root;
        stage.show();
    }
}
