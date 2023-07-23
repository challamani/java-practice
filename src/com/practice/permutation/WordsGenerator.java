package com.practice.permutation;


import java.util.ArrayList;
import java.util.Scanner;

public class WordsGenerator {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		char[] letters = sc.next().toCharArray();
		int len = letters.length;
		letters = sortLetters(letters);

		ArrayList<String> words = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			for (String str : preparePermutationStr(letters[i], getCharsExcludeIndexI(i, letters))) {
				words.add(str);
			}
		}

		// System.out.println(words);
		for (String str : words) {
			System.out.println(str);
		}
		sc.close();
	}

	public static char[] getCharsExcludeIndexI(int indexI, char[] letters) {
		char[] newLetters = new char[letters.length - 1];
		int newIndex = 0;

		for (int i = 0; i < letters.length; i++) {
			if (indexI != i) {
				newLetters[newIndex] = letters[i];
				newIndex++;
			}
		}

		return newLetters;
	}

	public static String[] preparePermutationStr(char ch, char[] letters) {

		if (letters.length == 1) {
			return new String[] { ch + "" + letters[0] };
		} else {

			ArrayList<String> strList = new ArrayList<String>();
			for (int i = 0; i < letters.length; i++) {
				String[] str = preparePermutationStr(letters[i], getCharsExcludeIndexI(i, letters));
				for (String s : str) {
					strList.add(ch + "" + s);
				}
			}
			return strList.toArray(new String[strList.size()]);
		}
	}

	public static char[] sortLetters(char[] letters) {
		for (int i = 0; i < letters.length - 1; i++) {

			for (int j = i + 1; j < letters.length; j++) {
				if (letters[i] > letters[j]) {
					char ch = letters[i];
					letters[i] = letters[j];
					letters[j] = ch;
				}
			}
		}

		return letters;
	}
}

