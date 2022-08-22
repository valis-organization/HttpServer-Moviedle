package moviedle.movie;

import javax.persistence.*;

@Table
@Entity
public class Movie {
    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    private Long id;
    private String title;

    public Movie(Long id,String title){
        this.id = id;
        this.title = title;
    }

    public Movie() {

    }

}
