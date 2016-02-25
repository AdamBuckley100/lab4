package lab4;
import java.util.*;

/**
 * The main part of the calculator that actually does the calculations.
 * This class is the behind the scenes of how the calculator actually does
 * what the userInterface class specifies.
 * 
 * @author Adam Buckley
 * @Date - Start Date: 09/02/2016
 * @Date - End Date: 25/02/2016
 */
public class CalcEngine
{
	// declare a variable of type char named operator.
	char operator;

	String displayValue = "";
	// the default status of a boolean is false.
	boolean hasEqualsBeenPressed = false;

	// A hashmap mapping the priority of the operators (PEMDAS).
	static HashMap<String,Integer> priority = setPriorityOfOperands();

	/**
	 * Constructor.
	 */
	public CalcEngine()
	{
	}

	// This method deletes the very last character that the user has inputted. (clicked)
	public void delLastChar()
	{
		displayValue = displayValue.substring(0, displayValue.length()-1);
	}

	public String convertingInfixToPostfix()
	{
		String result = "";
		
		// st is a variable of type MyStack which is initialized with a stack (array stack) size of 100:
		// 100 spaces are in my stack on initialization.
		MyStack st = new MyStack(100);
		
		// scan the infix from left to right
		for(int i = 0; i < displayValue.length() ; i++)
		{
			char c = displayValue.charAt(i);
			// if the scanned character is an operand, add it to the postfix string called displayValue

			if (Character.isDigit(c) || c == '.')
			{
				result += c;

				if (i != displayValue.length() - 1)
				{
					char d = displayValue.charAt(i+1);
					if (!(Character.isDigit(d) || d == '.'))
					{
						result += " ";
					}
				}
				else
				{
					result += " ";
				}
				// if the else directly aboved is executed: keep going down ... straight past the else directly below.
			}
			else
			{
				if (st.isEmpty())
				{
					String o = String.valueOf(c);
					pushWithShow(st,o);
				}
				else if (c == '(')
				{
					String o = String.valueOf(c);
					pushWithShow(st,o);
				}
				else if (c == ')')
				{
					// if a ) is hit a special action must be taken: all operands on the stack MUST
					// be taken off the stack until a peek reveals a ( on the stack. when a peek reveals
					// a (, you must destroy the ( from the stack too.
					//result += ' ';
					do
					{
						result += popWithShow(st);
						result += " ";
						System.out.println("*" + peekWithShow(st) + "*");
						System.out.println("*" + peekWithShow(st).getClass().getName() + "*");
					}
					while (!peekWithShow(st).equals("("));
					// The line above: IT'S VERY IMPORTANT THAT WITH STRING COMPARING YPU USE THE .equals and
					// NEVER USE != or == etc.! STRING COMPARE USES .EQUALS ONLY!!!!

					// now discard the ) on the stack:
					popWithShow(st);
				}
				else
				{
					while (!st.isEmpty() && comparePriorityOfOperands(peekWithShow(st),String.valueOf(c)))
					{
						String popedChar = popWithShow(st);
						result += popedChar;
						result += " ";
					}
					// push to stack
					pushWithShow(st, String.valueOf(c));
				}
			}
		}
		//gets to this while if theres only operands on the stack left to be taken off and 
		//puts those on the output string and nothing else left to do.
		while (!st.isEmpty())
		{
			String popedChar = popWithShow(st);
			result += popedChar;
			result += " ";
		}
		return result;
	}

	/**
	 *
	 */
	public void evaluatingAPostfixExpression()
	{
		String thePostfixExpression = convertingInfixToPostfix();
		
		// st is a variable of type MyStack which is initialized with a stack (array stack) size of 100:
		// 100 spaces are in my stack on initialization.
		MyStack st = new MyStack(100);
		
		// Below line: An array of Strings is populated with the sum in post fix expression mode
		// but with each single character of the String having it's own spot in the array.
		// a space is used a delimiter.
		String[] arrayOfStrings = thePostfixExpression.split(" ");

		// Going through each String at every String array location in the arrayOfStings String array.
		for (int p = 0 ; p < arrayOfStrings.length ; p++)
		{
			// charAt(0) works because there is only one character per array location.
			char ch = arrayOfStrings[p].charAt(0);
			
			// If the arrayofString's location single single is a digit
			if (Character.isDigit(ch))
			{ 
				// push it onto myStack
				pushWithShow(st, arrayOfStrings[p]);
			}

			// If the character is not a digit
			if (!Character.isDigit(ch))
			{
				String tempTwo = popWithShow(st);
				String tempOne = popWithShow(st);

				double result = 0;

				double temp1 = Double.valueOf(tempOne);
				double temp2 = Double.valueOf(tempTwo);

				// does the calculation based on what ch is (switch statement).
				switch (ch)
				{
				case '+': result = temp1 + temp2;
				break;
				case '-': result = temp1 - temp2;
				break;
				case '×': result = temp1 * temp2;
				break;
				case '/': result = (temp1/temp2);
				break;
				case '÷': result = (temp1/temp2);
				break;
				case '^': result = Math.pow(temp1, temp2);
				}
				String resultInString = Double.toString(result);
				pushWithShow(st, resultInString);
			}
		}
		String finalResultInString = popWithShow(st);
		
		// To avoid answers like 38.0 on the calc output I put the following line to chop ".0" off the end
		// It is there because I'm working with a double.
		if (finalResultInString.endsWith(".0"))
		{
			finalResultInString = finalResultInString.substring(0, finalResultInString.length() - 2);
		}
		displayValue = finalResultInString;
	}

	/**
	 * 
	 * @param theString
	 * @param String - two different String variables.
	 * @return boolean - return true if the first string has a higher priority 
	 * than the second String.
	 */
	public boolean comparePriorityOfOperands(String theString, String c)
	{
		if (priority.get(theString) >= priority.get(c))
		{
			return true;
		}
		else
		{
			return false;
		}	
	}

	/**
	 * This method sets the priority of my operators.
	 * 
	 * @return HashMap<String,Integer> - a hashmap mapping a String to an integer is returned,
	 * Each String operator will be mapped with it's priority within a sum (highest = go first).
	 */
	public static HashMap<String,Integer> setPriorityOfOperands()
	{
		//BIMDAS - ORDER: 1) brackets 2) multiplication 3) division 4) addition (same as subtraction)
		// 5) subtraction (same as addition).

		HashMap<String,Integer> priority = new HashMap<String,Integer>();

		priority.put("+",1);

		priority.put("-",1);

		priority.put("(",0);

		priority.put("÷",2);

		priority.put("×",2);

		priority.put("^", 3);

		// BIMDAS so indices ^ is 3, multiplication is 2 and so is division and finally addition
		// and subtraction are both 1 and ( is lowest.
		// ( has the very lowest priority because I want ( to be placed on the stack
		// every single time a ( is found (no exceptions).
		return priority;
	}

	/**
	 * A number button was pressed. Do whatever you have to do to handle it.
	 * The number value of the button is given as a parameter.
	 */
	public void numberPressed(int number)
	{
		displayValue = displayValue + number;
	}

	/**
	 * The 'plus' button was pressed. 
	 */
	public void plus()
	{
		displayValue += "+";
	}

	/**
	 * The 'minus' button was pressed.
	 */
	public void minus()
	{
		displayValue += "-";
	}

	public void multiply()
	{
		displayValue += "×";
	}

	public void dotPressed()
	{
		displayValue += ".";
		// do something to now print a dot to the string displayValue Running total String.
	}

	public void divide()
	{
		displayValue += "÷";
	}

	public void openBracket()
	{
		displayValue += "(";
	}

	public void closeBracket()
	{ 
		displayValue += ")";
	}

	public void dot()
	{
		displayValue += ".";
	}

	public void caret()
	{
		displayValue += "^";
	}

	/**
	 * The '=' button was pressed.
	 */
	public void equals()
	{
		setHasEqualsBeenPressed(true);
		evaluatingAPostfixExpression();
	}

	/**
	 * The 'C' (clear) button was pressed. This button clears the entire calculator view contents.
	 */
	public void clear()
	{
		setHasEqualsBeenPressed(false);
		
		// simply resets the displayValue String to an empty string so user can start
		// all over inputting a completely new sum.
		displayValue = "";
	}

	/**
	 * Return the title of this calculation engine.
	 */
	public String getTitle()
	{
		return("My Calculator");
	}

	/**
	 * Return the author of this engine. This string is displayed as it is,
	 * so it should say something like "Written by H. Simpson".
	 */
	public String getAuthor()
	{
		return("Adam Buckley");
	}

	/**
	 * a getter for displayValue global variable.
	 * @return String - the displayValue global variable.
	 */
	public String getDisplayValue()
	{
		return displayValue;
	}

	/**
	 *  A method returning a true of false based on whether the user has pressed
	 *  = or if they have not presse equals yet on the calculator.
	 * @return boolean - true or false: has = been pressed yet.
	 */
	public boolean getHasEqualsBeenPressed()
	{
		return hasEqualsBeenPressed;
	}

	/**
	 * Setter to set the hasEqualsBeenPresed global variable to either true or false. 
	 */
	public void setHasEqualsBeenPressed(boolean trueOrFalse)
	{
		hasEqualsBeenPressed = trueOrFalse;
	}

	/**
	 * Return the version number of this engine. This string is displayed as 
	 * it is, so it should say something like "Version 1.1".
	 */
	public String getVersion()
	{
		return("Ver. 1.0");
	}

	/**
	 * This method pushes String variable onto the stack and shows a
	 * System.out.println output to console to show what's happening.
	 * 
	 * @param Stack and String - The stack itself and the String variable
	 * to be added to the stack.
	 */
	static void pushWithShow(MyStack st, String c) {
		st.push(c);
		System.out.println("push(" + c + ")");
		System.out.println("stack: " + st);
	}

	/**
	 * This method pops String variable off of the stack and shows a
	 * System.out.println output to console to show what's happening.
	 * 
	 * @param Stack and String - The stack itself.
	 * @return String - return the poped off String variable.
	 */
	static String popWithShow(MyStack st) {
		System.out.print("pop -> ");
		String a = st.pop();
		System.out.println(a);
		System.out.println("stack: " + st);
		return a;
	}

	/**
	 * This method looks at the String variable at the very top of the stack.
	 * 
	 * @param Stack  - The stack itself.
	 * @return String - return the String variable on the top of the stack.
	 */
	static String peekWithShow(MyStack st) {
		String p = st.peek();
		return p;
	}
}