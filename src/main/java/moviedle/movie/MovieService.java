package moviedle.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieByTitle(String title) {
        return movieRepository.getMovieByTitle(title);
    }

    public Movie getRandomMovie() {
        List<Movie> moviesList = getMovies();
        int randomNumber = (int) Math.floor(Math.random() * (moviesList.size()));
        return moviesList.get(randomNumber);
    }
}
