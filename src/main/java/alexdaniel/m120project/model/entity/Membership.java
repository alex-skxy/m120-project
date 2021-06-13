package alexdaniel.m120project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Membership {
    public Long id;

    public String title;
    public Integer extraDays;
}
