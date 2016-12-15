import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


public class CryptarithmeticPuzzle {
	
	static Scanner inputString = new Scanner( System.in );

	static String firstString;
	static String secondString;
	static String resultString;

	static int domain[] = {0,1,2,3,4,5,6,7,8,9};

	static char [] firstArr = new char[0];
	static char [] secondArr = new char[0];
	static char [] resultArr = new char[0];
	
	static ArrayList<Character> uniqueAL = new ArrayList<Character>();
	static ArrayList<Character> commonAL = new ArrayList<Character>();
	static ArrayList<Character> te = new ArrayList<Character>();

	static HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
	static int firstInput,secondInput,thirdInput,count=0;
	static boolean solutionfound=false;
	static ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
	static ArrayList<Integer> kevin = new ArrayList<Integer>();
	static ArrayList<Integer> shah = new ArrayList<Integer>();
	static ArrayList<Integer> bharat = new ArrayList<Integer>();
	
	public static void main(String args[]){
		getInput();
		unique();
		validation();
		System.out.println("Calculating. Please wait...");
		calculate();
		System.out.println("DONE!"); 
	}
	
	
	public static void getInput(){
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
	}
	
	public static void unique(){
		HashSet<Character> h1 = new HashSet<Character>(), h2 = new HashSet<Character>(), h3 = new HashSet<Character>();
		HashSet<Character> u1 = new HashSet<Character>(), c1 = new HashSet<Character>();

		firstArr = new char[firstString.length()];
		secondArr = new char[secondString.length()];
		resultArr = new char[resultString.length()];

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

		for(Character i : h1){
			if(!h2.contains(i) || !h3.contains(i)){
				u1.add(i);
				uniqueAL.add(i);
			}else{
				c1.add(i);
				commonAL.add(i);
			}
		}

		for(Character i : h2){
			if(!h1.contains(i) || !h3.contains(i)){
				if(!u1.contains(i)){
					uniqueAL.add(i);
					u1.add(i);
				}
			}else{
				if(!c1.contains(i)){
					commonAL.add(i);
					c1.add(i);
				}
			}
		}
		for(Character i : h3){
			if(!h1.contains(i) || !h2.contains(i)){
				if(!u1.contains(i)){
					u1.add(i);
					uniqueAL.add(i);
				}
			}else{
				if(!c1.contains(i)){
					commonAL.add(i);
					c1.add(i);
				}
			}
		}
	//	System.out.println("New Common"+commonAL.toString());
	//	System.out.println("New Unique"+uniqueAL.toString());
	}
	
	public static void validation(){
		int totalVariableLength = commonAL.size() + uniqueAL.size();
		
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
			System.out.println("Exit.");
			System.exit(0);
		}
		/*
		System.out.println("h1 Length " +firstArr.length); 
		System.out.println("h2 Length " +secondArr.length); 
		System.out.println("h3 Length " +resultStringLength); 
	 	System.out.println("Longest " +longestString); 
		*/
		//resultString length should be equal to or + 1 the longest inputString
		if( !(resultStringLength == longestString || resultStringLength == (longestString + 1))){
			System.out.println("Result String Length Error: There are no results for the given problem. "); 
			System.out.println("Exit.");
			System.exit(0);
		}
	}
	
	public static void calculate(){
		te.addAll(uniqueAL);
		te.addAll(commonAL);
		Collections.sort(te);

		permutations(domain, 0);
		
		for(int i=0;i<permutations.size();i++){
			for(int j=0;j<te.size();j++){
				hm.put(te.get(j),permutations.get(i).get(j));			
			}

			firstInput = getInteger(firstString);
			secondInput = getInteger(secondString);
			thirdInput = getInteger(resultString);
			if(thirdInput==firstInput+secondInput && 
					getLengthOfInt(firstInput)==firstString.length() && 
					getLengthOfInt(secondInput)==secondString.length() && 
					getLengthOfInt(thirdInput)==resultString.length()){
				
				solutionfound=true;
				if(!kevin.contains(firstInput)){
					System.out.println(firstString+":"+firstInput+"  "+secondString+":"+secondInput+"  "+resultString+":"+thirdInput);
					count++;
				}
				kevin.add(firstInput);
			}	
		}
		System.out.println("Number of Solutions: "+count);
	
		if(!solutionfound)
			System.out.println("No solution found!");
	}
	
	public static void permutations(int []a, int k){
		if(k==a.length){
			ArrayList<Integer> perm = new ArrayList<Integer>();
			for(int i=0;i<a.length;i++){
				perm.add(a[i]);
			}
			permutations.add(perm);
		}else{	
			for (int i = k; i < a.length; i++){
				int temp=a[k];
				a[k]=a[i];
				a[i]=temp;
				permutations(a,k+1);
				temp=a[k];
				a[k]=a[i];
				a[i]=temp;
			}
		}
	}
	
	public static void iterateHashMap(){	
		for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
		    char key = entry.getKey();
		    int value = entry.getValue();
		    
		    System.out.println("Key:"+key+" Value:"+value);
		}
	}
	
	public static int getInteger(String str){
		String temp="";
		for(int i=0;i<str.length();i++){
			temp=temp+hm.get(str.charAt(i));
		}
		return Integer.parseInt(temp);
	}
	
	public static int getLengthOfInt(int idx){
		 return String.valueOf(idx).length();
	}
}
