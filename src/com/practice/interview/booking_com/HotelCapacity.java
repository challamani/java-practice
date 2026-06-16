package com.practice.interview.booking_com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelCapacity {

    public boolean canHotelAccommodate(List<Integer> arrival, List<Integer> depart, int capacity){

        List<Event> events = new ArrayList<>();

        for(int i=0; i<arrival.size(); i++){
            events.add(new Event(arrival.get(i), 1));
            events.add(new Event(depart.get(i), -1));
        }

        events.sort((a, b) -> {
            if (a.time != b.time)
                return Integer.compare(a.time, b.time);
            return Integer.compare(a.type, b.type);
        });

        int guests = 0;
        for(Event event: events){
            guests += event.type;
            if(guests > capacity){
                return false;
            }
        }
        return true;
    }

    static class Event {
        int time;
        int type;

        Event(int time, int type){
            this.time = time;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        // Test Case 1
        HotelCapacity hotelCapacity = new HotelCapacity();
        System.out.println("Test 1 (Expected False): " +
                hotelCapacity.canHotelAccommodate(Arrays.asList(1, 3, 5), Arrays.asList(2, 6, 8), 1));

        // Test Case 2
        System.out.println("Test 2 (Expected True): " +
                hotelCapacity.canHotelAccommodate(Arrays.asList(1, 2), Arrays.asList(2, 3), 1));

        // Test Case 3
        System.out.println("Test 3 (Expected False): " +
                hotelCapacity.canHotelAccommodate(Arrays.asList(1, 1, 1), Arrays.asList(5, 5, 5), 2));
    }
}
