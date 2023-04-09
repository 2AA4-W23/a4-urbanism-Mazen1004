package ca.mcmaster.cas.se2aa4.pathfinder.pathfinding;

import ca.mcmaster.cas.se2aa4.pathfinder.graphs.nodes.Nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra implements Pathfinder {
    public static List<Integer> findShortestPath(Map<Integer, Map<Integer, Integer>> adjacencyList, int start, int end) {

        //Tracks the shortest distance from start node to other nodes in the graph
        Map<Integer, Double> distances = new HashMap<>();

        //Priority queue to store the nodes in the graph ordered by their distance from the start node
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> Double.compare(distances.get(a), distances.get(b)));

        //Set all distances to infinity except the start node, which is set to 0
        for (int node : adjacencyList.keySet()) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);
        //Adds start node to priority queue
        pq.add(start);

        //Track the previous node on the shortest path to each node in the graph
        Map<Integer, Integer> previous = new HashMap<>();

        while (!pq.isEmpty()) {

            // Remove the node with the shortest distance from the priority queue
            int curr = pq.poll();

            // If end node reached break loop
            if (curr == end) {
                break;
            }

            // Loop over all neighbors of the current node
            for (Map.Entry<Integer, Integer> neighbor : adjacencyList.get(curr).entrySet()) {

                //Gets neighbors and edges needed for calculation
                int neighborNode = neighbor.getKey();
                double edgeWeight = neighbor.getValue();

                //Calculates distance of new path found
                double newDistance = distances.get(curr) + edgeWeight;

                //If the new distance is shorter than the current distance to the neighbor node, update the distance and the previous node
                if (newDistance < distances.get(neighborNode)) {
                    distances.put(neighborNode, newDistance);
                    pq.remove(neighborNode);
                    pq.add(neighborNode);
                    previous.put(neighborNode, curr);
                }
            }
        }

        // Initialize a list to store the nodes on the shortest path from the start node to the end node
        List<Integer> path = new ArrayList<>();
        // Starting from the end node, follow the previous nodes to the start node and add them to the path list
        int curr = end;
        while (previous.containsKey(curr)) {
            path.add(curr);
            curr = previous.get(curr);
        }

        //Add the start node to the path list and reverse it to get the correct order of nodes
        path.add(start);
        Collections.reverse(path);

        return path;
    }

}
