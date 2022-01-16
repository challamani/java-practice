package com.core_java_practice.multithread.examples.sorting;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MergeSort implements Sort<Integer> {

    Logger logger  = Logger.getLogger("MergeSort");

    public List<Integer> sort(List<Integer> input) {
        return doSorting(input);
    }

    @Override
    public Map<String, String> getMetadata() {
        return null;
    }

    private List<Integer> doSorting(List<Integer> input)  {
        Integer[] inputArray = input.toArray(new Integer[input.size()]);
        mergeSort(inputArray, 0, (input.size()-1));
        return Arrays.asList(inputArray);
    }

    private void mergeSort(Integer[] input, int minIndex, int maxIndex)  {

        if(minIndex < maxIndex && (maxIndex-minIndex) > 1){
            int mid = (minIndex + maxIndex) / 2;
            mergeSort(input,minIndex,mid);
            mergeSort(input,(mid+1),maxIndex);
            mergeValues(input,minIndex,maxIndex,((minIndex+maxIndex) / 2));
        }else if(input[minIndex] > input[maxIndex]){
              int value = input[minIndex];
              input[minIndex] = input[maxIndex];
              input[maxIndex] = value;
        }
    }

    private void mergeValues(Integer[] input, int start, int end, int mid)  {
        Integer[] sortedArray = new Integer[(end-start)+1];
        try {
            int left=start;
            int right=(mid+1);
            int index=0;
            while (index<=(end-start))
            {
                if((left <= mid && right <= end) && (input[left] > input[right])){
                    sortedArray[index] = input[right];
                    right++;
                } else if ((left <= mid && right <= end) && (input[left] <= input[right])) {
                    sortedArray[index] = input[left];
                    left++;
                } else if(right <= end){
                    sortedArray[index] = input[right];
                    right++;
                } else if(left <= mid){
                    sortedArray[index] = input[left];
                    left++;
                }
                index++;
            }

            for(int ind=start,i=0;ind<=end;ind++,i++) {
                input[ind] = sortedArray[i];
            }

        } catch (Exception e) {
            logger.info("exception : {} "+e.getMessage());
        }
    }

    private void logInput(String prefix,Integer[] input,int start, int end){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=start;i<=end;i++){
            stringBuilder.append(input[i]+",");
        }
        logger.info("{} {}"+prefix+","+stringBuilder.toString());
    }
}
