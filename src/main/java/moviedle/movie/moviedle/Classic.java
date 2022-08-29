package moviedle.movie.moviedle;

import moviedle.movie.Movie;
import moviedle.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@EnableScheduling
@RequestMapping("games/classic")
public class Classic {

    private final MovieService movieService;
    private Movie movieToGuess;

    @Autowired
    public Classic(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/start")  //TEMP METHOD
    private void startGame() {
        movieToGuess = movieService.getRandomMovie();
        System.out.println(movieToGuess.getTitle());
    }

    @Scheduled(cron = "0 0 2 * * ?")  //2:00: 0 0 2 - seconds,minute,hour
    private void generateRandomMovieEveryDay() {
        movieToGuess = movieService.getRandomMovie();
        System.out.println(movieToGuess.getTitle());
    }

    @GetMapping(path = "/movieToGuess")
    private Movie getMovieToGuess(){
        return movieToGuess;
    }

    @GetMapping(path = "guess/{title}")
    private List guessMovie(@PathVariable("title") String title) {
        if (movieToGuess != null) {
            Movie chosenMovie = movieService.getMovieByTitle(title.toLowerCase(Locale.ROOT));
            if (!movieExistsInDB(chosenMovie)) {
                throw new IllegalStateException("Movie does not exist");
            } else {
                return List.of(chosenMovie, MovieComparator.compareChosenMovie(chosenMovie, movieToGuess));
            }
        }
        throw new IllegalStateException("You need to start game first!");
    }

    private boolean movieExistsInDB(Movie movie) {
        return movie != null;
    }

}
