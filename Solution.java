import java.util.*;

class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        // Result array
        int[] answer = new int[queries.length];
        
        // Graph represented as an adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Initialize the default roads: from city i to city i+1
        for (int i = 0; i < n - 1; i++) {
            graph.get(i).add(i + 1);
        }
        
        // Process each query
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            graph.get(u).add(v); // Add the new road
            answer[i] = findShortestPath(n, graph); // Find shortest path after this query
        }
        
        return answer;
    }
    
    // Function to find the shortest path from 0 to n-1 using BFS
    private int findShortestPath(int n, List<List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.add(0);
        visited[0] = true;
        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (current == n - 1) {
                    return distance;
                }
                for (int neighbor : graph.get(current)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
            distance++;
        }
        return -1; // Unreachable
    }
}
