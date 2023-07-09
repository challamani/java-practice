package com.corejavapractice.misc_questions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListShuffle {

    public static void main(String[] args) {
        //phone number masking
        randomPhoneNumberMask();
        System.out.println("Current DateTime :: " + getCurrentDateTime("yyyy-MM-dd'T'HH:mm:ss"));
        String amountToDebit = "56.06";
        Integer amountToPaymentGateway = Double.valueOf(Double.parseDouble(amountToDebit) * 100).intValue();
        System.out.println(amountToPaymentGateway);
    }

    public static String getCurrentDateTime(String format) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 2);
            DateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static void randomPhoneNumberMask() {

        Integer NUM_OF_DIGITS_MASK = 4;
        List<Integer> RANDOM_DIGITS = Arrays.asList(3, 4, 5, 6, 7, 8, 9);

        Collections.shuffle(RANDOM_DIGITS);
        System.out.println("random digits : "+RANDOM_DIGITS);

        StringBuilder msisdnMasked = new StringBuilder("919703779308");
        for (int i = 0; i < NUM_OF_DIGITS_MASK; i++) {
            msisdnMasked.setCharAt(RANDOM_DIGITS.get(i), 'X');
        }
        String similarMSISDNMasked = msisdnMasked.toString();
        System.out.println("similarMSISDNMasked ::" + similarMSISDNMasked);
    }

    public static class TowersAndWaterHolding {

        public static void main(String args[]) {

            int[] towers = {5, 3, 7, 4, 6, 7, 8, 2, 1, 5, 6, 7, 3, 2, 3};
            int[] waterHolding = new int[towers.length];
            int max = towers[0];
            int x = 1;
            int secondMax = 0;
            int lastMaxPosition = 0;

            while (x < towers.length) {
                if (max < towers[x]) {
                    secondMax = max;
                    max = towers[x];
                    fillWater(towers, waterHolding, lastMaxPosition, x, secondMax);
                    lastMaxPosition = x;
                } else if (x + 1 == towers.length) {
                    x = getMaxTowerPosition(towers, lastMaxPosition + 1);
                    System.out.println("reverse max :: " + towers[x] + " , pos :: " + x);
                    secondMax = towers[x];
                    max = secondMax;
                    fillWater(towers, waterHolding, lastMaxPosition, x, secondMax);
                    lastMaxPosition = x;
                }
                x++;
            }
            for (int water : waterHolding)
                System.out.print(water + ",");
        }

        private static void fillWater(int[] towers, int[] waterHolding, int x, int y, int maxTower) {
            for (int i = x; i < y; i++) {
                waterHolding[i] = maxTower <= towers[i] ? 0 : maxTower - towers[i];
            }
        }

        private static int getMaxTowerPosition(int[] towers, int x) {
            int max = towers[x];
            int maxNumberPosition = x;
            for (int i = x; i < towers.length; i++) {
                if (max < towers[i]) {
                    maxNumberPosition = i;
                    max = towers[i];
                }
            }
            return maxNumberPosition;
        }
    }
}
