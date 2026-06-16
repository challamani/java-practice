package com.practice.ds.dp;


import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;


class Result {

    public static int findMinimumPlansForBandwidth(List<Integer> planSizes, int targetBandwidth) {
        // Write your code here
        Collections.sort(planSizes);

        int max = targetBandwidth + 1;
        int[] prefillPlans = new int[targetBandwidth+1];
        Arrays.fill(prefillPlans, max);

        prefillPlans[0] = 0;
        for(int i=1; i<=targetBandwidth; i++){
            for(Integer plan: planSizes){
                if(i >= plan){
                    prefillPlans[i] = Math.min(prefillPlans[i], prefillPlans[i-plan] + 1);
                }
            }
        }
        return prefillPlans[targetBandwidth] > targetBandwidth ? -1 : prefillPlans[targetBandwidth];
    }

}

public class MinimumPlans {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int planSizesCount = Integer.parseInt(bufferedReader.readLine().trim());
        List<Integer> planSizes = IntStream.range(0, planSizesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int targetBandwidth = Integer.parseInt(bufferedReader.readLine().trim());
        int result = Result.findMinimumPlansForBandwidth(planSizes, targetBandwidth);
        System.out.println(result);
        bufferedReader.close();
    }
}
