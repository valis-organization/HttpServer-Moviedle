package moviedle.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/moviesList")
    public List<Movie> getMoviesList() {   //all movies list
        return movieService.getMovies();
    }
    @GetMapping(path = "/moviesList/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable("genre") String genre){
        return movieService.getMoviesByGenre(genre);
    }

    @GetMapping(path = "/movie/{title}")
    public Movie getMovie(@PathVariable("title") String title) {
        return movieService.getMovieByTitle(title.toLowerCase(Locale.ROOT));
    }

    @GetMapping(path = "/randomMovie")
    public Movie getRandomMovie() {
        return movieService.getRandomMovie();
    }

}
