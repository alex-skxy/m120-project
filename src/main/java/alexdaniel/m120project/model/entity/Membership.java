package alexdaniel.m120project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Membership {
    public Long id;

    public String title;
    public Integer extraDays;
}
