import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class CryptarithmeticPuzzle {

	@SuppressWarnings("null")
	public static void main(String args[]){

		Scanner inputString = new Scanner( System.in );

		String firstString;
		String secondString;
		String resultString;


		System.out.print("Enter first input string: ");
		firstString = inputString.next( );
		if(Pattern.matches("[a-zA-Z]+", firstString)){ 
			firstString = firstString.toLowerCase();
		}else{
			System.out.println("ERROR: Enter a string");
			System.out.print("Enter first input string: ");
			firstString = inputString.next( );
			firstString = firstString.toLowerCase();
		}

		System.out.print("Enter second input string: ");
		secondString = inputString.next( );
		if(Pattern.matches("[a-zA-Z]+", secondString)){ 
			secondString = secondString.toLowerCase();
		}else{
			System.out.println("ERROR: Enter a string");
			System.out.print("Enter second input string: ");
			secondString = inputString.next( );
			secondString = secondString.toLowerCase();
		}

		System.out.print("Enter result input string: ");
		resultString = inputString.next( );
		if(Pattern.matches("[a-zA-Z]+", resultString)){ 
			resultString = resultString.toLowerCase();
		}else{
			System.out.println("ERROR: Enter a string");
			System.out.print("Enter result input string: ");
			resultString = inputString.next( );
			resultString = resultString.toLowerCase();
		}

		String full_name;
		full_name = firstString + " " + secondString+ " " + resultString;
		System.out.println("You are " + full_name);

		char [] firstArr = new char[firstString.length()];
		char [] secondArr = new char[secondString.length()];
		char [] resultArr = new char[resultString.length()];

		HashSet<Character> h1 = new HashSet<Character>(), h2 = new HashSet<Character>(), h3 = new HashSet<Character>();
		HashSet<Character> u1 = new HashSet<Character>(), c1 = new HashSet<Character>();


		for(int idx = 0; idx < firstString.length(); idx++){
			firstArr[idx] = firstString.charAt(idx);
			h1.add(firstString.charAt(idx));
		}
		for(int jdx = 0; jdx < secondString.length(); jdx++){
			secondArr[jdx] = secondString.charAt(jdx);
			h2.add(secondString.charAt(jdx));
		}
		for(int kdx = 0; kdx < resultString.length(); kdx++){
			resultArr[kdx] = resultString.charAt(kdx);
			h3.add(resultString.charAt(kdx));
		}
		
		StringBuffer commonSB = new StringBuffer();
		StringBuffer uniqueSB = new StringBuffer();

		for(Character i : h1){
			if(!h2.contains(i) || !h3.contains(i)){
				u1.add(i);
				uniqueSB.append(i);
			}else{
				c1.add(i);
				commonSB.append(i);
			}
		}

		for(Character i : h2){
			if(!h1.contains(i) || !h3.contains(i)){
				if(!u1.contains(i)){
					uniqueSB.append(i);
					u1.add(i);
				}
			}else{
				if(!c1.contains(i)){
					commonSB.append(i);
					c1.add(i);
				}
			}
		}

		for(Character i : h3){
			if(!h1.contains(i) || !h2.contains(i)){
				if(!u1.contains(i)){
					u1.add(i);
					uniqueSB.append(i);
				}
			}else{
				if(!c1.contains(i)){
					commonSB.append(i);
					c1.add(i);
				}
			}
		}
		
		System.out.println("Common:"+commonSB.toString().replace(" ", "")); 
		System.out.println("Unique:"+uniqueSB.toString().replace(" ", "")); 

		int totalVariableLength = commonSB.length() + uniqueSB.length();
		int resultStringLength = resultArr.length;
		int longestString = 0; 
		if(firstArr.length > secondArr.length){
			longestString = firstArr.length;
		}else{
			longestString = secondArr.length;
		}
		//Constrains
		//Max Distinct variable from inputString and resultSring <= 10
		if(totalVariableLength > 10){
			System.out.println("Error: There are more than 10 variables."); 
			System.exit(0);
		}
		System.out.println("h1 Length " +firstArr.length); 
		System.out.println("h2 Length " +secondArr.length); 
		System.out.println("h3 Length " +resultStringLength); 
	 	System.out.println("Longest " +longestString); 
		
		//resultString length should be equal to or + 1 the longest inputString
		if( !(resultStringLength == longestString || resultStringLength == (longestString + 1))){
			System.out.println("Result String Length Error: There are no results for the given problem. "); 
			System.exit(0);
		}
		
		System.out.println("DONE!"); 

	}
}
