import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 *  Name: Roman Manzhelii
 *  Class Group: SD2a
 */
public class CA3_Question10 {

    public static void main(String[] args) {
        try {
            Map<String, TreeSet<DistanceTo>> graph = readGraph();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter start city:");
            String startCity = scanner.next();
            Map<String, Integer> distances = dijkstra(graph, startCity);
            writeData(distances, startCity);
        } catch (FileNotFoundException e) {
            System.out.println("File didn't find.");
        }
    }

    private static Map<String, TreeSet<DistanceTo>> readGraph() throws FileNotFoundException {
        Map<String, TreeSet<DistanceTo>> graph = new HashMap<>();

        //TODO provide your file path
        Scanner scanner = new Scanner(new File("C:\\Users\\PRO\\Desktop\\network.txt")); 
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" ");
            String city1 = parts[0];
            String city2 = parts[1];
            int distance = Integer.parseInt(parts[2]);

            graph.computeIfAbsent(city1, k -> new TreeSet<>()).add(new DistanceTo(city2, distance));
            graph.computeIfAbsent(city2, k -> new TreeSet<>()).add(new DistanceTo(city1, distance));
        }
        scanner.close();
        return graph;
    }

    private static Map<String, Integer> dijkstra(Map<String, TreeSet<DistanceTo>> graph, String start) {
        final int INF = Integer.MAX_VALUE;
        Map<String, Integer> distance = new HashMap<>();
        PriorityQueue<DistanceTo> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(DistanceTo::getDistance));

        for (String city : graph.keySet()) {
            distance.put(city, INF);
        }
        distance.put(start, 0);
        priorityQueue.add(new DistanceTo(start, 0));

        while (!priorityQueue.isEmpty()) {
            DistanceTo current = priorityQueue.poll();
            String currentCity = current.getTarget();
            int currentDistance = current.getDistance();

            for (DistanceTo neighbor : graph.getOrDefault(currentCity, new TreeSet<>())) {
                String neighborCity = neighbor.getTarget();
                int newDist = currentDistance + neighbor.getDistance();
                if (newDist < distance.getOrDefault(neighborCity, INF)) {
                    distance.put(neighborCity, newDist);
                    priorityQueue.add(new DistanceTo(neighborCity, newDist));
                }
            }
        }

        return distance;
    }

    private static void writeData(Map<String, Integer> distances, String startCity) {
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            String targetCity = entry.getKey();
            int distance = entry.getValue();
            String result = distance == Integer.MAX_VALUE ? "no" : String.valueOf(distance);
            System.out.println(startCity + " => " + targetCity + " = " + result);
        }
    }
}