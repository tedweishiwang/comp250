package assignments2017.A2PostedCode;

/*
Name:Weishi Wang
ID:260540022
 */

import java.util.Stack;
import java.util.ArrayList;

public class Expression  {
	private ArrayList<String> tokenList;

	//  Constructor    
	/**
	 * The constructor takes in an expression as a string
	 * and tokenizes it (breaks it up into meaningful units)
	 * These tokens are then stored in an array list 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException{
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();
		
		//ADD YOUR CODE BELOW HERE
		//..
			token.append(expressionString);
			
			int i = 0;
		
			String tokenStr="";
			String numberStr="";
	

			
			while (i < token.length()) {
			int thischar = token.charAt(i);
			if (thischar==32) {
				token.deleteCharAt(i);

			}
			
			else {
			if (thischar<48 || thischar>57 ) {

				if(numberStr.isEmpty()) {
					
				}
				else {
					tokenList.add(numberStr);
				}
			
		
				tokenStr=""+token.charAt(i);
				tokenList.add(tokenStr);
				numberStr="";
				
				i++;
			
			}
			else if (thischar>=48 && thischar<=57) {
				numberStr=numberStr+token.charAt(i);
				if (i==token.length()-1) {
					tokenList.add(numberStr);
				}
				
				i++;
			}	
			}
			}
			
			for(int j =0;j<tokenList.size()-1;j++) {
				if (tokenList.get(j).equals("+") && tokenList.get(j+1).equals("+")) {
					tokenList.remove(j);
					tokenList.remove(j);
					tokenList.add(j,"++");
				}
			}
	
			for(int j =0;j<tokenList.size()-1;j++) {
				if (tokenList.get(j).equals("-") && tokenList.get(j+1).equals("-")) {
					tokenList.remove(j);
					tokenList.remove(j);
					tokenList.add(j,"--");
					
				}
			}
	
		}
		//..
		//ADD YOUR CODE ABOVE HERE
	

	/**
	 * This method evaluates the expression and returns the value of the expression
	 * Evaluation is done using 2 stack ADTs, operatorStack to store operators
	 * and valueStack to store values and intermediate results.
	 * - You must fill in code to evaluate an expression using 2 stacks
	 */
	public Integer eval(){
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();
		
		//ADD YOUR CODE BELOW HERE
		//..
		int note = 0;
		int result=0;
		Expression element;	
		int size = tokenList.size();
		for (int i=0; i<size;i++) {
			
		element=new Expression(tokenList.get(i));
		
		
		if (element.isInteger(tokenList.get(i))) {
			Integer value = Integer.valueOf(tokenList.get(i));
			valueStack.push(value);
		}
		if (tokenList.get(i).equals("(")) {
		
		}

		
		if (tokenList.get(i).equals("+")||tokenList.get(i).equals("-")||tokenList.get(i).equals("*")||tokenList.get(i).equals("/")||tokenList.get(i).equals("++")||tokenList.get(i).equals("--")||tokenList.get(i).equals("]")) {
			if (tokenList.get(i).equals("++")) {
				valueStack.push(1);
				operatorStack.push("+");
			}
			else if (tokenList.get(i).equals("--")) {
				valueStack.push(-1);
				operatorStack.push("+");
			}
			
			else if(tokenList.get(i).equals("]")) {
				operatorStack.push("]");
				tokenList.remove(i);
				tokenList.add(i,")");
				note=1;
				}
			
			
			else {operatorStack.push(tokenList.get(i));}
		}
		
		
		
		
		if (tokenList.get(i).equals(")")) {
			
			if (operatorStack.isEmpty()) {
				break;
			}
			
			String opera = operatorStack.pop();

			if (valueStack.size()>1) {
				int numb1 = valueStack.pop();
				int numb2 = valueStack.pop();
				if (opera.equals("+")) {
					result = numb2 + numb1;
			
				}
				else if (opera.equals("-")) {
				result = numb2 - numb1;
				
				}
				else if (opera.equals("*")) {
				result = numb2 * numb1;
				}
				else if (opera.equals("/")) {
				result = numb2/numb1;
				}
				else if (opera.equals("]")) {
					valueStack.push(numb2);
					result=Math.abs(numb1);

				}

			}
			
			else if (valueStack.size()==1&&opera.equals("]")) {
	
					result=Math.abs(valueStack.pop());
			
		}
			valueStack.push(result);
			}
		
		if(note==1) {
			tokenList.remove(i);
			tokenList.add(i,"]");
			note=0;
			}
		
		//..
		//ADD YOUR CODE ABOVE HERE
		
		}
		
	
		int finalresult = valueStack.pop();
		return finalresult;

	
	}

	//Helper methods
	/**
	 * Helper method to test if a string is an integer
	 * Returns true for strings of integers like "456"
	 * and false for string of non-integers like "+"
	 * - DO NOT EDIT THIS METHOD
	 */
	public boolean isInteger(String element){
		try{
			Integer.valueOf(element);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList.
	 * - DO NOT EDIT THIS METHOD    
	 */

	@Override
	public String toString(){	
		String s = new String(); 
		for (String t : tokenList )
			s = s + "~"+  t;
		return s;		
	}

}

