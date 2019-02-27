package com.sonatype.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EngConverterTest {
	
	private NumberConverter converter = new EnglishNumberConverter();

	@Test
	void testConvertToWords() {
		Throwable exception = assertThrows(RuntimeException.class, ()-> converter.convertToWords("nan"));
		assertEquals(exception.getMessage(), "Invalid parameter. Only integers are allowed");
		
		exception = assertThrows(RuntimeException.class, ()-> converter.convertToWords("000"));
		assertEquals(exception.getMessage(), "Invalid parameter. Only integers are allowed");
		
		exception = assertThrows(RuntimeException.class, ()-> converter.convertToWords(""));
		assertEquals(exception.getMessage(), "Missing parameter. Integer is required.");
		
		assertEquals(converter.convertToWords("-1"), "Negative one");
		
		assertEquals(converter.convertToWords("0"), "Zero");
		assertEquals(converter.convertToWords("1"), "One");
		assertEquals(converter.convertToWords("2"), "Two");
		assertEquals(converter.convertToWords("3"), "Three");
		assertEquals(converter.convertToWords("4"), "Four");
		assertEquals(converter.convertToWords("5"), "Five");
		assertEquals(converter.convertToWords("6"), "Six");
		assertEquals(converter.convertToWords("7"), "Seven");
		assertEquals(converter.convertToWords("8"), "Eight");
		assertEquals(converter.convertToWords("9"), "Nine");
		assertEquals(converter.convertToWords("10"), "Ten");
		assertEquals(converter.convertToWords("11"), "Eleven");
		assertEquals(converter.convertToWords("12"), "Twelve");
		assertEquals(converter.convertToWords("13"), "Thirteen");
		assertEquals(converter.convertToWords("14"), "Fourteen");
		assertEquals(converter.convertToWords("15"), "Fifteen");
		assertEquals(converter.convertToWords("16"), "Sixteen");
		assertEquals(converter.convertToWords("17"), "Seventeen");
		assertEquals(converter.convertToWords("18"), "Eighteen");
		assertEquals(converter.convertToWords("19"), "Nineteen");
		assertEquals(converter.convertToWords("20"), "Twenty");
		assertEquals(converter.convertToWords("21"), "Twenty one");
		assertEquals(converter.convertToWords("30"), "Thirty");
		assertEquals(converter.convertToWords("40"), "Forty");
		assertEquals(converter.convertToWords("50"), "Fifty");
		assertEquals(converter.convertToWords("60"), "Sixty");
		assertEquals(converter.convertToWords("70"), "Seventy");
		assertEquals(converter.convertToWords("80"), "Eighty");
		assertEquals(converter.convertToWords("90"), "Ninety");
		assertEquals(converter.convertToWords("100"), "One hundred");
		assertEquals(converter.convertToWords("101"), "One hundred and one");
		assertEquals(converter.convertToWords("110"), "One hundred and ten");
		assertEquals(converter.convertToWords("111"), "One hundred and eleven");
		assertEquals(converter.convertToWords("240"), "Two hundred and forty");
		assertEquals(converter.convertToWords("242"), "Two hundred and forty two");
		
		assertEquals(converter.convertToWords("1000"), "One thousand");
		assertEquals(converter.convertToWords("1200"), "One thousand two hundred");
		assertEquals(converter.convertToWords("10000"), "Ten thousand");
		assertEquals(converter.convertToWords("10010"), "Ten thousand and ten");
		assertEquals(converter.convertToWords("10100"), "Ten thousand one hundred");
		
		assertEquals(converter.convertToWords("100000"), "One hundred thousand");
		
		assertEquals(converter.convertToWords("1000000"), "One million");
		assertEquals(converter.convertToWords("1000000000"), "One billion");
		assertEquals(converter.convertToWords("1000000000000"), "One trillion");
		assertEquals(converter.convertToWords("1000000000000000"), "One quadrillion");
		
		assertEquals(converter.convertToWords("123"), "One hundred and twenty three");
		assertEquals(converter.convertToWords("211"), "Two hundred and eleven");
		assertEquals(converter.convertToWords("3406"), "Three thousand four hundred and six");
		
		assertEquals(converter.convertToWords("1001001001001000"), "One quadrillion one trillion one billion one million one thousand");
		assertEquals(converter.convertToWords("56123456"), "Fifty six million one hundred twenty three thousand four hundred and fifty six");
		
	}

}
