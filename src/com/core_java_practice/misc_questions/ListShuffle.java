package com.core_java_practice.misc_questions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListShuffle {

    public static void main(String[] args) {
        //phone number masking
        randomPhoneNumberMask();
        System.out.println("Current DateTime :: " + getCurrentDateTime("yyyy-MM-dd'T'HH:mm:ss"));
        String amountToDebit = "56.06";
        Integer amountToPaymentGateway = new Double(Double.parseDouble(amountToDebit) * 100).intValue();
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
}
