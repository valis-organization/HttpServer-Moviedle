package movieProject.moviedle.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "jd")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){this.movieService = movieService;}

    @GetMapping
    public List<Movie> helloMovie(){
     return movieService.getMovies();
    }
}
