package lab4;

import java.util.Arrays;

/** 
 * This class is my array stack replacing the java API stack implementation.
 * This stack is implemented using an array instead of using the stack class as implemented in the java API.
 * 
 * Module: Data Structures.
 * References: Moodle Data Structures notes & youtube video "stacks & queues".
 * 
 * @author Adam Buckley (20062910)
 * @version 1.0
 * @Date - Start Date: 09/02/2016
 * @Date - End Date: 25/02/2016
 */

public class MyStack {

	// myStackArray which is an array of String variables.
	private String[] myStackArray;

	private int stackSize;

	private int theTopOfStack = 0;

	/**
	 * Constructs the stack.
	 * @param sizeOfStack - the size which I want (which was 100).
	 */
	public MyStack(int sizeOfStack)
	{
		stackSize = sizeOfStack;

		myStackArray = new String[sizeOfStack];

		// Make sure all the strings in my strings stack are just all "0")
		Arrays.fill(myStackArray, "0");
	}

	/**
	 * push adds a String variable to the stack (to the top of the stack).
	 */
	public void push (String input)
	{
		if(theTopOfStack + 1 < stackSize)
		{
			// the following increment happens after current the theTopOfStack variable has been used. 
			// (so addition is done basically after that line has done ALL it's working so to speak.
			myStackArray[theTopOfStack++] = input;
		}
		else
		{
			System.out.println("Stack is full, cannot add this on");
		}
	}

	/**
	 * pop removes what is on the top of the stack
	 */
	public String pop()
	{
		if (theTopOfStack >= 0)
		{
			System.out.println(myStackArray[theTopOfStack-1] + " was removed from the stack");

			// Specific decrementatio : The following decrement happens before the theTopOfTheSTack variable is used to get the position
			// in the myStackArray of return.
			return myStackArray[--theTopOfStack];
		}
		else
		{
			System.out.println("the stack is empty");
			return null;
		}
	}

	/**
	 * peek checks what is on the top of the stack.
	 * 
	 * @return - returns a String variable, A String representation of the operator or operand on
	 * the top of the stack.
	 */
	public String peek()
	{
		if (theTopOfStack > 0)
		{
			System.out.println("peek: " + myStackArray[theTopOfStack-1] + " is at the top of the stack");
			return myStackArray[theTopOfStack-1];
		}
		else
		{
			return null;
		}
	}

	/**
	 * Update the interface display to show the current value of the 
	 * calculator.
	 * 
	 * @return - returns a boolean variable, true if stack is empty, false if it's not empty.
	 */
	public boolean isEmpty()
	{
		if (theTopOfStack == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}