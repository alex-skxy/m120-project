package alexdaniel.m120project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Loan {
    public Long id;
    public String name;
    public Date date;
    public Movie movie;
    public Membership membership;
    public Boolean returned;
}
