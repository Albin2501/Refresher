package albin2501.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import albin2501.entity.TextArt;

@Repository
public interface TextArtRepository extends JpaRepository<TextArt, Long> {
    @Query("SELECT t FROM TextArt t ORDER BY t.time DESC")
    List<TextArt> findAllCustom();
}
