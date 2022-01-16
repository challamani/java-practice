package com.core_java_practice.multithread.examples.sorting;

import java.util.List;
import java.util.Map;

public interface Sort<T> {
    List<T> sort(List<T> input) ;
    Map<String,String> getMetadata();
}
