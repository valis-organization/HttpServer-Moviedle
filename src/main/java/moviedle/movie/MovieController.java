package moviedle.movie;

import moviedle.helpers.Logger;
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
    private List<Movie> getMoviesList() {   //all movies list
        Logger.receivedAllMoviesRequest();
        return movieService.getMovies();
    }
    @GetMapping(path = "/moviesList/{genre}")
    private List<Movie> getMoviesByGenre(@PathVariable("genre") String genre){
        return movieService.getMoviesByGenre(genre);
    }
    @GetMapping(path = "/moviesList/type/{type}")
    private List<Movie> getMoviesByType(@PathVariable("type") String type){
        return movieService.getMoviesByType(type);
    }

    @GetMapping(path = "/movie/{title}")
    private Movie getMovie(@PathVariable("title") String title) {
        return movieService.getMovieByTitle(title.toLowerCase(Locale.ROOT));
    }

    @GetMapping(path = "/randomMovie")
    private Movie getRandomMovie() {
        Logger.receivedRandomMovieRequest();
        return movieService.getRandomMovie();
    }

}
