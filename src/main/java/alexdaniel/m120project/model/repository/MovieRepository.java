package alexdaniel.m120project.model.repository;

import alexdaniel.m120project.model.entity.Loan;
import alexdaniel.m120project.model.entity.Membership;
import alexdaniel.m120project.model.entity.Movie;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MovieRepository {
    private static List<Movie> movies;

    static {
        movies = new ArrayList<>();
    }

    public static void createMovie(Movie movie) {
        movies.add(movie);
        movie.id = (long) movies.indexOf(movie);
    }

    public static List<Movie> getAll() {
        return ImmutableList.copyOf(movies);
    }

    public static Movie getMovie(String title) {
        var optionalMovie = movies.stream().filter(membership -> Objects.equals(membership.title, title)).findFirst();
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        } else {
            var newMovie = Movie.builder().title(title).build();
            createMovie(newMovie);
            return newMovie;
        }
    }
}
