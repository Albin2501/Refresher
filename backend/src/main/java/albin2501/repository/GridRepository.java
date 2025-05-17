package albin2501.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import albin2501.entity.Grid;

@Repository
public interface GridRepository extends JpaRepository<Grid, Long> {
    @Query("SELECT g.id FROM Grid g")
    Optional<Long[]> findAllIds();
}
