package moviedle.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    //SELECT * FROM movieProject.moviedle.managers.movie WHERE title = ?
    @Query("SELECT s FROM Movie s WHERE s.title = ?1")
    Movie getMovieByTitle(String title);

    @Query("SELECT s FROM Movie s WHERE s.genre LIKE CONCAT('%',:genre,'%')")
    List<Movie> getMoviesByGenre(@Param("genre") String genre);
}
