package lab4;
import java.util.*;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  M. Kolling 
 * @version 0.1 (incomplete)
 */
public class CalcEngine
{
	char operator;

	String displayValue = "";

	static HashMap<Character,Integer> priority = setPriorityOfOperands();

	/**
	 * Create a CalcEngine instance. Initialise its state so that it is ready 
	 * for use.
	 */
	public CalcEngine()
	{
		//Stack st = new Stack();
		//operator =' ';
	}

	public String convertingInfixToPostfix()
	{
		String result = "";
		Stack<Character> st = new Stack<Character>();
		// scan the infix for left to right
		for(int i = 0; i < displayValue.length() ; i++)
		{
			char c = displayValue.charAt(i);
			// if the scanned character is an operand, add it to the postfix string called displayValue
			if (Character.isDigit(c))
			{
				result += c;
			}
			else
			{
				if (st.isEmpty())
				{
					pushWithShow(st,c);
				}
				else
				{
					while (!st.isEmpty() && comparePriorityOfOperands(peekWithShow(st),c))
					{
						char popedChar = popWithShow(st);
						result += popedChar;
					}
					// push to stack
					pushWithShow(st, c);
				}
			}
		}
		while (!st.isEmpty())
		{
			char popedChar = popWithShow(st);
			result += popedChar;
		}
		return result;
	}
	
	public void evaluatingAPostfixExpression()
	{
		String thePostfixExpression = convertingInfixToPostfix();
		Stack<Character> st = new Stack<Character>();

		// this stack (which is the same stack) should be empty as the 
		// stack is empty at the end of the convertingInfixToPostfix method.
		
		// scan the infix for left to right
		for(int i = 0 ; i < thePostfixExpression.length() ; i++)
		{
			char c = displayValue.charAt(i);
			if (Character.isDigit(c))
			{
				pushWithShow(st,c);
			}
			else
			{
				// c right now is an operator represente as a char
				int tempOne = Character.getNumericValue(popWithShow(st));
				int tempTwo = Character.getNumericValue(popWithShow(st));
				
				int result = 0;
				
				//good place for a switch
				
				switch (c)
				{
				case '+': result = tempOne + tempOne;
				}
				}
			}
		}
	//}

	public boolean comparePriorityOfOperands(char a, char b)
	{
		if (priority.get(a) > priority.get(b))
		{
			return true;
		}
		else
		{
			return false;
		}	
	}

	public static HashMap<Character,Integer> setPriorityOfOperands()
	{
		//BIMDAS - ORDER: 1) brackets 2) multiplication 3) division 4) addition (same as subtraction)
		// 5) subtraction (same as addition).

		HashMap<Character,Integer> priority = new HashMap<Character,Integer>();

		priority.put('+',1);

		priority.put('-',1);

		priority.put('(',4);
		priority.put(')',4);

		priority.put('÷',2);

		priority.put('×',2);

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
	}

	public void divide()
	{
		displayValue += "÷";
	}

	public void openBracket()
	{
		displayValue += '(';
	}

	public void closeBracket()
	{ 
		//for some reason it must be ' and never "
		displayValue += ')';
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
		System.out.println(convertingInfixToPostfix());
	}

	/**
	 * The 'C' (clear) button was pressed.
	 */
	public void clear()
	{
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
		return(" ");
	}

	public String getDisplayValue()
	{
		return displayValue;
	}

	/**
	 * Return the version number of this engine. This string is displayed as 
	 * it is, so it should say something like "Version 1.1".
	 */
	public String getVersion()
	{
		return("Ver. 1.0");
	}

	static void pushWithShow(Stack<Character> st, char a) {
		st.push(a);
		System.out.println("push(" + a + ")");
		System.out.println("stack: " + st);
	}

	static char popWithShow(Stack<Character> st) {
		System.out.print("pop -> ");
		char a = (char) st.pop();
		System.out.println(a);
		System.out.println("stack: " + st);
		return a;
	}

	static char peekWithShow(Stack<Character> st) {
		char p = st.peek();
		return p;
	}
}