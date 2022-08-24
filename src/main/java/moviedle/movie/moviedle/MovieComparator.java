package moviedle.movie.moviedle;

import moviedle.movie.Movie;

import java.util.Objects;

public class MovieComparator {
    private static final String ATTRIBUTE_TYPE = "TYPE";
    private static final String ATTRIBUTE_GENRE = "GENRE";
    private static final String ATTRIBUTE_DIRECTOR = "DIRECTOR";
    private static final String ATTRIBUTE_RANK = "RANK";
    private static final String ATTRIBUTE_RELEASE_YEAR = "RELEASE_YEAR";

    public static ComparedAttributes compareChosenMovie(Movie chosenMovie, Movie movieToGuess) {
        ComparedAttributes comparedAttributes = new ComparedAttributes(
                compareAttribute(ATTRIBUTE_TYPE,chosenMovie,movieToGuess),
                compareAttribute(ATTRIBUTE_GENRE,chosenMovie,movieToGuess),
                compareAttribute(ATTRIBUTE_DIRECTOR,chosenMovie,movieToGuess),
                compareAttribute(ATTRIBUTE_RANK,chosenMovie,movieToGuess),
                compareAttribute(ATTRIBUTE_RELEASE_YEAR,chosenMovie,movieToGuess)
        );
        return comparedAttributes;
    }

    private static ResultType compareAttribute(String attribute, Movie chosenMovie, Movie movieToGuess){
        String chosenMovieAttribute = attributeMapper(attribute,chosenMovie);
        String movieToGuessAttribute = attributeMapper(attribute,movieToGuess);

        if(Objects.requireNonNull(chosenMovieAttribute).equals(movieToGuessAttribute)){
            return ResultType.CORRECT;
        }else if(chosenMovieAttribute.contains(Objects.requireNonNull(movieToGuessAttribute))
                || movieToGuessAttribute.contains(chosenMovieAttribute)){
            return ResultType.PARTIAL;
        }else {
            return ResultType.WRONG;
        }
    }

    private static String attributeMapper(String attribute,Movie movie){
        switch (attribute) {
            case ATTRIBUTE_TYPE:
                return movie.getType();
            case ATTRIBUTE_GENRE:
                return movie.getGenre();
            case ATTRIBUTE_DIRECTOR:
                return movie.getDirector();
            case ATTRIBUTE_RANK:
                return movie.getRank();
            case ATTRIBUTE_RELEASE_YEAR:
                return movie.getReleaseYear();
        }
        return null;
    }
}
