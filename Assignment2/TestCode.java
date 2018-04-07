package assignments2017.A2PostedCode;

import java.util.ArrayList;
import java.util.Stack;

public class TestCode {
	
	
	public static void main(String[] args) {
	Stack<String> equation = new Stack<String>();
	Stack<String> operatorStack = new Stack<String>();
	Stack<Integer> valueStack = new Stack<Integer>();
	
	//ADD YOUR CODE BELOW HERE
	//..
	ArrayList<String> tokenList;;
	tokenList = new ArrayList<>();
	
	tokenList.add("(");
	tokenList.add("(");
	tokenList.add("7");
	tokenList.add("-");
	tokenList.add("2");
	tokenList.add(")");
    tokenList.add("*");
	tokenList.add("(");
	tokenList.add("++");
	tokenList.add("(");
	tokenList.add("4");
	tokenList.add("-");
	tokenList.add("3");
	tokenList.add(")");
	tokenList.add(")");
	tokenList.add(")");

	Expression element;
	for(int i =tokenList.size()-1; i>=0;i--) {
	element = new Expression(tokenList.get(i));
	if (element.isInteger(tokenList.get(i))) {
		Integer value = Integer.valueOf(tokenList.get(i));
		valueStack.push(value);
	
	}
	else {

			operatorStack.push(tokenList.get(i));
			
		
	}
	
	equation.push(tokenList.get(i));
	
	}
	}
}
	
	
	
	
	/*int result=0;
	int mark =0;
	String storedOperator="";
	int size = operatorStack.size();
	for (int i=0;i<size;i++) {
	String operator = operatorStack.pop();
	
	
	if (operator.equals("+")) {
		valueStack.push(valueStack.pop()+valueStack.pop());
	}

	
	else if (operator.equals("-")) {
		valueStack.push(valueStack.pop()-valueStack.pop());
		
	}
	
	else if (operator.equals("*")) {
		valueStack.push(valueStack.pop()*valueStack.pop());
	}
	else if (operator.equals("/")) {
		valueStack.push(valueStack.pop()/valueStack.pop());
	}
	
	else if(operator.equals("]")) {
		valueStack.push(Math.abs(valueStack.pop()));
	}
	
	else if(operator.equals("revMinus")) {
		valueStack.push(-valueStack.pop()+valueStack.pop());
	}
	
	else if(operator.equals("++")) {
		valueStack.push(1);
		operatorStack.push("+");
		operatorStack.push(")");
		size = operatorStack.size()+1;
	}
	
	else if(operator.equals("--")) {
		valueStack.push(1);
		operatorStack.push("revMinus");
		operatorStack.push(")");
		size = operatorStack.size()+1;
	}
	
	if(result!=0 ) {
		valueStack.push(result);
		operatorStack.push(storedOperator);
		result=0;
	}
	
	
	 if (operator.equals(")") && valueStack.size()!=1 && operatorStack.size()>2) {

		result = result + valueStack.pop();
		storedOperator = operatorStack.pop();

	}
System.out.println(valueStack);
System.out.println(operatorStack);
	}

	
	
Expression lol = new Expression ("((7-2)*(++(4-3)))");
	System.out.println(lol.eval());
	*/
			
