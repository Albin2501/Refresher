package albin2501.persistence;

import org.springframework.stereotype.Repository;
import albin2501.entity.ShortestPathData;
import albin2501.exception.PersistenceException;
import albin2501.util.datatype.CustomGraph;

// TODO: Add database instead of .json file

@Repository
public class ShortestPathPersistence {
    private CustomGraph currGraph; // volatile memory

    public ShortestPathPersistence() { }

    public ShortestPathPersistence(CustomGraph currGraph) {
        this.currGraph = currGraph;
    }

    public CustomGraph getCurrGraph() {
        if (currGraph == null) throw new PersistenceException("Graph needs to be generated first.");
        return currGraph;
    }

    public void setCurrGraph(CustomGraph currGraph) {
        this.currGraph = currGraph;
    }

    public ShortestPathData getShortestPathData() {
        return null;
    }
}
