package com.practice.ds.priority_queue;

import java.util.*;

public class TopKFrequent {

    public List<Integer> returnTopKFrequentItems(List<Integer> items, int k){

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for(Integer item: items){
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for(Map.Entry<Integer, Integer> entry: frequencyMap.entrySet()){
            priorityQueue.offer(entry);
            if(priorityQueue.size() > k){
                priorityQueue.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!priorityQueue.isEmpty()){
            result.add(priorityQueue.poll().getKey());
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> items = Arrays.asList(1, 1, 1, 2, 2, 3, 3, 3, 3);
        int k = 2;

        TopKFrequent topKFrequent = new TopKFrequent();
        List<Integer> topKItems = topKFrequent.returnTopKFrequentItems(items, k);

        System.out.println(topKItems); // Output: [1, 3]
    }
}
