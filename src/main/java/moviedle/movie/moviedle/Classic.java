package moviedle.movie.moviedle;

import moviedle.movie.Movie;
import moviedle.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("games/classic")
public class Classic {

    private final MovieService movieService;
    private Movie movieToGuess;

    @Autowired
    public Classic(MovieService movieService) {
        this.movieService = movieService;
    }

    @PutMapping(path = "/start")
    public void startGame() {
        movieToGuess = movieService.getRandomMovie();
        System.out.println(movieToGuess.getTitle());
    }


    @GetMapping(path = "guess/{title}")
    public ComparedAttributes guessMovie(@PathVariable("title") String title) {
        if (movieToGuess != null) {
            Movie chosenMovie = movieService.getMovieByTitle(title.toLowerCase(Locale.ROOT));
            if (!movieExistsInDB(chosenMovie)) {
                throw new IllegalStateException("Movie does not exist");
            }else {
                return MovieComparator.compareChosenMovie(chosenMovie,movieToGuess);
            }
        }
        throw new IllegalStateException("You need to start game first!");
    }

    private boolean movieExistsInDB(Movie movie){
        return movie != null;
    }

}
