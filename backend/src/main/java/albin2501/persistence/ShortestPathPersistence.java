package albin2501.persistence;

import org.springframework.stereotype.Repository;
import albin2501.entity.ShortestPathData;
import albin2501.util.datatype.CustomGraph;

@Repository
public class ShortestPathPersistence {
    private CustomGraph currGraph; // volatile memory

    public ShortestPathPersistence() { }

    public ShortestPathPersistence(CustomGraph currGraph) {
        this.currGraph = currGraph;
    }

    public CustomGraph getCurrGraph() {
        return this.currGraph;
    }

    public void setCurrGraph(CustomGraph currGraph) {
        this.currGraph = currGraph;
    }

    public ShortestPathData getShortestPathData() {
        // TODO

        return null;
    }
}
