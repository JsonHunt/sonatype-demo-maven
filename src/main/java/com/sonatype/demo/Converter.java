package com.sonatype.demo;

public class Converter {
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("This program requires 1 argument: Number to be translated");
			return;
		}
		
		NumberConverter conv = new EnglishNumberConverter();
		System.out.println("Translating " + args[0]);
		String text = conv.convertToWords(args[0]);
		System.out.println(text);
		
	}

}
