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

	boolean hasTheDecimalBeenPressed;
	boolean hasANumPressedAfterDot;
	double operand1;
	Stack st;

	/**
	 * Create a CalcEngine instance. Initialise its state so that it is ready 
	 * for use.
	 */
	public CalcEngine()
	{
		Stack st = new Stack();
		hasTheDecimalBeenPressed = false;
		hasANumPressedAfterDot = false;
		operator =' ';
		operand1 = 0;
	}

	/**
	 * Return the value that should currently be displayed on the calculator
	 * display.
	 */
	public boolean getHasANumAfterDot()
	{
		return hasANumPressedAfterDot;
	}

	/**
	 * A number button was pressed. Do whatever you have to do to handle it.
	 * The number value of the button is given as a parameter.
	 */
	public void numberPressed(int number)
	{

	}

	public void dotPressed()
	{
		hasTheDecimalBeenPressed = true;
	}

	public boolean getDotPressed()
	{
		return hasTheDecimalBeenPressed;
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
		displayValue += "X";
	}

	public void divide()
	{
		displayValue += "÷";
	}

	public void openBracket()
	{
		operator = '(';
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
		for(int i = 0, n = displayValue.length() ; i < n ; i++) { 
		    char c = displayValue.charAt(i); 
			pushWithShow(st, String.valueOf(c));
		}
	}

	/**
	 * The 'C' (clear) button was pressed.
	 */
	public void clear()
	{
		operand1 = 0;
		hasTheDecimalBeenPressed = false;
		hasANumPressedAfterDot = false;
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
	
	/**
	 * This method simply reads the contents of the stack.
	 */
	public void readTheStacksContents()
	{
		for(int i = 0, n = displayValue.length() ; i < n ; i++) { 
		    popWithShow(st);
		}
	}

	/**
	 * Return the version number of this engine. This string is displayed as 
	 * it is, so it should say something like "Version 1.1".
	 */
	public String getVersion()
	{
		return("Ver. 1.0");
	}

	static void pushWithShow(Stack st, String a) {
		st.push(new String(a));
		System.out.println("push(" + a + ")");
		System.out.println("stack: " + st);
	}

	static char popWithShow(Stack st) {
		System.out.print("pop -> ");
		char a = (char) st.pop();
		System.out.println(a);
		System.out.println("stack: " + st);
		return a;
	}
}