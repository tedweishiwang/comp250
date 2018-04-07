package assignments2017.a1posted;

/*
 *   STUDENT NAME      :  Weishi Wang
 *   STUDENT ID        :  260540022
 *   
 *   If you have any issues that you wish the T.A.s to consider, then you
 *   should list them here.   If you discussed on the assignment in depth 
 *   with another student, then you should list that student's name here.   
 *   We insist that you each write your own code.   But we also expect 
 *   (and indeed encourage) that you discuss some of the technical
 *   issues and problems with each other, in case you get stuck.    

 *   
 */

import java.util.LinkedList;

public class NaturalNumber {

	/*
	 * 
	 * If the list has N coefficients, then then the number is represented is a
	 * polynomial: coefficients[N-1] base^{N-1} + ... coefficients[1] base^{1} +
	 * coefficients[0] where base has a particular value and the coefficients are in
	 * {0, 1, ... base - 1}
	 * 
	 * For any base and any positive integer, the representation of that positive
	 * integer as a sum of powers of that base is unique.
	 * 
	 * We require that the coefficient of the largest power is non-zero. For
	 * example, '354' is a valid representation (which we call
	 * "three hundred fifty four") but '0354' is not.
	 * 
	 */

	private int base;

	LinkedList<Integer> coefficients;

	public boolean compareTo;

	// Constructors

	NaturalNumber(int base) {

		// If no string argument is given, then it constructs an empty list of
		// coefficients.

		this.base = base;
		coefficients = new LinkedList<Integer>();
	}

	/*
	 * The constructor builds a LinkedList of Integers where the integers need to be
	 * in {0,1,2,3,4...,base-1}. The string only represents a base 10 number when
	 * the base is given to be 10.
	 */
	NaturalNumber(String sBase, int base) throws Exception {
		int i;
		this.base = base;
		coefficients = new LinkedList<Integer>();
		if ((base < 2) || (base > 10)) {
			System.out.println("constructor error:  base must be between 2 and 10");
			throw new Exception();
		}

		/*
		 * The large integer inputs will be read in as strings with character digits.
		 * These characters will need to be converted to integers. The characters are
		 * represented in ASCII. See the decimal (dec) and character (char) values in
		 * http://www.asciitable.com/ ). The ASCII value of symbol '0' is 48, and the
		 * ASCII value of symbol '1' is 49, etc. So for example to get the numerical
		 * value of '2', we subtract the character value of '0' (48) from the character
		 * value of '2' (50).
		 */

		int l = sBase.length();
		for (int indx = 0; indx < l; indx++) {
			i = sBase.charAt(indx);
			if ((i >= 48) && (i - 48 < base))
				coefficients.addFirst(new Integer(i - 48));
			else {
				System.out.println("constructor error:  all coefficients should be non-negative and less than base");
				throw new Exception();
			}
		}
	}

	/*
	 * Construct a NaturalNumber object for a number that has just one digit in [0,
	 * base).
	 * 
	 * This constructor acts as a helper. It is not called from the Tester class.
	 */

	NaturalNumber(int i, int base) throws Exception {
		this.base = base;
		coefficients = new LinkedList<Integer>();

		if ((i >= 0) && (i < base))
			coefficients.addFirst(new Integer(i));
		else {
			System.out.println("constructor error: all coefficients should be non-negative and less than base");
			throw new Exception();
		}
	}

	/*
	 * The plus method computes this.plus(b), that is, a+b where 'this' is a.
	 * 
	 * If you do not know what the Java keyword 'this' is, then see
	 * https://docs.oracle.com/javase/tutorial/java/javaOO/thiskey.html
	 * 
	 */

	/*
	 * Getter for both the private variables- Added by Navin/Ramchalam for
	 * testGrader
	 */

	public int getBase() {
		return base;
	}

	public LinkedList<Integer> getCoefficients() {
		return coefficients;
	}

	// To perform a+b, call a.plus(b). The parameter second refers to the second
	// operand in a+b, that is, b

	public NaturalNumber plus(NaturalNumber second) throws Exception {

		// initialize the sum as an empty list of coefficients

		NaturalNumber sum = new NaturalNumber(this.base);

		if (this.base != second.base) {
			System.out.println("ERROR: bases must be the same in an addition");
			throw new Exception();
		}

		/*
		 * The plus method must not affect the numbers themselves. So let's just work
		 * with a copy (a clone) of the numbers.
		 */

		NaturalNumber firstClone = this.clone();
		NaturalNumber secondClone = second.clone();

		/*
		 * If the two numbers have a different polynomial order then pad the smaller one
		 * with zero coefficients.
		 */

		int diff = firstClone.coefficients.size() - second.coefficients.size();
		while (diff < 0) { // second is bigger

			firstClone.coefficients.addLast(0);
			diff++;
		}
		while (diff > 0) { // this is bigger
			secondClone.coefficients.addLast(0);
			diff--;
		}

		// ADD YOUR CODE HERE

		// --------- BEGIN SOLUTION (plus) ----------
		//

		// Create a linked list to store the coefficients of the first number, second
		// number and the sum

		LinkedList<Integer> firstCoe;
		LinkedList<Integer> secondCoe;
		LinkedList<Integer> sumCoe;

		// Store the coefficients of each number in linked list form

		firstCoe = firstClone.getCoefficients();

		secondCoe = secondClone.getCoefficients();

		sumCoe = sum.getCoefficients();

		/*
		 * Initialized the carry The index will indicate a specific digit of the number
		 * that we want to do operation
		 */

		int carry = 0;

		/*
		 * for loop to update the index Condition indicates that the loop executes when
		 * the index is smaller than the size of the linked list This will stop the for
		 * loop at the last digit in both numbers
		 */
		// We start with the first digit, so we initialized index as 0
		for (int index = 0; index < firstClone.coefficients.size(); index++) {

			int digit1;                                               // declare an int variable digit1 to store the digit of the first number
			digit1 = firstCoe.get(index); 							 // store the digit of the first number into digit1
			int digit2; 												 // declare an int variable digit2 to store the digit of the second number
			digit2 = secondCoe.get(index); 							 // store the digit of the second number into digit2
			int digitsum; 											 // declare an int variable to store the sum of the digits
			digitsum = digit1 + digit2 + carry; 						 // the sum would be the sum of the two digits and also the carry of the
																	 // previous addition if there is any

			/*
			 * Creat if loop to compare the sum with the base if the sum is smaller than the
			 * base, we simply put the sum in the linked list of sum if the sum is greater
			 * or equal to the base, then we have to subtract the sum by the base, and store
			 * the result in the linked list of sum and as the sum is greater or equal to
			 * the base, we need to give a 1 to the carry, so it will be added to the
			 * addition of the next pair of digits.
			 */

			if (digitsum < this.base) { 								 // if the sum is smaller than the base
				sumCoe.addLast(digitsum); 							 // simply store the digit in the linked list
				carry = 0; 											 // the carry in this case would be 0
			} else { 												 // if the sum is greater than the base
				int rem = digitsum - this.base; 						 // we subtract the sum by the base to obtain the reminder
				carry = 1; 											 // the carry would be 1
				sumCoe.addLast(rem);									 // store the reminder to the linked list
			}
		} 															 // end the for loop

		/*
		 * If there is still a carry at the end of the for loop means that the addition
		 * of the last two digits is greater than the base therefore we need to add the
		 * carry in front
		 */
		if (carry != 0) {
			sumCoe.addLast(carry);
		}
		/*
		 * The code above give us the coefficients of the sum of the two numbers in
		 * linked list form The required output is in String We need to convert the
		 * linked list to the String output
		 */

		String s = new String(); 										// declare a String
		for (Integer coef : sumCoe) 										// create for loop to read each elements in the linked list
			s = coef.toString() + s; 									// store each elements in String form
		sum = new NaturalNumber(s, this.base); 							// input the String and the base into NaturalNumbers class, and obtain
																		// the required output, which is also a String

		// Note 'firstClone' and 'secondClone' have the same size. We add the
		// coefficients
		// term by term. If the last coefficient yields a carry, then we add
		// one more term with the carry.

		// --------- END SOLUTION (plus) ----------

		return sum;
	}

	/*
	 * Slow multiplication algorithm, mentioned in lecture 1. You need to implement
	 * the plus algorithm in order for this to work.
	 * 
	 * 'this' is the multiplicand i.e. a*b = a+a+a+...+a (b times) where a is
	 * multiplicand and b is multiplier
	 */

	public NaturalNumber slowTimes(NaturalNumber multiplier) throws Exception {

		NaturalNumber prod = new NaturalNumber(0, this.base);
		NaturalNumber one = new NaturalNumber(1, this.base);
		for (NaturalNumber counter = new NaturalNumber(0, this.base); counter
				.compareTo(multiplier) < 0; counter = counter.plus(one)) {

			prod = prod.plus(this);
		}
		return prod;
	}

	/*
	 * The multiply method must NOT be the same as what you learned in grade school
	 * since that method uses a temporary 2D table with space proportional to the
	 * square of the number of coefficients in the operands i.e. O(N^2). Instead,
	 * you must write a method that uses space that is proportional to the number of
	 * coefficients i.e. O(N). Your algorithm will still take time O(N^2) however.
	 */

	/*
	 * The multiply method computes this.multiply(b) where 'this' is a.
	 */

	public NaturalNumber times(NaturalNumber multiplicand) throws Exception {

		// initialize product as an empty list of coefficients

		NaturalNumber product = new NaturalNumber(this.base);

		if (this.base != multiplicand.base) {
			System.out.println("ERROR: bases must be the same in a multiplication");
			throw new Exception();
		}

//	    ADD YOUR CODE HERE
		
			// --------------  BEGIN SOLUTION (multiply)  ------------------
			
			/*
			 *           multiplicand
			 *          x  multiplier  (this)
			 *        ---------------
			 *            
			 *   Note we use a helper method.  See below.
			 */

			

		/*
		 * declare the variables
		 * make clone of our input to work with, since we do not want to change the value of our input
		 */
		NaturalNumber first = this.clone();
		NaturalNumber second= multiplicand.clone();

		/*
		 * create linked list to store the coefficients
		 */
		
		LinkedList<Integer> secondCoe;
		LinkedList<Integer> prod_singleCoe;
		

		secondCoe = second.getCoefficients();								//get the linked list coefficient of the second number

		/*
		 * create a loop to do single digit multiplication
		 * for each digit in the multiplicand
		 * then add them togeter with offsets proportional to the index (adding 0's to the linked list to achieve this)
		 */
		for(int index = 0; index<secondCoe.size(); index++) {
			int secondDigit;														
			secondDigit = secondCoe.get(index);									//to get a single digit of second number
			NaturalNumber secondDigitList;										//create a NaturalNumber variable to store this single digit 
			secondDigitList = new NaturalNumber(secondDigit,this.base);			//store this single digit as NaturalNumber
			
			NaturalNumber prod_single;											//create a NaturalNumber to store the product of single digit and first number
			prod_single = new NaturalNumber(this.base);							//create empty space with same base as the first number
			prod_single = first.timesSingleDigit(secondDigitList);				//call the helper method to solve the single digit product
			prod_singleCoe     = prod_single.getCoefficients();					//get the linked list of the result
			for(int j= index;j>0;j--) {											//depending on the index of the second number, give appropriate offset by adding 0's at the end
			prod_singleCoe.addFirst(0);
			}
			/*
			 * Transform the linked list single digit product to the NaturalNumber form
			 * so we can call the plus method and do the addition of each single digit product
			 */
			String s = new String(); 								
			for (Integer coef : prod_singleCoe) 								
				s = coef.toString() + s; 							
			prod_single = new NaturalNumber(s, this.base); 						//get the NaturalNumber form of this single digit product
			product = product.plus(prod_single);									//the total product is the sum of every single digit product
			}
		
		/*
		 * The following is to eliminate the leading 0's in our product
		 * The leading 0's happens because in the plus method, we add 0's in the front to match the size of the linked list with the other number
		 * Now we added 0's at the end to offset the product of single digit, and the 0's in the front will become a leading 0
		 * So we need to eliminate the leading 0's
		 */
		LinkedList<Integer> productCoe;					
		productCoe = product.getCoefficients();									//get the coefficients in linked list form
		while (productCoe.get(productCoe.size()-1)==0) {              			//while the leading number is 0     		
			productCoe.removeLast();												//remove the leading number, until the leading number is not 0
		}
		String s = new String(); 								      			//transform the linked list form of our result to String
		for (Integer coef : productCoe) 								
			s = coef.toString() + s; 							
		product = new NaturalNumber(s, this.base); 								//finally, obtain the NaturalNumber form of our product

		
	//  ---------------  END SOLUTION  (multiply) -------------------
		
		
		return product;
	}


	// -------- BEGIN SOLUTION *helper method* for multiply -----

	/*
	 * 'this' (the caller) will be the multiplicand.
	 */
	public NaturalNumber timesSingleDigit(NaturalNumber second) throws Exception{

		NaturalNumber product_single = new NaturalNumber(this.base);        				//declare an empty Natural Number with the desired base
		NaturalNumber first = this.clone();												//declare a clone so that we do not change the value of the first number		

		// declare the linked lists

		LinkedList<Integer> firstCoe;						
		LinkedList<Integer> secondCoe;
		LinkedList<Integer> prodCoe;														//this linked list is used to store the coefficients of the result

		// Store the coefficients of each number in linked list form

		firstCoe = first.getCoefficients();

		secondCoe = second.getCoefficients();

		prodCoe = product_single.getCoefficients();

		// Initialized the product and the carry
		
		int pro = 0;
		int carry = 0;
		
		/*
		 *  This for loop evaluate digit by digit product
		 */

		for (int index = 0; index < first.coefficients.size(); index++) {

			int digit1;										//a variable to store one digit of the first number
			digit1 = firstCoe.get(index);					//set/update the digit proportional with the index
			int digit2;										//a variable to store the only digit in the second number
			digit2 = secondCoe.get(0); 						//the second number only have one digit at [0]
			pro = digit2 * (digit1);							//store the product of these two digits in pro
			int rem = (pro + carry) % this.base;				//evaluate the remainder of this product, of course we need to add the carry of the previous product to this product
			carry = (pro + carry) / this.base;				//evaluate the carry of this product
			prodCoe.addLast(rem);							//store the remainder in the linked list as our answer					
		}

		/*
		 * if there is still a carry after the last multiplication
		 * we need to add the carry in front to complete our multiplication
		 */
		if (carry != 0) {									
			prodCoe.addLast(carry);
		}
		/*
		 * transform the linked list to a String answer
		 */

		String s = new String(); 							// declare a String
		for (Integer coef : prodCoe) 						// create for loop to read each elements in the linked list
			s = coef.toString() + s; 						// store each elements in String form
		product_single = new NaturalNumber(s, this.base); 	// input the String and the base into NaturalNumbers class,
															// and obtain the required output, which is also a String

		return product_single;
	}

	// END SOLUTION ---------- *helper method* for multiply ---------

	/*
	 * The minus method computes this.minus(b) where 'this' is a, and a > b. If a <
	 * b, then it throws an exception.
	 * 
	 */

	public NaturalNumber minus(NaturalNumber second) throws Exception {

		// initialize the result (difference) as an empty list of coefficients

		NaturalNumber difference = new NaturalNumber(this.base);

		if (this.base != second.base) {
			System.out.println("ERROR: bases must be the same in a subtraction");
			throw new Exception();
		}
		/*
		 * The minus method is not supposed to change the numbers. But the grade school
		 * algorithm sometimes requires us to "borrow" from a higher coefficient to a
		 * lower one. So we work with a copy (a clone) instead.
		 */

		NaturalNumber first = this.clone();

		// You may assume 'this' > second.

		if (this.compareTo(second) < 0) {
			System.out.println("Error: the subtraction a-b requires that a > b");
			throw new Exception();
		}

		// ADD YOUR CODE HERE

		// --------- BEGIN SOLUTION (minus) ----------

		/*
		 * Same as before, if the number of digits in both numbers is not equal, place
		 * 0's until the size of the linked lists have the same size
		 */
		second = second.clone();

		int diff = first.coefficients.size() - second.coefficients.size();
		while (diff < 0) { // second is bigger

			first.coefficients.addLast(0);
			diff++;
		}
		while (diff > 0) { // this is bigger
			second.coefficients.addLast(0);
			diff--;
		}

		/*
		 * The following is the code for the minus method
		 */

		// declare the linked lists

		LinkedList<Integer> firstCoe;
		LinkedList<Integer> secondCoe;
		LinkedList<Integer> diffCoe;

		// Store the coefficients of each number in linked list form

		firstCoe = first.getCoefficients();

		secondCoe = second.getCoefficients();

		diffCoe = difference.getCoefficients();

		// initialize borrow

		int borrow = 0;

		/*
		 * for loop to update the index Condition indicates that the loop executes when
		 * the index is smaller than the size of the linked list This will stop the for
		 * loop at the last digit in both numbers
		 */

		for (int index = 0; index < first.coefficients.size(); index++) {

			int digit1; 												// declare an int variable digit1 to store the digit of the first number
			digit1 = firstCoe.get(index); 			    			    // store the digit of the first number into digit1
			int digit2; 												// declare an int variable digit2 to store the digit of the second number
			digit2 = secondCoe.get(index);		            		    // store the digit of the second number into digit2
			int digitdiff; 											// declare an int variable to store the difference of the two digits
			digit1 = digit1 - borrow; 								// update the digit of the first number depending if the previous
																	// subtraction needed to borrow 1

			/*
			 * If the first digit is greater or equal to the second digit we do not need to
			 * borrow, and the difference can simply be stored and added to the linked list
			 */
			if (digit1 >= digit2) { 									// if the digit of the first number is greater or equal to the second
																	// one
				digitdiff = digit1 - digit2;						    // simply do the subtraction
				diffCoe.addLast(digitdiff); 							// add the difference in the linked list
				borrow = 0; 											// since there is no need for borrow, borrow=0
			}
			/*
			 * If the digit of the first number is smaller than the second number we need to
			 * borrow 1 from the next digit
			 */
			else { 													// if the digit of the first number is smaller than the second one
				digitdiff = digit1 + base - digit2; 					// borrow from the next digit, so we add the base value
																	// to this digit and then do the subtraction
				diffCoe.addLast(digitdiff); 							// add the difference of the two digits in the linked list
				borrow = 1; 											// since the first digit is smaller than the second one, we need to borrow 1
																	// from the next digit
			}
		}

		String s = new String(); 									// declare a String
		for (Integer coef : diffCoe) 								// create for loop to read each elements in the linked list
			s = coef.toString() + s; 								// store each elements in String form
		difference = new NaturalNumber(s, this.base); 				// input the String and the base into NaturalNumbers class, and
																// obtain the required output, which is also a String

		// --------- END SOLUTION (minus) ----------

		/*
		 * In the case of say 100-98, we will end up with 002. Remove all the leading
		 * 0's of the result.
		 *
		 * We are giving you this code because you could easily neglect to do this
		 * check, which would mess up grading since correct answers would appear
		 * incorrect.
		 */

		while ((difference.coefficients.size() > 1) & (difference.coefficients.getLast().intValue() == 0)) {
			difference.coefficients.removeLast();
		}
		return difference;
	}

	/*
	 * Slow division algorithm, mentioned in lecture 1.
	 */

	public NaturalNumber slowDivide(NaturalNumber divisor) throws Exception {

		NaturalNumber quotient = new NaturalNumber(0, base);
		NaturalNumber one = new NaturalNumber(1, base);
		NaturalNumber remainder = this.clone();
		while (remainder.compareTo(divisor) >= 0) {
			remainder = remainder.minus(divisor);
			quotient = quotient.plus(one);
		}
		return quotient;
	}

	/*
	 * The divide method divides 'this' by 'divisor' i.e. this.divide(divisor) When
	 * this method terminates, there is a remainder but it is ignored (not
	 * returned).
	 * 
	 */

public NaturalNumber divide( NaturalNumber  divisor ) throws Exception{
		
		//  initialize quotient as an empty list of coefficients
		
		NaturalNumber  quotient = new NaturalNumber(this.base);
		
		if (this.base != divisor.base){
			System.out.println("ERROR: bases must be the same in an division");
			throw new Exception();
		}
		
		if(divisor.compareTo(new NaturalNumber(0, this.base))==0){
			System.out.println("ERROR: division by zero not possible");
			throw new Exception();
		}
		
		NaturalNumber  remainder = this.clone();

		//   ADD YOUR CODE HERE.
		
		//  --------------- BEGIN SOLUTION (divide) --------------------------

		/*
		 * Declare variables
		 */
		NaturalNumber temp;													//temp variable will store the partition of the remainder
		temp = new NaturalNumber(this.base);								    //create empty temp
		NaturalNumber divisor_copy = divisor.clone();						//work with a copy of divisor since we do not want to change the value of the divisor
		NaturalNumber ref;													//create a NaturalNumber that contains only 1 element of 0, for the purpose of reference
		ref = new NaturalNumber(0,this.base);

		
		int carry =0;														//initialize the carry to be 0													
		int index =0;														//initialize the index to be 0;
		LinkedList<Integer> remainderCoe;									//to store the coefficients of remainder
		remainderCoe = remainder.getCoefficients();
		int remainderSize;													
		remainderSize = remainder.coefficients.size();						//get the size of the remainder
		
		/*
		 * The logic below can be explained by a simply example:
		 * 					   ______________
		 * Let's consider  5346|4125166125812
		 * 
		 * first, take the first digit of the remainder which is 4, and place it into temp
		 * compare temp with the divisor 5346
		 * if temp< 5346,
		 * we put the next digit of the remainder into temp, which makes temp become 41.
		 * again compare temp with the divisor,
		 * repeat this until the temp is greater than the divisor,
		 * so temp will be 41251.
		 * Now we use the minus method, to see how many times temp can be subtracted by divisor 5346, can keep truck of the number, store it in carry.
		 * 41251-5346=35905, set it as the new temp, it is still greater than the divisor so repeat it until the temp is smaller than the divisor,
		 * which give us carry = 7, temp = 3829,
		 * Now temp is smaller than the divisor, we repeat the previous logic and add the next number of the remainder to our temp, and it will give us temp = 38296.
		 * Repeat the above logic until the remainder have no digit left.
		 * 
		 * 
		 * Of course, there is smaller things such as the temp=0 and the next digit in remainder is also 0,
		 * we will see how to fix these special cases in the code.
		 * However, the above is the main idea of this method.
		 */
		
		for(int i = 1; i<=remainder.coefficients.size(); i++) {                    //start the loop with i = 1	   
			index = remainderSize - i;                                             //the index is the index of the digits of the remainder that we want to add to our temp
			temp.coefficients.addFirst(remainder.coefficients.get(index));         //add the corresponding digit to our temp
			
			if(temp.compareTo(ref)==0) {											 //this is to make sure that temp do not have leading 0's
				temp.coefficients.removeFirst();                                   //remove the leading 0 in temp
			}
			
			carry=0;                      										 //initialize carry to be 0, and it is in the for loop, since we need to reset carry for every new temp
			if(temp.compareTo(divisor_copy)>=0) {								 //if temp is greater than the divisor, we operate the while loop
				
				while(temp.compareTo(divisor_copy)>=0)	{						 //while temp is greater than divisor, keep subtracting the temp until it is less than the divisor
				temp = temp.minus(divisor_copy);									 //update temp after the subtraction
				
				if (temp.coefficients.getLast()==0) {							 //if temp become 0, we remove the 0 and proceed the for loop
					temp.coefficients.removeLast(); 								 //remove temp that has value of 0
				}
				
				carry = carry+1;													 //the carry counts the number of times that the substraction occured, and it will be one digit of our answer
				}

				quotient.coefficients.addFirst(carry);							 //add the carry to our answer
			}																	 //																							   ______
			else if (i>1) {														 //starting form the second digit, if the temp is smaller than the divisor, we need to borrow the next number, and put a 0 in our answer
				quotient.coefficients.addFirst(0);;								 //put 0 when temp is smaller than the divisor starting from the second digit
			}
		}
		
		while (quotient.coefficients.getLast()==0) {								 //this is to eliminate the leading 0's
			quotient.coefficients.removeLast();									 //if the first digit is 0, remove the first digit, repeat until the first digit is not 0
		}
		
		
		
		
		
		

		// ------------- END SOLUTION (divide) ---------------------

		return quotient;
}


	// Helper methods

	/*
	 * The methods you write to add, subtract, multiply, divide should not alter the
	 * two numbers. If a method require that one of the numbers be altered (e.g.
	 * borrowing in subtraction) then you need to clone the number and work with the
	 * cloned number instead of the original.
	 */

	@Override
	public NaturalNumber clone() {

		// For technical reasons that don't interest us now (and perhaps ever), this
		// method
		// has to be declared public (not private).

		NaturalNumber copy = new NaturalNumber(this.base);
		for (int i = 0; i < this.coefficients.size(); i++) {
			copy.coefficients.addLast(new Integer(this.coefficients.get(i)));
		}
		return copy;
	}

	/*
	 * The subtraction method (minus) computes a-b and requires that a>b. The
	 * a.compareTo(b) method is useful for checking this condition. It returns -1 if
	 * a < b, it returns 0 if a == b, and it returns 1 if a > b.
	 * 
	 * The compareTo() method assumes that the two numbers have the same base. One
	 * could add code to check this but I didn't.
	 */

	public int compareTo(NaturalNumber second) {

		// if this < other, return -1
		// if this > other, return 1
		// otherwise they are equal and return 0

		// Assume maximum degree coefficient is non-zero. Then, if two numbers
		// have different maximum degree, it is easy to decide which is larger.

		int diff = this.coefficients.size() - second.coefficients.size();
		if (diff < 0)
			return -1;
		else if (diff > 0)
			return 1;
		else {

			// If two numbers have the same maximum degree, then it is a bit trickier
			// to decide which number is larger. You need to compare the coefficients,
			// starting from the largest and working toward the smallest until you find
			// coefficients that are not equal.

			boolean done = false;
			int i = this.coefficients.size() - 1;
			while (i >= 0 && !done) {
				diff = this.coefficients.get(i) - second.coefficients.get(i);
				if (diff < 0) {
					return -1;
				} else if (diff > 0)
					return 1;
				else {
					i--;
				}
			}
			return 0; // if all coefficients are the same, so numbers are equal.
		}
	}

	/*
	 * computes 'this' * base^n
	 */

	private NaturalNumber timesBaseToThePower(int n) {
		for (int i = 0; i < n; i++) {
			this.coefficients.addFirst(new Integer(0));
		}
		return this;
	}

	/*
	 * The following method is invoked by System.out.print. It returns the string
	 * with coefficients in the reverse order which is the natural format for people
	 * to reading numbers, i.e. people want to read a[N-1], ... a[2] a[1] a[0]. It
	 * does so simply by make a copy of the list with elements in reversed order,
	 * and then printing the list using the LinkedList's toString() method.
	 */

	@Override
	public String toString() {
		String s = new String();
		for (Integer coef : coefficients) // Java enhanced for loop
			s = coef.toString() + s; // Append each successive coefficient.
		return "(" + s + ")_" + base;
	}

}
