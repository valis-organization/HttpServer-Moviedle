package moviedle.user;

import moviedle.movie.Movie;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import java.util.Set;


@Table
@Entity
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    @ElementCollection(targetClass = Movie.class)
    private Set<Movie> favouriteMovies;
    @ElementCollection(targetClass = Movie.class)
    private Set<Movie> dislikedMovies;



    //TABLE
    public User(Long id, String nickname, String password, String salt) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
    }

    //Saving to dB
    public User(String nickname, String password, String salt) {
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
    }

    //Received user
    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public User() {

    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt(){return salt;}

    public Set<Movie> getFavouriteMovies() {
        return favouriteMovies;
    }

    public void addMovieToMyList(Movie movie) {
        this.favouriteMovies.add(movie);
    }
    public void removeMovieFromMyList(Movie movie){
        this.favouriteMovies.remove(movie);
    }
}
