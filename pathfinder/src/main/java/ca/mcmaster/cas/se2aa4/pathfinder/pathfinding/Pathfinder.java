package ca.mcmaster.cas.se2aa4.pathfinder.pathfinding;

import ca.mcmaster.cas.se2aa4.pathfinder.graphs.nodes.Nodes;

import java.util.List;
import java.util.Map;

public interface Pathfinder {
    static List<Nodes> findShortestPath(Map<Integer, Map<Integer, Integer>> adjacencyList, int startNodeID, Nodes endNodeID) {
        return null;
    }
}