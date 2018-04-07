package assignments2017.A2PostedCode;

import java.util.ArrayList;
import java.util.Stack;

public class secondTest {
	
	public static void main(String[] args) {
		
	
	Stack<String> operatorStack = new Stack<String>();
	Stack<Integer> valueStack = new Stack<Integer>();
	
	//ADD YOUR CODE BELOW HERE
	//..
	ArrayList<String> tokenList;;
	tokenList = new ArrayList<>();
	
	tokenList.add("(");
	tokenList.add("(");
	tokenList.add("--");
	tokenList.add("(");
	tokenList.add("2");
	tokenList.add("+");
	tokenList.add("3");
	tokenList.add(")");
	tokenList.add(")");
	tokenList.add("-");
	tokenList.add("1");
	tokenList.add(")");

//	System.out.println(tokenList);
	
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
		
		else {operatorStack.push(tokenList.get(i));}
	}
	if (tokenList.get(i).equals(")")) {
		String opera = operatorStack.pop();

		if (valueStack.size()>1) {
			int numb1 = valueStack.pop();
			int numb2 = valueStack.pop();
			if (opera.equals("+")) {
				result = numb2 + numb1;
		
		}
		if (opera.equals("-")) {
			result = numb2 - numb1;
			
		}
		if (opera.equals("*")) {
			result = numb2 * numb1;
		}
		if (opera.equals("/")) {
			result = numb2/numb1;
		}
		
		valueStack.push(result);
		}
		
		
	}
	
	if (tokenList.get(i).equals("]")) {
		valueStack.push(Math.abs(valueStack.pop()));
		
		
	}
	
	}
	//System.out.println(valueStack);
	
	
	
	Expression testing = new Expression("[3+2]");
	System.out.println(testing.eval());
	
	Expression t = new Expression("(++(++[(20*38)]))");
	System.out.println(t.eval());
	//System.out.println(Math.abs(1));
	

	
	
	}
}



	
	

