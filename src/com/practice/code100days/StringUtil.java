package com.practice.code100days;

import com.practice.MyTechHub;

import java.util.Scanner;

public class StringUtil implements MyTechHub {

    public StringUtil(){
        displayProblemAndApproach();
    }

    @Override
    public String getApproach() {
        return "Iterate the string to " +
                "\nidentify the character at each index";
    }

    @Override
    public String getProblem() {
        return "Encode/Decode the given string based on char \n" +
                "frequency, e.g aaaddde <=> a3d3e1";
    }

    public String encode(String input){
        int frequency=0;
        char currentChar = input.charAt(0);
        StringBuilder encodeString = new StringBuilder();
        for(char ch: input.toCharArray()){
            if(ch == currentChar){
                frequency++;
            }else{
                encodeString.append(currentChar)
                        .append(frequency);
                frequency=0;
                currentChar = ch;
            }
        }
        encodeString.append(currentChar)
                .append(frequency);
        return encodeString.toString();
    }

    public String decode(String input){

        int index=0;
        StringBuilder decodeString = new StringBuilder();
        char currentChar = input.charAt(0);
        int skipChars = 0;
        for(char ch: input.toCharArray()){
            index++;
            if(skipChars >0){
                skipChars--;
                continue;
            }
            if(ch >= 'a' && ch <= 'z'){
                currentChar = ch;
            }else{
                String number = String.valueOf(ch);
                for(char num: input.substring(index)
                        .toCharArray()){
                    if(num >= '0' && num <= '9'){
                        number = number.concat(String.valueOf(num));
                        skipChars++;
                    }else{
                        break;
                    }
                }
                decodeString.append(String
                        .format("%"+number+"c",currentChar)
                        .replace(' ', currentChar));
            }

        }
        return decodeString.toString();
    }

    public static void main(String[] args) {
        StringUtil stringUtil = new StringUtil();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the input string:");
        String input = scanner.nextLine();
        String output = stringUtil.encode(input);
        System.out.println("Encoded String: "+output);
        System.out.println("Decoded String: "+
                stringUtil.decode(output));
    }
}
