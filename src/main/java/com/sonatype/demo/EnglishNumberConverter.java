package com.sonatype.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnglishNumberConverter implements NumberConverter {

	private Map<String, String> numbers = new HashMap<String, String>();
	private Map<Integer, String> bigNumbers = new HashMap<Integer, String>();

	public EnglishNumberConverter() {
		numbers.put("-", "negative");
		numbers.put("0", "zero");
		numbers.put("1", "one");
		numbers.put("2", "two");
		numbers.put("3", "three");
		numbers.put("4", "four");
		numbers.put("5", "five");
		numbers.put("6", "six");
		numbers.put("7", "seven");
		numbers.put("8", "eight");
		numbers.put("9", "nine");
		numbers.put("10", "ten");
		numbers.put("11", "eleven");
		numbers.put("12", "twelve");
		numbers.put("13", "thirteen");
		numbers.put("14", "fourteen");
		numbers.put("15", "fifteen");
		numbers.put("16", "sixteen");
		numbers.put("17", "seventeen");
		numbers.put("18", "eighteen");
		numbers.put("19", "nineteen");
		numbers.put("20", "twenty");
		numbers.put("30", "thirty");
		numbers.put("40", "forty");
		numbers.put("50", "fifty");
		numbers.put("60", "sixty");
		numbers.put("70", "seventy");
		numbers.put("80", "eighty");
		numbers.put("90", "ninety");
		numbers.put("100", "hundred");

		bigNumbers.put(1, "thousand");
		bigNumbers.put(2, "million");
		bigNumbers.put(3, "billion");
		bigNumbers.put(4, "trillion");
		bigNumbers.put(5, "quadrillion");
		bigNumbers.put(6, "quintillion");
		bigNumbers.put(7, "sextillion");
		bigNumbers.put(8, "septillion");
		bigNumbers.put(9, "octillion");
	}

	public String convertToWords(String str) {
		String text = "";
		Boolean isNegative = false;
		if ("0".equals(str)) {
			text = numbers.get("0");
		}
		else {
			if (str == null || str.trim().length() == 0) {
				throw new RuntimeException("Missing parameter. Integer is required.");
			}
			Boolean isValid = validateNumber(str);
			if (!isValid) {
				throw new RuntimeException("Invalid parameter. Only integers are allowed");
			}
			else {
				if (str.indexOf("-") == 0) {
					isNegative = true;
					str = str.substring(1);
				}
				if (str.length() > 30) {
					throw new RuntimeException("Invalid parameter. Number must be smaller than 1 nonillion");
				}
				List<String> parts = getParts(str);
				while (parts.size() > 0) {
					Boolean useAnd = (str.length() > 2) && (parts.size() == 1);
					String partText = translatePart(parts.get(0), useAnd);
					if (partText.length() > 0) {
						text += ' ' + partText;
					}
					parts.remove(0);
					if (parts.size() > 0 && partText.length() > 0) {
						text += ' ' + bigNumbers.get(parts.size());
					}
				}
				if (isNegative) {
					text = numbers.get("-") + ' ' + text;
				}
			}
		}

		return capitalize(text.replaceAll("  ", " ").trim());
	}
	
	private Boolean validateNumber(String str) {
		return str.matches("^-?[123456789]\\d*$") || str.matches("^0$");
	}
	
	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	private Boolean isNullOrZero(String str) {
		return (str == null) || (Integer.parseInt(str) == 0);
	}

	private List<String> getParts(String numberString) {
		List<String> partList = new ArrayList<String>();
		int firstPartLen = numberString.length() % 3;
		int fullPartCount = numberString.length() / 3;

		if (firstPartLen > 0) {
			partList.add(numberString.substring(0, firstPartLen));
		}

		for (int i = 0; i < fullPartCount; i++) {
			int startIndex = firstPartLen + (i * 3);
			partList.add(numberString.substring(startIndex, startIndex + 3));
		}

		return partList;
	}
	
	private String translatePart(String part, boolean useAnd) {
		String text = "";
		String hundreds = null;
		String tens = null;
		String ones = null;
		
		while((part.length() > 0) && (part.charAt(0) == '0')) {
			part = part.substring(1);
		}
		
		if (part.length() == 3) {
			hundreds = part.substring(0, 1);
			part = part.substring(1);
		}
		if (part.length() == 2) {
			tens = part.substring(0,1);
			if ("1".equals(tens)) {
				tens = part.substring(0,2);
				part = part.substring(2);
			}
			else {
				tens += "0";
				part = part.substring(1);
			}
		}
		if (part.length() == 1) {
			ones = part.substring(0,1);
		}
		
		Boolean hasAnd = useAnd && (!isNullOrZero(tens) || !isNullOrZero(ones));
		
		if (!isNullOrZero(hundreds)) {
			text += numbers.get(hundreds) + " " + numbers.get("100");
		}
		if (hasAnd) {
			text += " and";
		}
		if (!isNullOrZero(tens)) {
			text += " " + numbers.get(tens);
		}
		if (!isNullOrZero(ones)) {
			text += " " + numbers.get(ones);
		}
		return text.replaceAll("  ", " ").trim();
	}

}
