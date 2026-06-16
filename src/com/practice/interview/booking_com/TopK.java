package com.practice.interview.booking_com;

import java.util.*;
import java.util.stream.Collectors;

public class TopK {

    private static final java.util.regex.Pattern NON_ALPHABET = java.util.regex.Pattern.compile("[^a-z]");

    public List<Integer> getTopKHotelsByScore(String positiveWords,
                                              String negativeWords,
                                              List<Integer> hotelIds,
                                              List<String> reviews,
                                              int k) {

        Set<String> posWords = Arrays.stream(positiveWords.toLowerCase().split("\\s+"))
                .collect(Collectors.toUnmodifiableSet());
        Set<String> negWords = Arrays.stream(negativeWords.toLowerCase().split("\\s+"))
                .collect(Collectors.toUnmodifiableSet());


//        Map<Integer, Integer> hotelAndScopeMap = IntStream.range(0, reviews.size())
//                .boxed()
//                .collect(Collectors.groupingBy(
//                        hotelIds::get,
//                        Collectors.summingInt(i -> calculateScore(reviews.get(i), posWords, negWords))
//                ));

        Map<Integer, Integer> hotelAndScoreMap = new HashMap<>();
        for (int i = 0; i < reviews.size(); i++) {
            int score = calculateScore(reviews.get(i), posWords, negWords);
            hotelAndScoreMap.merge(hotelIds.get(i), score, Integer::sum);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> {
                    if (!a.getValue().equals(b.getValue())) {
                        return a.getValue() - b.getValue();
                    }
                    return b.getKey() - a.getKey();
                }
        );

        hotelAndScoreMap.entrySet().stream().forEach(entry -> {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        });

        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        Collections.reverse(result);
        return result;

//        return hotelAndScoreMap.entrySet().stream()
//                .sorted(Map.Entry.<Integer,Integer>comparingByValue(Comparator.reverseOrder())
//                        .thenComparing(Map.Entry::getKey))
//                .limit(k)
//                .map(Map.Entry::getKey)
//                .toList();

    }

    private int calculateScore(String reviews, Set<String> posWords, Set<String> negWords) {

        if (reviews == null || reviews.isEmpty()) {
            return 0;
        }
        int score = 0;
        String cleaned = NON_ALPHABET.matcher(reviews.toLowerCase()).replaceAll(" ");
        String[] reviewWords = cleaned.split("\\s+");
        for (String reviewWord : reviewWords) {

            if (reviewWord.isEmpty())
                continue;

            if (posWords.contains(reviewWord)) {
                score += 3;
            } else if (negWords.contains(reviewWord)) {
                score -= 1;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        TopK topK = new TopK();
        String pos1 = "breakfast beach view";
        String neg1 = "noisy dirty";
        List<Integer> hotelIds1 = Arrays.asList(1, 2, 1, 1, 2, 3);
        List<String> reviews1 = Arrays.asList(
                "This hotel has a great beach view!", // Hotel 1: +6
                "The room was noisy and dirty.",      // Hotel 2: -2
                "I loved the breakfast.",             // Hotel 1: +3
                "Beach beach beach!",                // Hotel 1: +9
                "Very dirty.",
                "The room was noisy."
        );
        int k1 = 2;

        List<Integer> hotels = topK.getTopKHotelsByScore(pos1, neg1, hotelIds1, reviews1, k1);
        System.out.println(hotels);
    }
}
