package alexdaniel.m120project.model.repository;

import alexdaniel.m120project.model.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private static List<Movie> movies;

    static {
        movies = new ArrayList<>();
    }

    public static void createMovie(Movie movie) {
        movies.add(movie);
        movie.id = (long) movies.indexOf(movie);
    }
}
