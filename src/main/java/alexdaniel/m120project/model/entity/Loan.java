package alexdaniel.m120project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Loan {
    private Long id;
    private String name;
    private Date date;
    private Movie movie;
    private Membership membership;
    private Boolean returned;

    public boolean isLate() {
        var today = Calendar.getInstance();
        return calculateReturnDate().compareTo(today) < 0;
    }

    public Calendar calculateReturnDate() {
        var calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, membership.getExtraDays() + 30);
        return calendar;
    }
}
