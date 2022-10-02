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
    private String imageUrl;
    private String title;
    private String type; //movie, series,miniseries,animated movie, animated series
    private String genre; //action, adventure, fantasy...
    private String director;
    private String rank; //position in rank from imdb, top 10, top 50...
    private String releaseYear;// 2011-2020,2000-2010 ,2021+...

    public Movie(Long id,String imageUrl, String title, String type, String genre, String director, String rank, String releaseYear) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.director = director;
        this.rank = rank;
        this.releaseYear = releaseYear;
    }

    public Movie(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Movie() {

    }

    public String getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
