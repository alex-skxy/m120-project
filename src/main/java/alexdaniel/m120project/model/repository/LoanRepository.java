package alexdaniel.m120project.model.repository;

import alexdaniel.m120project.model.entity.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanRepository {
    private static List<Loan> loans;

    static {
        loans = new ArrayList<>();
    }

    public static void createMovie(Loan loan) {
        loans.add(loan);
        loan.id = (long) loans.indexOf(loan);
    }
}
