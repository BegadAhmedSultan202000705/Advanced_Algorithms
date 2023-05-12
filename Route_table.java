package submisson;

import java.util.*;

public class Route_table {
    
    public static Map<String, Map<String, String>> buildRoutingTables(Map<String, List<String>> network) {
        Map<String, Map<String, String>> routingTables = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();
        
        // Initialize routing tables and distances 
        for (String node : network.keySet()) {
            routingTables.put(node, new HashMap<>());
            distances.put(node, 0); 
        }
        
        // Mark distances to self as 0 and next hop as self   
        for (String node : network.keySet()) {
            routingTables.get(node).put(node, node);   
        }
        
        // Calculate shortest paths using Bellman-Ford algorithm
        for (int i = 0; i < network.size() - 1; i++) {
            for (String node : network.keySet()) {
                for (String neighbor : network.get(node)) {
                    if (distances.get(neighbor) > distances.get(node) + 1) {
                        distances.put(neighbor, distances.get(node) + 1);           
                        routingTables.get(neighbor).put(node, node);
                    }   
                }
            }
        }
        
        return routingTables;   
    }



    public static void main(String[] args) {
        Map<String, List<String>> network = new HashMap<>(); 
        network.put("A", Arrays.asList("B", "C", "D"));
        network.put("B", Arrays.asList("A")); 
        network.put("C", Arrays.asList("A"));
        network.put("D", Arrays.asList("A"));
        
        Map<String, Map<String, String>> routingTables = Route_table.buildRoutingTables(network);
        
        System.out.println(routingTables);    
    }
}