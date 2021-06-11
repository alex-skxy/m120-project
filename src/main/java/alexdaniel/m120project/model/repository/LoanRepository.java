package alexdaniel.m120project.model.repository;

import alexdaniel.m120project.model.entity.Loan;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoanRepository {
    private static List<Loan> loans;

    static {
        loans = new ArrayList<>();
    }

    public static void createMovie(Loan loan) {
        loans.add(loan);
        loan.id = (long) loans.indexOf(loan);
    }

    public static List<Loan> getNotReturned(){
        return loans.stream().filter(loan -> !loan.getReturned()).collect(Collectors.toUnmodifiableList());
    }
}
