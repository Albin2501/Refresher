package albin2501.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import albin2501.entity.Grid;

// TODO: datagenerator

@Repository
public interface GridRepository extends JpaRepository<Grid, Long> {

    @Query("SELECT g.id FROM Grid g")
    Optional<Long[]> findAllIds();

    @Query(value = "SELECT array_length(cells, 1), " +
        "array_length(cells, 2) FROM Grid WHERE id = :id", nativeQuery = true)
    Optional<Long[]> findDimension(@Param("id") Long id);

    @Override
    Optional<Grid> findById(Long id);
}
