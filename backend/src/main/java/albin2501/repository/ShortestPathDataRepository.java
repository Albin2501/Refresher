package albin2501.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import albin2501.entity.ShortestPath;

@Repository
public interface ShortestPathDataRepository extends JpaRepository<ShortestPath, Long> {
    
    // TODO: get all the important information from an SQL query
    // @Query("SELECT ...")
    // ShortestPathData getData();
}
