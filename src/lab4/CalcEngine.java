package lab4;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  M. Kolling 
 * @version 0.1 (incomplete)
 */
public class CalcEngine
{
	char operator;
	
	int displayValueWhole, displayValueFraction;
	
	boolean hasTheDecimalBeenPressed;
	boolean hasANumPressedAfterDot;
	double operand1;

	/**
	 * Create a CalcEngine instance. Initialise its state so that it is ready 
	 * for use.
	 */
	public CalcEngine()
	{
		hasTheDecimalBeenPressed = false;
		hasANumPressedAfterDot = false;
		operator =' ';
		displayValueWhole=0;
		operand1 = 0;
	}

	/**
	 * Return the value that should currently be displayed on the calculator
	 * display.
	 */
	public int getWholeDisplayValue()
	{
		return(displayValueWhole);
	}

	/**
	 * Return the value that should currently be displayed on the calculator
	 * display.
	 */
	public int getFractionDisplayValue()
	{
		return(displayValueFraction);
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
		if (hasTheDecimalBeenPressed == false)
		{
			displayValueWhole = displayValueWhole *10 + number;
		}
		else
		{
			displayValueFraction = displayValueFraction *10 + number;
			System.out.println("other");
			hasANumPressedAfterDot = true;
		}
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
		operand1 = convertNumToDouble();
		displayValueWhole = 0;
		displayValueFraction = 0;
		hasTheDecimalBeenPressed = false;
		hasANumPressedAfterDot = false;
		operator = '+';
	}

	/**
	 * The 'minus' button was pressed.
	 */
	public void minus()
	{
		operand1 = displayValueWhole;
		displayValueWhole = 0;
		operator = '-'; 
	}

	public void multiply()
	{
		operand1 = displayValueWhole;
		displayValueWhole = 0;
		operator = '*'; 
	}

	public void divide()
	{
		operand1 = displayValueWhole;
		displayValueWhole = 0;
		operator = '/'; 
	}

	public void openBracket()
	{
		operand1 = displayValueWhole;
		displayValueWhole = 0;
		operator = '/'; 
	}

	public void closeBracket()
	{
		operand1 = displayValueWhole;
		displayValueWhole = 0;
		operator = '/'; 
	}

	public void dot()
	{
		operand1 = displayValueWhole;
		displayValueWhole = 0;
		operator = '.'; 
	}

	public void caret()
	{
		operand1 = displayValueWhole;
		displayValueWhole = 0;
		operator = '/'; 
	}

	/**
	 * The '=' button was pressed.
	 */
	public void equals()
	{
		if (operator == '+') {
			double fullNumber = convertNumToDouble();
			double fullNumAdded = fullNumber + operand1;
			convertFromDouble(fullNumAdded);
			operand1 = 0;
		}
		else if (operator == '-') {
			//displayValueWhole = operand1-displayValueWhole;
			operand1 = 0;
		}
		else if (operator == '*') {
			//displayValueWhole = operand1*displayValueWhole;
			operand1 = 0;
		}
		else if (operator == '/') {
			//displayValueWhole = operand1/displayValueWhole;
			operand1 = 0;
		}
	}

	public double convertNumToDouble()
	{
		String num = displayValueWhole + "." + displayValueFraction;

		double number = Double.parseDouble(num);

		return number;
	}

	public void convertFromDouble(double num)
	{
		int wholePart = (int) num;
		double fractionPart = num-wholePart;

		displayValueWhole = wholePart;

		if (fractionPart > 0)
		{
			String s = String.valueOf(fractionPart);
			//limit included (2+9)
			String t = s.substring(2,2+9);
			displayValueFraction = Integer.parseInt(t);
			// t needs trailing zeros removed.


			hasTheDecimalBeenPressed = true;
			hasANumPressedAfterDot = true;
		}
		else
		{
			displayValueFraction = 0;
			hasTheDecimalBeenPressed = false;
			hasANumPressedAfterDot = false;
		}
		//String num = displayValueWhole + "." + displayValueFraction;
	}

	/**
	 * The 'C' (clear) button was pressed.
	 */
	public void clear()
	{
		displayValueWhole = 0;
		displayValueFraction = 0;
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
		return("Joe Daly");
	}

	/**
	 * Return the version number of this engine. This string is displayed as 
	 * it is, so it should say something like "Version 1.1".
	 */
	public String getVersion()
	{
		return("Ver. 1.0");
	}
}