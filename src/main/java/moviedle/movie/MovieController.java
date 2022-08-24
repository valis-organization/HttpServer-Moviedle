package moviedle.movie;

import moviedle.movie.moviedle.Classic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){this.movieService = movieService;}

    @GetMapping(path = "/moviesList")
    public List<Movie> getMoviesList(){   //all movies list
     return movieService.getMovies();
    }

}
