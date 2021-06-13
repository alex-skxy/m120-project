package alexdaniel.m120project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Movie {
    public Long id;
    public String title;
}
