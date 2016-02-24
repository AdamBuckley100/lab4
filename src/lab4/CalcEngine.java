package lab4;
import java.util.*;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  Adam Buckley (20062910)
 * @version 1.000
 * @Date - Start Date: 09/02/2016
 * @Date - End Date: 16/02/2016
 */
public class CalcEngine
{
	char operator;

	String displayValue = "";
	// the default status of a boolean is false.
	boolean hasEqualsBeenPressed = false;

	// A hashmap mapping the priority of the operators (BIMDAS).
	static HashMap<String,Integer> priority = setPriorityOfOperands();

	/**
	 * Create a CalcEngine instance. Initialise its state so that it is ready 
	 * for use.
	 */
	public CalcEngine()
	{
		//Stack st = new Stack();
		//operator =' ';
	}

	// This method deletes the very last character that the user has inputted. (clicked)
	public void delLastChar()
	{
		//char a = String.valueOf(displayValue);
		//String a = displayValue.charAt(displayValue.length());
		//char[] charArray = displayValue.toCharArray();
		//charArray.splice(, 1);

		//displayValue.length()-2 is the entire current displayValue string excluding the
		//very last string variable.
		displayValue = displayValue.substring(0, displayValue.length()-1);
	}

	public String convertingInfixToPostfix()
	{
		String result = "";
		MyStack st = new MyStack(100);
		// scan the infix for left to right
		for(int i = 0; i < displayValue.length() ; i++)
		{
			char c = displayValue.charAt(i);
			System.out.print("kkkkkkk" + displayValue.charAt(i) + "kk");
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
				// else keep going down ... straight past the else directly below.
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
						System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBB");
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
					System.out.println("HERE??");
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
			System.out.println("display value: " + displayValue);
			System.out.println("result: " + result);
		}
		//gets to this while is theres only operands on the stack left to be taken off and 
		//put on the output string and nothing else left to do.
		while (!st.isEmpty())
		{
			//result += ' ';
			String popedChar = popWithShow(st);
			result += popedChar;
			result += " ";
		}
		System.out.println(result + "end of infix to postfix");
		return result;
	}

	public void evaluatingAPostfixExpression()
	{
		String thePostfixExpression = convertingInfixToPostfix();
		MyStack st = new MyStack(100);
		// this stack (which is the same stack) should be empty as the 
		// stack is empty at the end of the convertingInfixToPostfix method.
		System.out.println("hi.....    " + thePostfixExpression);
		//for(int i = 0 ; i < thePostfixExpression.length() ; i++)
		//{
		String[] arrayOfStrings = thePostfixExpression.split(" ");

		for (int p = 0 ; p < arrayOfStrings.length ; p++)
		{
			System.out.println(arrayOfStrings[p]);
			char ch = arrayOfStrings[p].charAt(0);


			if (Character.isDigit(ch))
			{ 
				pushWithShow(st, arrayOfStrings[p]);
			}

			if (!Character.isDigit(ch))
			{
				String tempTwo = popWithShow(st);
				String tempOne = popWithShow(st);

				//int tempTwo = Character.getNumericValue(popWithShow(st));
				//int tempOne = Character.getNumericValue(popWithShow(st));	

				double result = 0;

				//good place for a switch

				double temp1 = Double.valueOf(tempOne);
				double temp2 = Double.valueOf(tempTwo);

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
		if (finalResultInString.endsWith(".0"))
		{
			finalResultInString = finalResultInString.substring(0, finalResultInString.length() - 2);
		}
		displayValue = finalResultInString;
	}

	public boolean comparePriorityOfOperands(String theString, String c)
	{
		System.out.println("jjjjjj" + priority.get(theString));
		if (priority.get(theString) >= priority.get(c))
		{
			return true;
		}
		else
		{
			return false;
		}	
	}

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
		// and subtraction are both 1. ( always will have lowest.
		System.out.println(priority.get("+"));
		System.out.println(priority.get("-"));
		System.out.println(priority.get("("));
		System.out.println(priority.get("÷"));
		System.out.println(priority.get("×"));
		System.out.println(priority.get("^"));
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
		//for some reason it must be ' and never "
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
		//	for(int i = 0, n = displayValue.length() ; i < n ; i++) { 
		//	char c = displayValue.charAt(i); 
		//pushWithShow(st, String.valueOf(c));
		//System.out.println(convertingInfixToPostfix());
		setHasEqualsBeenPressed(true);
		evaluatingAPostfixExpression();
	}

	/**
	 * The 'C' (clear) button was pressed.
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

	public String getDisplayValue()
	{
		return displayValue;
	}

	public boolean getHasEqualsBeenPressed()
	{
		return hasEqualsBeenPressed;
	}

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

	static void pushWithShow(MyStack st, String c) {
		st.push(c);
		System.out.println("push(" + c + ")");
		System.out.println("stack: " + st);
	}

	static String popWithShow(MyStack st) {
		System.out.print("pop -> ");
		String a = st.pop();
		System.out.println(a);
		System.out.println("stack: " + st);
		return a;
	}

	static String peekWithShow(MyStack st) {
		String p = st.peek();
		return p;
	}
}