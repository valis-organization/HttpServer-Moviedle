package moviedle.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/games")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){this.movieService = movieService;}

    @GetMapping(path = "/moviesList")
    public List<Movie> getMoviesList(){
     return movieService.getMovies();
    }



    @GetMapping(path = "{title}")
    public Movie guessMovie(@PathVariable("title") String title){
        if(movieService.getMovieByTitle(title) == null){
            throw new IllegalStateException("Movie does not exist");
        }
        return movieService.getMovieByTitle(title);
    }
}
