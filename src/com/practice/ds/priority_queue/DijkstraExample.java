package com.practice.ds.priority_queue;


import java.util.*;

public class DijkstraExample {


    private static class Node implements Comparable<Node> {
        int id;
        int distance;

        Node(int id, int distance){
            this.id = id;
            this.distance= distance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public Map<Integer, Integer> findShortestPath(int startNode,
                                                  Map<Integer,List<Edge>> graphs,
                                                  Integer backtrack){

        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        distances.put(startNode, 0);
        priorityQueue.offer(new Node(startNode, 0));
        Map<Integer, Integer> pathMap =  new HashMap<>();
        while (!priorityQueue.isEmpty()){

            Node current = priorityQueue.poll();
            int currentNodeId = current.id;
            if(current.distance > distances.get(currentNodeId)){
                continue;
            }

            if(graphs.containsKey(currentNodeId)){
                graphs.get(currentNodeId).forEach(edge -> {
                    int v = edge.targetNode;
                    int weight = edge.weight;
                    int newDistance = distances.get(currentNodeId) + weight;

                    if (newDistance < distances.getOrDefault(v, Integer.MAX_VALUE)){
                        distances.put(v, newDistance);
                        pathMap.put(v, currentNodeId);
                        priorityQueue.offer(new Node(v, newDistance));
                    }
                });
            }
        }

        List<Integer> path = new ArrayList<>();
        int target = backtrack;
        while (backtrack != null) {
            path.add(backtrack);
            backtrack = pathMap.get(backtrack); // Move to the person who invited this node
        }
        Collections.reverse(path);
        System.out.println("Path to target: "+target+" is: "+ path);
        return distances;
    }

    public static void main(String[] args) {
        // Constructing a simple graph
        int startNode = 0;
        int backtrack = 3;
        Map<Integer, List<Edge>> graph = new HashMap<>();
        graph.put(0, List.of(new Edge(1, 4), new Edge(2, 1)));
        graph.put(1, List.of(new Edge(3, 1)));
        graph.put(2, List.of(new Edge(1, 2), new Edge(3, 5)));
        graph.put(3, new ArrayList<>());

        Map<Integer, Integer> distances = new DijkstraExample().findShortestPath(startNode, graph, backtrack);
        distances.forEach((node, dist) -> System.out.printf("from star: %d to node: %d, distance is: %d \n", startNode, node, dist));
    }
}

class Edge{
    int weight;
    int targetNode;

    Edge(int targetNode,int weight){
        this.weight = weight;
        this.targetNode = targetNode;
    }
}
