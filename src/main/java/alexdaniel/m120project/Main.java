package alexdaniel.m120project;

import alexdaniel.m120project.model.entity.Loan;
import alexdaniel.m120project.model.repository.LoanRepository;
import alexdaniel.m120project.model.repository.MembershipRepository;
import alexdaniel.m120project.model.repository.MovieRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        LoanRepository.createLoan(
                Loan.builder()
                        .name("alex")
                        .date(new Date())
                        .movie(MovieRepository.getMovie(
                                "A wild ride to down to hell on a stripper pole while being sexy and gay, fuck yeah, this is lil nas x"
                        ))
                        .membership(MembershipRepository.getMembership("Piss"))
                        .returned(false)
                        .build());

        LoanRepository.createLoan(
                Loan.builder()
                        .name("daniel")
                        .date(new GregorianCalendar(2014, Calendar.AUGUST, 12).getTime())
                        .movie(MovieRepository.getMovie(
                                "Kumo desu ga nani ka: into the spiderverse"
                        ))
                        .membership(MembershipRepository.getMembership("Silver"))
                        .returned(false)
                        .build());

        Stage root = FXMLLoader.load(getClass().getResource("view/tabledisplay.fxml"));

        stage = root;
        stage.show();
    }
}
