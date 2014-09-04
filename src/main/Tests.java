package main;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;


public class Tests {

	SequenceAligner _seq;
	private long timeTakenMS;

	@Test
	public void test1() {
		String string1 = "AAA";
		String string2 = "BBB";

		String result1 = "AAA";
		String result2 = "BBB";
		int resultingPen = -3;

		Given_the_strings(string1, string2);
		After_aligning();

		The_resulting_strings_are_as_expected(result1, result2);
		The_resulting_cost_is_as_expected(resultingPen);
	}

	@Test
	public void equalStrings(){
		String string1 = "AAAAAAAAAAAAAAAAAAAA";
		String string2 = "AAAAAAAAAAAAAAAAAAAA";

		String result1 = "AAAAAAAAAAAAAAAAAAAA";
		String result2 = "AAAAAAAAAAAAAAAAAAAA";
		int resultingPen = 20;

		Given_the_strings(string1, string2);
		After_aligning();

		The_resulting_strings_are_as_expected(result1, result2);
		The_resulting_cost_is_as_expected(resultingPen);
	}

	@Test
	public void reverseString(){
		String string1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String string2 = "ZYXWVUTSRQPONMLKJIHGFEDCBA";

		String result1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String result2 = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
		int resultingPen = -26;

		Given_the_strings(string1, string2);
		After_aligning();

		The_resulting_strings_are_as_expected(result1, result2);
		The_resulting_cost_is_as_expected(resultingPen);
	}

	@Test
	public void assigCaseTest(){
		String string1 = "GATCGGCAT";
		String string2 = "CAATGTGAATC";

		String result1 = "GATCG_GCAT_";
		String result2 = "CAATGTGAATC";
		int resultingPen = -3;

		Given_the_strings(string1, string2);
		After_aligning();

		The_resulting_strings_are_as_expected(result1, result2);
		The_resulting_cost_is_as_expected(resultingPen);
	}

	@Test
	public void patternTwo(){
		String string1 = "ABABAB";
		String string2 = "BABABA";

		String result1 = "ABABAB_";
		String result2 = "_BABABA";
		int resultingPen = 1;

		Given_the_strings(string1, string2);
		After_aligning();

		The_resulting_strings_are_as_expected(result1, result2);
		The_resulting_cost_is_as_expected(resultingPen);
	}

	@Test
	public void patternThree(){
		String string1 = "ABCABCABC";
		String string2 = "BCABCABCA";

		String result1 = "ABCABCABC_";
		String result2 = "_BCABCABCA";
		int resultingPen = 4;

		Given_the_strings(string1, string2);
		After_aligning();

		The_resulting_strings_are_as_expected(result1, result2);
		The_resulting_cost_is_as_expected(resultingPen);
	}

	//The following is a test used for benchmarking
	/*@Test
	public void randomStringBenchmarking(){
		for(int i = 0; i < 300; i++){
			String string1 = randomString(i*10);
			String string2 = randomString(i*10);

			int total = 0;
			for(int j = 0; j < 3; j++){
				Given_the_strings(string1, string2);
				After_aligning();
				total+=timeTakenMS;
			}

			System.out.println(string1.length()*string2.length()+","+(int)(total/3));
		}
	}*/

	public String randomString( int len )
	{
		Random rnd = new Random();
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ )
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}

	private void Given_the_strings(String string1, String string2) {
		_seq = new SequenceAligner();
		_seq.setStrings(string1, string2);
	}

	private void After_aligning() {
		long start = System.currentTimeMillis();
		_seq.align();
		long end = System.currentTimeMillis();
		timeTakenMS = end-start;
	}

	private void The_resulting_strings_are_as_expected(String result1, String result2) {
		//assertEquals(result1, _seq.getRes1());
		assertEquals(result2, _seq.getRes2());
	}

	private void The_resulting_cost_is_as_expected(int resultingCost) {
		assertEquals(resultingCost, _seq.getTotalPen());
	}




}
