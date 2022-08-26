package moviedle.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    //SELECT * FROM movieProject.moviedle.managers.movie WHERE title = ?
    @Query("SELECT s FROM Movie s WHERE s.title = ?1")
    Movie getMovieByTitle(String title);
}
