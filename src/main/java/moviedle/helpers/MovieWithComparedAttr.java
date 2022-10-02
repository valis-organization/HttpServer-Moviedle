package moviedle.helpers;

import moviedle.movie.Movie;
import moviedle.movie.moviedle.ComparedAttributes;

public class MovieWithComparedAttr {
    private final Movie movie;
    private final ComparedAttributes comparedAttributes;

    public MovieWithComparedAttr(Movie movie, ComparedAttributes comparedAttributes){
        this.movie = movie;
        this.comparedAttributes = comparedAttributes;
    }

    public Movie getMovie() {
        return movie;
    }

    public ComparedAttributes getComparedAttributes() {
        return comparedAttributes;
    }
}
