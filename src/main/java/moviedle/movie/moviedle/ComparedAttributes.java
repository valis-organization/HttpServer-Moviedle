package moviedle.movie.moviedle;

public class ComparedAttributes {
    private ResultType type;
    private ResultType genre;
    private ResultType director;
    private ResultType rank;
    private ResultType releaseYear;

    public ComparedAttributes(ResultType type, ResultType genre, ResultType director, ResultType rank, ResultType releaseYear) {
        this.type = type;
        this.genre = genre;
        this.director = director;
        this.rank = rank;
        this.releaseYear = releaseYear;
    }

    public ResultType getType() {
        return type;
    }

    public void setType(ResultType type) {
        this.type = type;
    }

    public ResultType getGenre() {
        return genre;
    }

    public void setGenre(ResultType genre) {
        this.genre = genre;
    }

    public ResultType getDirector() {
        return director;
    }

    public void setDirector(ResultType director) {
        this.director = director;
    }

    public ResultType getRank() {
        return rank;
    }

    public void setRank(ResultType rank) {
        this.rank = rank;
    }

    public ResultType getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(ResultType releaseYear) {
        this.releaseYear = releaseYear;
    }

}