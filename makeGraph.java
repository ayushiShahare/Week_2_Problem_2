import java.util.LinkedList;
import java.util.Scanner;
public class makeGraph {
    static class Edge {
        String source;
        String destination;
        int weight;

        public Edge(String source, String destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class Graph {
        int vertices;
        int paths;
        int totalDistance;
        LinkedList<Edge>[] adjList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }
        public void addEgde(String source, String destination, int weight) {
            Edge e1=new Edge(source,destination,weight);
            Edge e2=new Edge(destination,source,weight);
            int index1=(int)(source.charAt(0))-65;
            int index2=(int)(destination.charAt(0))-65;
            adjList[index1].addFirst(e1);
            adjList[index2].addFirst(e2);
            /*Edge edge = new Edge(source, destination, weight);
            adjList[source].addFirst(edge);*/
        }

        public void printGraph() {
            for (int i = 0; i < vertices; i++) {
                LinkedList<Edge> list = adjList[i];
                for (int j = 0; j < list.size(); j++) {
                    System.out.println("vertex-" + i + " is connected to " +
                            list.get(j).destination + " with weight " + list.get(j).weight);
                }
            }
        }

        
        public void calculate(String a, String b, boolean visited[], int dis) {
            if (a.equals(b)) {
                paths++;
                totalDistance += dis;
                return;
            }
            int index = (int) (a.charAt(0)) - 65;
            for (int i = 0; i < adjList[index].size(); i++) {
                String to = adjList[index].get(i).destination;
                int indexOfDestination = (int) (to.charAt(0)) - 65;
                if (visited[indexOfDestination] == false) {
                    visited[indexOfDestination] = true;
                    calculate(to, b, visited, dis + adjList[index].get(i).weight);
                    visited[indexOfDestination] = false;
                }
            }
            return;
        }

	public double calculateAverageDistanceBetweenTwoPoints(String a, String b) {
            boolean visited[] = new boolean[vertices];
            for (int i = 0; i < vertices; i++)
                visited[i] = false;
            paths = 0;
            totalDistance = 0;
            visited[(int) (a.charAt(0)) - 65] = true;
            calculate(a, b, visited, 0);
            return ((double) totalDistance / (double) paths);
        }

    }
    public static void main(String[] args) {
        int vertices = 5;
        Graph graph = new Graph(vertices);
        graph.addEgde("A", "B", 12);
        graph.addEgde("A", "C", 13);
        graph.addEgde("A", "E", 8);
        graph.addEgde("A", "D", 11);
        graph.addEgde("D", "E", 7);
        graph.addEgde("E", "C", 4);
        graph.addEgde("B", "C", 3);
        graph.printGraph();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter First Node");
        String a=sc.next();
        System.out.println("Enter Second Node ");
        String b= sc.next();
        double avgDistance=graph.calculateAverageDistanceBetweenTwoPoints(a,b);
        System.out.println("Average Distance between two Nodes is "+avgDistance);
    }
}
