package com.practice.ds.priority_queue;

import java.util.*;

public class MergeListItems {

    @SafeVarargs
    public final List<Integer> mergeSortedItems(List<Integer>... items) {

        List<Integer> result = new ArrayList<>();
        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(0)));
        Arrays.stream(items).forEach(integers ->
                priorityQueue.offer(new ArrayList<>(integers))
        );

        while (!priorityQueue.isEmpty()) {
            List<Integer> smalletList = priorityQueue.poll();
            result.add(smalletList.remove(0));

            if (!smalletList.isEmpty()) {
                priorityQueue.offer(smalletList);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> item1 = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> item2 = List.of(0, 12, 13, 14, 15, 16);
        List<Integer> item3 = List.of(10, 11, 17, 18, 19, 25);

        MergeListItems mergeListItems = new MergeListItems();
        System.out.println(mergeListItems.mergeSortedItems(item1, item2, item3));
    }
}
