/*
 * Author: Ben Esposito
 * Created: 3/25/16
 * Last Modified: 4/18/16
 * Version: 1.1
 * 
 * To do:
 * Add trig
 * Fix nested parentheses
 * 
 * Mac: //Users//benesposito//Desktop//Coding//Workspace//Mess Around//src//Files//
 * PC: C:\\Users\\junke\\Desktop\\Coding\\Workspace\\MainProj\\src\\Files\\
 * School: P:\\Honors Programming\\Calculator\\Files\\
 * Linux: /mnt/HDD/Coding/Java/Main Workspace/Calculator/src/Files/
 * Generic: System.getenv("USERPROFILE") + "\\OneDrive\\Documents\\Files\\";
 * fd: F:\\Calculator\\Main\\src\\Files\\
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;
import javax.swing.*;


public class Calculator
{
	BorderLayout B = new BorderLayout();
	BorderLayout BL = new BorderLayout(10, 0);
	GridLayout G = new GridLayout(5, 1);
	GridLayout sciG = new GridLayout(4, 3);
	
	JFrame frame;
	JFrame workFrame;
	
	JPanel mainPanel;
	JPanel numbersPanel;
	JPanel inputPanel;
	JPanel outputPanel;
	JPanel opsPanel;
	JPanel sciPanel;
	JPanel workPanel;
	
	static JLabel output;
	
	static JTextArea workOutput;
	
	JScrollPane sp;
	
	JButton work;
	JButton mode;
	JButton one;
	JButton two;
	JButton three;
	JButton four;
	JButton five;
	JButton six;
	JButton seven;
	JButton eight;
	JButton nine;
	JButton zero;
	JButton decimal;
	JButton clear;
	
	JButton plus;
	JButton minus;
	JButton times;
	JButton division;
	JButton equals;
	
	JButton a;
	JButton b;
	JButton c;
	JButton set;
	JButton exp;
	JButton factorial;
	JButton openP;
	JButton closeP;
	JButton avg;
	JButton distance;
	JButton quadratic;
	JButton linConvert;
	JButton midpt;
	JButton prime;
	JButton sqrt;
	JButton dummy4;
	JButton ans;
	
	static JTextField inputField;
	
	JFrame avgFrame;
	JPanel avgPanel;
	JTextField avgTF;
	JButton avgButton;
	
	static JFrame distanceFrame;
	JPanel distancePanel;
	static JTextField distanceTF;
	JButton distanceButton;
	static JLabel distanceLabel;
	
	static JFrame linConvertFrame;
	JPanel linConvertPanel;
	static JTextField linConvertTF;
	JButton linConvertButton;
	static JLabel linConvertLabel;
	
	static String input = "";
	static String workString = "";
	static String workStringOld = "";
	static int distanceI = 0; 
	static int midpointI = 0;
	
	static double x1;
	static double y1;
	static double x2;
	static double y2;
	static Ben ben = new Ben();
	static Scanner in = new Scanner(System.in);
	static ArrayList<String> expressionStringArray = new ArrayList<String>();
	static ArrayList<Double> expression = new ArrayList<Double>();
	static String rawExpressionString = "";
	static String rES;
	static String ow[] = new String[11];
	static char mdas;
	static int numOfSpaces = 0;
	static int numOfElements;
	static int owInt;
	static double owDouble[] = new double[10];
	static double answer;
	static boolean check;
	static boolean varCheck1;
	static boolean varCheck2;
	static final String OS = "pc";
	static String LOCATION;
	static final String EXT = ".txt";
	
	public static void main(String[] args) throws IOException
	{
		setOS();
		
		new Calculator();
	}
	
	public Calculator()
	{
		ListenForSubmit lForSubmit = new ListenForSubmit();
		
		frame = new JFrame("Calculator v1.1");
		workFrame = new JFrame();
		
		mainPanel = new JPanel(B);
		numbersPanel = new JPanel(new GridLayout());
		inputPanel = new JPanel();
		outputPanel = new JPanel();
		opsPanel = new JPanel(G);
		sciPanel = new JPanel(sciG);
		workPanel = new JPanel();
		
		output = new JLabel("Output");
		workOutput = new JTextArea(15, 50);
		sp = new JScrollPane(workOutput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		work = new JButton("Work");
		mode = new JButton("Mode");
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		zero = new JButton("0");
		decimal = new JButton(".");
		clear = new JButton("C");
		
		plus = new JButton("+");
		minus = new JButton("-");
		times = new JButton("x");
		division = new JButton("/");
		equals = new JButton("=");
		
		a = new JButton("a");
		b = new JButton("b");
		c = new JButton("c");
		set = new JButton("set");
		exp = new JButton("^");
		factorial = new JButton("!");
		openP = new JButton("(");
		closeP = new JButton(")");
		avg = new JButton("avg");
		distance = new JButton("distance");
		linConvert = new JButton("lin convt");
		midpt = new JButton("midpt");
		quadratic = new JButton("quadratic");
		sqrt = new JButton("sqrt");
		dummy4 = new JButton();
		ans = new JButton("ans");
		
		if(OS.equals("mac"))
			inputField = new JTextField(15);
		else
			inputField = new JTextField(20);
		
		avgFrame = new JFrame("Averaging");
		avgButton = new JButton("Submit");
		avgPanel = new JPanel();
		avgTF = new JTextField(25);
		
		distanceFrame = new JFrame("Distance Formula");
		distancePanel = new JPanel();
		distanceTF = new JTextField(15);
		distanceButton = new JButton("Next");
		distanceLabel = new JLabel("x1:");
		
		linConvertFrame = new JFrame("Linear Function Convertion");
		linConvertPanel = new JPanel();
		linConvertTF = new JTextField(15);
		linConvertButton = new JButton("Convert");
		linConvertLabel = new JLabel("Function:");
		
		frame.setVisible(true);
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(mainPanel);
		
		workFrame.setVisible(false);
		workFrame.setSize(600, 300);
		workFrame.setResizable(true);
		workFrame.add(workPanel);
		
		work.setContentAreaFilled(false);
		mode.setContentAreaFilled(false);
		one.setContentAreaFilled(false);
		two.setContentAreaFilled(false);
		three.setContentAreaFilled(false);
		four.setContentAreaFilled(false);
		five.setContentAreaFilled(false);
		six.setContentAreaFilled(false);
		seven.setContentAreaFilled(false);
		eight.setContentAreaFilled(false);
		nine.setContentAreaFilled(false);
		zero.setContentAreaFilled(false);
		decimal.setContentAreaFilled(false);
		clear.setContentAreaFilled(false);
		
		plus.setContentAreaFilled(false);
		minus.setContentAreaFilled(false);
		times.setContentAreaFilled(false);
		division.setContentAreaFilled(false);
		equals.setContentAreaFilled(false);
		
		a.setContentAreaFilled(false);
		b.setContentAreaFilled(false);
		c.setContentAreaFilled(false);
		set.setContentAreaFilled(false);
		exp.setContentAreaFilled(false);
		factorial.setContentAreaFilled(false);
		openP.setContentAreaFilled(false);
		closeP.setContentAreaFilled(false);
		avg.setContentAreaFilled(false);
		distance.setContentAreaFilled(false);
		linConvert.setContentAreaFilled(false);
		midpt.setContentAreaFilled(false);
		quadratic.setContentAreaFilled(false);
		sqrt.setContentAreaFilled(false);
		dummy4.setContentAreaFilled(false);
		ans.setContentAreaFilled(false);
		
		avgButton.setContentAreaFilled(false);
		distanceButton.setContentAreaFilled(false);
		linConvertButton.setContentAreaFilled(false);
		
		work.addActionListener(lForSubmit);
		mode.addActionListener(lForSubmit);
		one.addActionListener(lForSubmit);
		two.addActionListener(lForSubmit);
		three.addActionListener(lForSubmit);
		four.addActionListener(lForSubmit);
		five.addActionListener(lForSubmit);
		six.addActionListener(lForSubmit);
		seven.addActionListener(lForSubmit);
		eight.addActionListener(lForSubmit);
		nine.addActionListener(lForSubmit);
		zero.addActionListener(lForSubmit);
		decimal.addActionListener(lForSubmit);
		
		clear.addActionListener(lForSubmit);
		plus.addActionListener(lForSubmit);
		minus.addActionListener(lForSubmit);
		times.addActionListener(lForSubmit);
		division.addActionListener(lForSubmit);
		equals.addActionListener(lForSubmit);
		
		a.addActionListener(lForSubmit);
		b.addActionListener(lForSubmit);
		c.addActionListener(lForSubmit);
		set.addActionListener(lForSubmit);
		exp.addActionListener(lForSubmit);
		factorial.addActionListener(lForSubmit);
		openP.addActionListener(lForSubmit);
		closeP.addActionListener(lForSubmit);
		avg.addActionListener(lForSubmit);
		distance.addActionListener(lForSubmit);
		linConvert.addActionListener(lForSubmit);
		midpt.addActionListener(lForSubmit);
		quadratic.addActionListener(lForSubmit);
		sqrt.addActionListener(lForSubmit);
		dummy4.addActionListener(lForSubmit);
		ans.addActionListener(lForSubmit);
		
		avgButton.addActionListener(lForSubmit);
		distanceButton.addActionListener(lForSubmit);
		linConvertButton.addActionListener(lForSubmit);
		
		workPanel.add(sp);
		
		numbersPanel.setLayout(new GridLayout(4, 3));
		numbersPanel.add(one);
		numbersPanel.add(two);
		numbersPanel.add(three);
		numbersPanel.add(four);
		numbersPanel.add(five);
		numbersPanel.add(six);
		numbersPanel.add(seven);
		numbersPanel.add(eight);
		numbersPanel.add(nine);
		numbersPanel.add(zero);
		numbersPanel.add(decimal);
		numbersPanel.add(clear);
		
		inputPanel.add(mode);
		inputPanel.add(inputField);
		inputPanel.add(work);
		outputPanel.add(output);
		
		opsPanel.add(plus);
		opsPanel.add(minus);
		opsPanel.add(times);
		opsPanel.add(division);
		opsPanel.add(equals);
		
		sciPanel.add(a);
		sciPanel.add(b);
		sciPanel.add(c);
		sciPanel.add(set);
		sciPanel.add(exp);
		sciPanel.add(factorial);
		sciPanel.add(openP);
		sciPanel.add(closeP);
		sciPanel.add(avg);
		sciPanel.add(distance);
		sciPanel.add(linConvert);
		sciPanel.add(midpt);
		sciPanel.add(quadratic);
		sciPanel.add(sqrt);
		sciPanel.add(dummy4);
		sciPanel.add(ans);
		sciPanel.setVisible(false);
		
		mainPanel.add(sciPanel, B.WEST);
		mainPanel.add(inputPanel, B.NORTH);
		mainPanel.add(numbersPanel, B.CENTER);
		mainPanel.add(opsPanel, B.EAST);
		mainPanel.add(outputPanel, B.SOUTH);
		
		avgFrame.setVisible(false);
		avgFrame.setSize(300, 100);
		avgFrame.setResizable(false);
		avgFrame.add(avgPanel);
		avgPanel.add(avgTF);
		avgPanel.add(avgButton);
		
		distanceFrame.setVisible(false);
		distanceFrame.setSize(300, 75);
		distanceFrame.setResizable(false);
		distanceFrame.add(distancePanel);
		distancePanel.add(distanceLabel);
		distancePanel.add(distanceTF);
		distancePanel.add(distanceButton);
		
		linConvertFrame.setVisible(false);
		linConvertFrame.setSize(300, 75);
		linConvertFrame.setResizable(false);
		linConvertFrame.add(linConvertPanel);
		linConvertPanel.add(linConvertTF);
		linConvertPanel.add(linConvertButton);
	}
	
	public static void setOS()
	{
		if(OS.equals("pc"))
		{
			LOCATION = "E:\\Coding\\Java\\Main Workspace\\Calculator\\src\\Files\\";
		}
		else if(OS.equals("mac"))
		{
			LOCATION = "/Users/benesposito/Desktop/Coding/Workspace/MainProj/src/Files/";
		}
		else if(OS.equals("school"))
		{
			LOCATION = "P:\\Honors Programming\\Calculator\\Main\\src\\Files";
		}
		else if(OS.equals("generic"))
		{
			LOCATION = System.getenv("USERPROFILE") + "\\OneDrive\\Documents\\Files\\";
		}
		else if(OS.equals("fd"))
		{
			LOCATION = "F:\\Calculator\\Main\\src\\Files\\";
		}
		else if(OS.equals("linux"))
			LOCATION = "/mnt/HDD/Coding/Java/Main Workspace/Calculator/src/Files/";
	}
	
	public static boolean askExpression(boolean check) throws IOException
	{
		
		if(rawExpressionString.equals("average") || rawExpressionString.equals("a"))
		{
			averaging();
			check = true;
		}
		else if(rawExpressionString.equals("byte conversion") || rawExpressionString.equals("byte"))
		{
			byteConversion();
			check = true;
		}
		else if(rawExpressionString.equals("distance") || rawExpressionString.equals("distance formula"))
		{
			distance();
			check = true;
		}
		else if(rawExpressionString.equals("quadratic"))
		{
			//quadratic();
			check = true;
		}
		else if(rawExpressionString.equals("prime") || rawExpressionString.equals("p"))
		{
			prime();
			check = true;
		}
		else if(rawExpressionString.equals("lc") || rawExpressionString.equals("linear"))
		{
			linearConversion(in.nextLine());
			check = true;
		}
		else if(rawExpressionString.length() > 3 && rawExpressionString.substring(0, 3).equals("set"))
		{
			set(rawExpressionString.substring(4));
			check = true;
		}
		else
		{
			myReplace();
			ow[0] = rawExpressionString;
			check = false;
		}
		
		return check;
	}
	
	public static void contProg() throws IOException
	{
		start();
		
		parentheses();
		
		betweenPAndE();
		
		exponents();
		
		betweenEAndMD();
		
		MD();
		
		betweenMDAndAS();
		
		AS();
		
		end();
	}
	
	public static void start()
	{
		System.out.println("\nWork:\n" + rawExpressionString);										//Formats "Work: "
		workString = workString + "Work:\n" + rawExpressionString + "\n";
			
		for(int i = 0; i < ow[0].length(); i++) 													//Counts number of elements in rawExpressionString
		{
			if (ow[0].charAt(i) == ' ')
				numOfSpaces++;
		}
    
		numOfElements = numOfSpaces + 1;															//Defines a lot of variables
		expressionStringArray.add(ow[0]);
		rES = ow[0];	
	
		for(int i = 0; i <= numOfSpaces; i++)														//Sets all data values in expressionStringArray (array) to each element
		{
			if(ow[0].contains(" "))
			{
				expressionStringArray.add(ow[0].substring(0, ow[0].indexOf(" ")));
				ow[0] = ow[0].substring(ow[0].indexOf(" ") + 1);
			}
		
			else
				expressionStringArray.add(ow[0]);
		}
		
		ow[9] = rES;
	}
	
	public static void parentheses()
	{
		boolean checkP;
		boolean checkTrig = false;
		boolean isFactorial;
		int parentheses;
		int pFor;
		int insideP = 0;
		int outsideP = 0;
		int firstP;
		int lastP;
		
		while(rES.contains("("))																	//"P" in PEMDAS
		{
			checkP = false;																			//Setting/Resetting variables
			parentheses = 0;
			pFor = 0;
			isFactorial = false;
			ow[5] = rES;
			
			
			while(ow[5].contains("("))
			{
				checkP = false;																		//Setting/Resetting variables
				parentheses = 0;
				ow[9] = rES;
				
				for(pFor = 0; pFor < ow[5].length(); pFor++)										//for loop for finding which parentheses goes with which parentheses
				{
					if(ow[5].charAt(pFor) == '(')//Checks if there are parentheses and how many there are
					{
						if(pFor > 0)
						{
							if(ow[5].charAt(pFor - 1) != 'n' || ow[5].charAt(pFor - 1) != 's')
							{
								parentheses++;
								checkP = true;
							}
						}
						else
						{
							parentheses++;
							checkP = true;
						}
						
					}
				
					else if(ow[5].charAt(pFor) == ')')												//Subtracts the number of closed parenthesis until 0
						parentheses--;
						
					if(parentheses == 0 && checkP == true)													
					{
						outsideP = pFor;
						insideP = ow[5].substring(0, pFor).lastIndexOf("(");
						
						if(ow[5].substring(pFor + 1).length() > 0)
						{
							if(ow[5].charAt(pFor + 1) == '!')
								isFactorial = true;
						}
						
						ow[5] = ow[5].substring(ow[5].indexOf("(") + 1, pFor);
					}
				}
			}
			
			pFor--;
			ow[6] = rES.substring(0, insideP);
			ow[7] = ow[5];
			
			if((rES.substring(pFor + 1)).length() > 0)
			{
				ow[8] = rES.substring(outsideP + 1);
				
				if(ow[8].charAt(0) == ' ')
					ow[8] = ow[8].substring(1);
			}
			else
				ow[8] = "";
			
			rES = ow[7];
			
			exponents();
			betweenEAndMD();
			MD();
			betweenMDAndAS();
			AS();
			
			if(isFactorial)
				rES = ow[6] + answer + ow[8];
			else
				rES = ow[6] + answer + " " + ow[8];
			
			System.out.println(rES);
			workString = workString + rES + "\n";
		}
	}	
	
	public static void betweenPAndE()
	{
		ow[9] = rES;
	}
	
	public static void exponents()
	{
		while(rES.contains("^") || rES.contains("sqrt")) 											//"E" in PEMDAS AND A HUGE MESS SO DON'T CHANGE ANYTHING D: ;( THIS WAS HARDER THAN IT SHOULD HAVE BEEN
		{
			for(int i = rES.length() - 1; i >= 0; i--)
			{
				if(rES.charAt(i) == '^')
				{
					ow[0] = rES.substring(0, rES.lastIndexOf("^") - 1); 									//Substrings from rES from beginning to base
					ow[0] = ow[0].substring(ow[0].lastIndexOf(" ") + 1);									//Substrings to base
					owDouble[0] = Double.parseDouble(ow[0]);												//Turns the base into a double
					
					if(rES.lastIndexOf(" ") == rES.lastIndexOf("^") + 1)									//Checks if the number after the last ^ is the last number
					{
						ow[1] = rES.substring(rES.lastIndexOf("^"));										//Substrings from the space before the last ^ to the end
						ow[1] = ow[1].substring(ow[1].lastIndexOf(" ") + 1); 								//Substrings to the last number of rES
					}
					
					else
					{
						ow[1] = rES.substring(rES.lastIndexOf("^") + 2);									//Substrings from rES from the power to the end
						ow[1] = ow[1].substring(0, ow[1].indexOf(" "));										//Substrings from rES to get the power
					}
					
					owDouble[1] = Double.parseDouble(ow[1]);												//Turns the power into a double
				
					owDouble[0] = Math.pow(owDouble[0], owDouble[1]);										//Simplifies base^power to just a double
					owDouble[1] = 0;																		//Resets all values of owDouble except for [0]
				
					if(rES.lastIndexOf(" ") == rES.lastIndexOf("^") + 1)									//Checks if power is the last number in rES
					{	
						if(rES.indexOf(" ") == rES.lastIndexOf("^") - 1)									//Checks if base is the first number in rES
							rES = "" + owDouble[0];															//Sets rES to the answer
						
						else
						{
							ow[0] = rES.substring(0, rES.lastIndexOf("^") - 1); 							//Substrings from rES from beginning to (and including) base
							ow[0] = ow[0].substring(0, ow[0].lastIndexOf(" "));								//Substrings to just the base
							rES = ow[0] + " " + owDouble[0];
						}
					}
				
					else
					{
						ow[0] = rES.substring(0, rES.lastIndexOf("^") - 1);
						ow[0] = ow[0].substring(0, ow[0].lastIndexOf(" ") + 1);
						ow[1] = rES.substring(rES.lastIndexOf("^") + 2);
						ow[1] = ow[1].substring(ow[1].indexOf(" "));
						rES = ow[0] + owDouble[0] + ow[1]; 													//Sets rES to the simplified expression
					}
					if(rES != ow[9])
					{
						System.out.println(rES);
						workString = workString + rES + "\n";
					}
				}
				else
				{
					try
					{
						if(rES.substring(i, i + 4).equals("sqrt"))
						{
							ow[0] = rES.substring(0, i);
							ow[1] = rES.substring(i + 4, rES.indexOf(" ", i));
							ow[2] = rES.substring(rES.indexOf(" ", i));
							
							owDouble[1] = Double.parseDouble(ow[1]);
							owDouble[1] = Math.sqrt(owDouble[1]);
							
							rES = ow[0] + owDouble[1] + ow[2];
							
							System.out.println(rES);
							workString = workString + rES + "\n";
						}
					}
					catch(IndexOutOfBoundsException e)
					{
						//HAHA IT WORKED GET REKT M8
					}
					
				}
			}
		}
	}
	
	public static void betweenEAndMD()
	{
		ow[0] = rES;
		expressionStringArray.clear();
		
		if(rES.charAt(rES.length() - 1) == ' ')
			rES = rES.substring(0, rES.length() - 1);
				
		for(int i = 0; i <= numOfSpaces; i++)														//Sets all data values in expressionStringArray (array) to each element
		{
			if(ow[0].contains(" "))
			{
				expressionStringArray.add(ow[0].substring(0, ow[0].indexOf(" ")));
				ow[0] = ow[0].substring(ow[0].indexOf(" ") + 1);
			}
		
			else
				expressionStringArray.add(ow[0]);
		}
	
		ow[9] = rES;
	}
	
	public static void MD()
	{
		while(rES.contains("x") || rES.contains("/") || rES.contains("!"))							//"M" & "D" in PEMDAS
		{
			for(int i = 0; i < rES.length(); i++)													//Loops i
			{
				if(rES.charAt(i) == '!')
				{
					ow[0] = rES.substring(0, rES.substring(0, i).lastIndexOf(" ") + 1);
					ow[1] = rES.substring(rES.substring(0, i).lastIndexOf(" ") + 1, i);
					
					if(i + 1 >= rES.length())
						ow[2] = "";
					else
						ow[2] = rES.substring(i + 1);
          	     
					owInt = Integer.parseInt(ow[1]);
					
					for(int e = owInt - 1; e > 0; e--)
						owInt *= e;
					
					rES = ow[0] + owInt + ow[2];
				}
				
				if(rES != ow[9])
				{
					System.out.println(rES);
					workString = workString + rES + "\n";
				}
				ow[9] = rES;
			}
			
			for(int i = 2; i < rES.length(); i++)													//Loops i
			{
				if(rES.charAt(i) == 'x' || rES.charAt(i) == '/')									//Checks for "M" or "D"
				{
					mdas = rES.charAt(i);															//Sets mdas to the operation (M, D, A, or S)
					ow[0] = rES.substring(0, i - 1);												//Substrings to the beginning of rES to the first factor/dividend
					
					if(ow[0].contains(" "))															//Checks if first factor/dividend is the first number
						ow[0] = ow[0].substring(ow[0].lastIndexOf(" "));							//Substrings to the first factor/dividend
			
					owDouble[0] = Double.parseDouble(ow[0]);										//Sets owDouble[0] to first factor/dividend
			
					ow[1] = rES.substring(i + 2);													//Substrings from second factor/divisor to end
			
					if(ow[1].contains(" "))															//Checks if second factor/divisor is the last number
						ow[1] = ow[1].substring(0, ow[1].indexOf(" "));								//Substrings to second factor/divisor
					
					owDouble[1] = Double.parseDouble(ow[1]);										//Sets owDouble[1] to second factor/divisor
			
					ow[0] = rES.substring(0, rES.indexOf(mdas) - 1);								//Sets ow[0] to rES from beginning to first factor
			
					if(ow[0].contains(" "))															//Checks if first factor/dividend is the first number
						ow[0] = ow[0].substring(0, ow[0].lastIndexOf(" ") + 1);						//Sets ow[0] to rES from beginning to the space before the first factor
					else	
						ow[0] = "";																	//Sets ow[0] to nothing
			
					ow[1] = rES.substring(rES.indexOf(mdas) + 2);									//Sets ow[1] to rES from the second factor to the end
			
					if(ow[1].contains(" "))															//Checks if second factor/divisor is the last number
						ow[1] = ow[1].substring(ow[1].indexOf(" "));								//Sets ow[1] to rES from first space after second factor to the end
					else	
						ow[1] = "";																	//Sets ow[1] to nothing
			
					if(rES.charAt(i) == 'x' || rES.charAt(i) == '*')								//Checks for multiplication
						owDouble[0] = owDouble[0] * owDouble[1];									//Multiplies the factors
					
					else if(rES.charAt(i) == '/')													//Checks for division
						owDouble[0] = owDouble[0] / owDouble[1];									//Divides the factors
			
					rES = ow[0] + owDouble[0] + ow[1];												//Sets rES to refined version
					i = 2;																			//Resets for loop
				}
				
				if(rES != ow[9])
				{
					System.out.println(rES);
					workString = workString + rES + "\n";
				}
				ow[9] = rES;
			}
		}
	}
	
	public static void betweenMDAndAS()
	{
		ow[0] = rES;																				//Sets ow[0] to rES
		expressionStringArray.clear();
		
		for(int i = 0; i <= numOfSpaces; i++)														//Sets all data values in expressionStringArray (array) to each element
		{
			if(ow[0].contains(" "))
			{
				expressionStringArray.add(ow[0].substring(0, ow[0].indexOf(" ")));
				ow[0] = ow[0].substring(ow[0].indexOf(" ") + 1);
			}
		
			else
				expressionStringArray.add(ow[0]);
		}
		numOfElements = expressionStringArray.size();
		for(int i = 0; i < numOfElements; i += 2)													//Sets all numbers in the string to doubles
			expression.add(Double.parseDouble(expressionStringArray.get(i)));
	
		answer = expression.get(0);
		owDouble[0] = answer;
		
	}
	
	public static void AS()
	{
		answer = Double.parseDouble(expressionStringArray.get(0));
		for(int i = 1; i < numOfElements; i++)														//"A" & "S" in PEMDAS
		{
			if(expressionStringArray.get(i).equals("+"))
				answer += Double.parseDouble(expressionStringArray.get(i + 1));
			else if(expressionStringArray.get(i).equals("-"))
				answer -= Double.parseDouble(expressionStringArray.get(i + 1));
		}
	}
	
	public static void end() throws IOException
	{
		File file = new File(LOCATION + "ans" + EXT);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		if(answer % 1 == 0)
		{
			owInt = (int) answer;
			System.out.println("\n" + rawExpressionString + " = " + owInt + "\n\nAnswer: " + owInt + "\n");
			workString = workString + "\n" + rawExpressionString + " = " + owInt + "\n\nAnswer: " + owInt + "\n\n";
		}
		else
		{
			System.out.println("\n" + rawExpressionString + " = " + answer + "\n\nAnswer: " + answer + "\n");
			workString = workString + "\n" + rawExpressionString + " = " + answer + "\n\nAnswer: " + answer + "\n\n";
		}
		
		System.out.println("---------------------------------------\n");
		workString = workString + "---------------------------------------\n\n";
		
		bw.write("" + answer);
		bw.close();
		
		workOutput.setText(workString);
	}

////////////////////////////////////////////////////////////////////////////////

	public static void averaging()
	{
		answer = 0;
		ArrayList<Double> numbers = new ArrayList<Double>();
		
		while(ow[0].contains(" "))
		{
			numbers.add(Double.parseDouble(ow[0].substring(0, ow[0].indexOf(' '))));
			ow[0] = ow[0].substring(ow[0].indexOf(' ') + 1);
		}
		
		numbers.add(Double.parseDouble(ow[0]));
		
		for(int i = 0; i < numbers.size(); i++)
			answer += numbers.get(i);
		
		answer /= numbers.size();
	}

	public static void byteConversion()
	{
		int power1 = 0;
		int power2 = 0;
		System.out.println("What is the current bytesize type?");
		String firstType = in.nextLine();
		
		if(firstType.equals("b")||firstType.equals("byte"))
		{
			firstType = "byte";
			power1 = 0;
		}
		else if(firstType.equals("kb")||firstType.equals("kilobyte"))
		{
			firstType = "kilobyte";
			power1 = 1;
		}
		else if(firstType.equals("mb")||firstType.equals("megabyte"))
		{
			firstType = "megabyte";
			power1 = 2;
		}
		else if(firstType.equals("gb")||firstType.equals("gigabyte"))
		{
			firstType = "gigabyte";
			power1 = 3;
		}
		else if(firstType.equals("tb")||firstType.equals("terabyte"))
		{
			firstType = "terabyte";
			power1 = 4;
		}
		else if(firstType.equals("pb")||firstType.equals("petabyte"))
		{
			firstType = "petabyte";
			power1 = 5;
		}
		else
		{
			System.out.println("That is not a valid bytesize type.");
			return;
		}
			
		System.out.println("How many " + firstType + "s?");
		double number = in.nextDouble();
		System.out.println("What is the bytesize type to which you want to convert?");
		in.nextLine();
		String secondType = in.nextLine();
		
		if(secondType.equals("b")||firstType.equals("byte"))
		{
			secondType = "byte";
			power2 = 0;
		}
		else if(secondType.equals("kb")||secondType.equals("kilobyte"))
		{
			secondType = "kilobyte";
			power2 = 1;
		}
		else if(secondType.equals("mb")||secondType.equals("megabyte"))
		{
			secondType = "megabyte";
			power2 = 2;
		}
		else if(secondType.equals("gb")||secondType.equals("gigabyte"))
		{
			secondType = "gigabyte";
			power2 = 3;
		}
		else if(secondType.equals("tb")||secondType.equals("terabyte"))
		{
			secondType = "terabyte";
			power2 = 4;
		}
		else if(secondType.equals("pb")||secondType.equals("petabyte"))
		{
			secondType = "petabyte";
			power2 = 5;
		}
		else
			return;
		
		double answer = number * Math.pow(1024, power1 - power2);
		System.out.println(number + " " + firstType + "s is " + answer + " " + secondType + "s."); 
	}

	public static void distance()
	{
		switch(distanceI)
		{
		case 0:
			distanceLabel.setText("x1:");
			break;
		case 1:
			distanceLabel.setText("y1:");
			x1 = Double.parseDouble(distanceTF.getText());
			distanceTF.setText("");
			break;
		case 2:
			distanceLabel.setText("x2:");
			y1 = Double.parseDouble(distanceTF.getText());
			distanceTF.setText("");
			break;
		case 3:
			distanceLabel.setText("y2:");
			x2 = Double.parseDouble(distanceTF.getText());
			distanceTF.setText("");
			break;
		case 4:
			y2 = Double.parseDouble(distanceTF.getText());
			distanceTF.setText("");
			distanceFrame.setVisible(false);
		}
		
		answer = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}
	
	public static void lengthConversion()
	{
		System.out.print("Current measurement type: ");
		String currentMeasurementType = in.nextLine();
		double number;
		
		if(currentMeasurementType.equals("mm")||currentMeasurementType.equals("millimeter"))
		{
			System.out.print("Number of millimeters: ");
			number = in.nextDouble();
		}
	}

	public static void literalEquations() //WIP
	{
		String equation;
		String newE;
		String var;
		
		System.out.print("Equation: ");
		equation = in.nextLine();
		System.out.print("Variable to solve for: ");
		var = in.nextLine();
	}
	
	public static void linearConversion(String original) //WIP
	{
		String newFormat;
		String currentFormat;
		String finished = "";
		String ow;
		double y1;
		double x1;
		double m;
		double b;
		
		if(original.substring(0, original.indexOf("=")).contains("-"))
			newFormat = "slope y-intercept";
		else
			newFormat = "point-slope";
		
		if(newFormat.equals("slope y-intercept"))
		{
			
		}
		else if(newFormat.equals("point-slope"));
		{
			ow = original.substring(original.indexOf("(") + 1);
			ow = ow.substring(ow.indexOf(" ") + 1);
			ow = ow.substring(ow.indexOf(" ") + 1, ow.indexOf(")"));
			
			y1 = Double.parseDouble(original.substring(4, original.indexOf("=") - 1));
			x1 = Double.parseDouble(ow);
			m = Double.parseDouble(original.substring(original.indexOf("=") + 2, original.indexOf("(")));
			b = (-1 * m * x1) - y1;
			
			if(m == (int) m)
				m = (int) m;
			if(b == (int) b)
				b = (int) b;
			
			if(b < 0)
				finished = "y = " + m + "x" + " - " + (b * -1);
			else
				finished = "y = " + m + "x" + " " + b;
		}
		
		System.out.println(finished);
		output.setText(finished);
		
		linConvertFrame.setVisible(false);
	}

	public static void midpoint()
	{
		switch(midpointI)
		{
		case 1:
			input = "x1: ";
			break;
			
		case 2:
			x1 = Double.parseDouble(inputField.getText().substring(4));
			input = "y1: ";
			break;
			
		case 3:
			y1 = Double.parseDouble(inputField.getText().substring(4));
			input = "x2: ";
			break;
		
		case 4:
			x2 = Double.parseDouble(inputField.getText().substring(4));
			input = "y2: ";
			break;
		
		case 5:
			y2 = Double.parseDouble(inputField.getText().substring(4));
			output.setText("(" + ((x1 + x2)/2) + ", " + ((y1 + y2)/2) + ")");
			input = "";
			midpointI = 6;
			break;
		}
	}

	public static void prime()
	{
		int userNumber;
		boolean primeCheck = true;
		
		System.out.print("Number: ");
		userNumber = in.nextInt();
		
		for(int i = 2; i <= Math.sqrt(userNumber); i++)
		{
			if(userNumber % i == 0)
				primeCheck = false;
		}
		if(primeCheck)
			System.out.println(userNumber + " is a prime number.");
		else
			System.out.println(userNumber + " is not a prime number.");
		
		rawExpressionString = in.nextLine();
	}

	public static void myReplace() throws IOException
	{
		rawExpressionString = rawExpressionString.replace('*', 'x');
		
		String var = "";
		
		for(int i = 0; i < 3; i++)
		{
			if(i == 0)
				var = "ans";
			else if(i == 1)
				var = "a";
			else if(i == 2)
				var = "b";
			else
				var = "c";
			
			File file = new File(LOCATION + var + EXT);
			FileReader fw = new FileReader(file);
			BufferedReader bw = new BufferedReader(fw);
			
			if(rawExpressionString.contains(var))
				rawExpressionString = rawExpressionString.replace(var, bw.readLine());
		}
	}
	
	public static void set(String equation) throws IOException
	{
		String input = equation;
		String var;
		var = input.substring(0, 1);
		
		File file = new File(LOCATION + var.substring(0, 1) + EXT);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		String num = input.substring(4);
		
		bw.write(num);
		bw.close();
	}
	
	public static void tempConverter()
	{
		String tempType;
		String tempString;
		double temp;
		
		System.out.print("Temperature: ");
		tempString = in.nextLine();
		
		if(!tempString.contains(" "))
		{
			System.err.println("[Error]: Incorrect format!");
			System.err.println("Correct format: [Temp] [F/C]");
		}
		
		temp = Double.parseDouble(tempString.substring(0, tempString.indexOf(" ")));
		tempType = tempString.substring(tempString.indexOf(" ") + 1);
		
		if(tempType.equals("F"))
		{
			tempType = "Celcius";
			temp = temp - 32 * 5.0/9.0;
		}
		else if(tempType.equals("C"))
		{
			tempType = "Fahrenheit";
			temp = temp * 9.0/5.0 + 32;
		}
		
		System.out.println("Temperature in " + tempType + " is " + temp);
	}
	
////////////////////////////////////////////////////////////////////////////////
	
	public class ListenForSubmit implements ActionListener
	{
		boolean modeToggle = false;
		boolean setCheck = false;
		boolean stringCheck = false;
		boolean midpointToggle = false;
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == one)
				input = inputField.getText() + "1";
			else if(e.getSource() == two)
				input = inputField.getText() + "2";
			else if(e.getSource() == three)
				input = inputField.getText() + "3";
			else if(e.getSource() == four)
				input = inputField.getText() + "4";
			else if(e.getSource() == five)
				input = inputField.getText() + "5";
			else if(e.getSource() == six)
				input = inputField.getText() + "6";
			else if(e.getSource() == seven)
				input = inputField.getText() + "7";
			else if(e.getSource() == eight)
				input = inputField.getText() + "8";
			else if(e.getSource() == nine)
				input = inputField.getText() + "9";
			else if(e.getSource() == zero)
				input = inputField.getText() + "0";
			else if(e.getSource() == decimal)
				input = inputField.getText() + ".";
			else if(e.getSource() == clear)
				input = "";
			else if(e.getSource() == plus)
			{
				if(inputField.getText().length() == 0)
					input = "ans + ";
				else
					input = inputField.getText() + " + ";
			}
			else if(e.getSource() == minus && !midpointToggle)
			{
				if(inputField.getText().length() == 0)
					input = "ans - ";
				else
					input = inputField.getText() + " - ";
			}
			else if(e.getSource() == minus && midpointToggle)
				input = input + " -";
			else if(e.getSource() == times)
			{
				if(inputField.getText().length() == 0)
					input = "ans x ";
				else
					input = inputField.getText() + " x ";
			}
			else if(e.getSource() == division)
			{
				if(inputField.getText().length() == 0)
					input = "ans / ";
				else
					input = inputField.getText() + " / ";
			}
			else if(e.getSource() == equals && !setCheck && !midpointToggle)
			{
				stringCheck = false;
				input = "";
				
				if(inputField.getText().length() != 0)
					rawExpressionString = inputField.getText().trim(); // right now am trying to repeat the expression if inputField empty and "=" is pressed, 5/13
				
				sp.repaint();
				
				try
				{
					if(askExpression(check) == false)
						contProg();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
			}
			else if(e.getSource() == equals && setCheck)
			{
				input = inputField.getText() + " = ";
				setCheck = false;
			}
			else if(e.getSource() == equals && midpointToggle)
			{
				midpointI++;
				midpoint();
			}
			else if(e.getSource() == exp)
				input = inputField.getText() + " ^ ";
			else if(e.getSource() == factorial)
				input = inputField.getText() + "!";
			else if(e.getSource() == openP)
				input = inputField.getText() + "(";
			else if(e.getSource() == closeP)
				input = inputField.getText() + ")";
			else if(e.getSource() == a)
				input = inputField.getText() + "a";
			else if(e.getSource() == b)
				input = inputField.getText() + "b";
			else if(e.getSource() == c)
				input = inputField.getText() + "c";
			else if(e.getSource() == set)
			{
				input = inputField.getText() + "set ";
				setCheck = true;
			}
			else if(e.getSource() == mode && !modeToggle)
			{
				sciPanel.setVisible(true);
				frame.repaint();
				frame.setSize(750, 500);
				modeToggle = true;
			}
				
			else if(e.getSource() == mode && modeToggle)
			{
				sciPanel.setVisible(false);
				frame.repaint();
				frame.setSize(400, 500);
				modeToggle = false;
			}
			else if(e.getSource() == work)
				workFrame.setVisible(true);
			else if(e.getSource() == avg)
				avgFrame.setVisible(true);
			else if(e.getSource() == avgButton)
			{
				ow[0] = avgTF.getText();
				avgTF.setText("");
				averaging();
				avgFrame.setVisible(false);
			}
			else if(e.getSource() == distance)
			{
				distanceFrame.setVisible(true);
				distanceI = 0;
				distance();
			}
			else if(e.getSource() == distanceButton)
			{
				distanceI++;
				distance();
			}
			else if(e.getSource() == midpt)
			{
				midpointToggle = true;
				midpointI = 1;
				midpoint();
			}
			else if(e.getSource() == ans)
			{
				input = inputField.getText() + "ans";
			}
			else if(e.getSource() == sqrt)
			{
				input = inputField.getText() + "sqrt(";
			}
			else if(e.getSource() == linConvert)
			{
				stringCheck = true;
				linConvertFrame.setVisible(true);
			}
			else if(e.getSource() == linConvertButton)
			{
				linearConversion(linConvertTF.getText());
				linConvertTF.setText("");
			}
			
			if(!stringCheck && midpointI != 6)
				output.setText("" + answer);
			
			if(midpointI == 6)
			{
				midpointToggle = false;
				midpointI = 1;
			}
			
			inputField.setText(input);
		}
	}

	

	/*public class ListenForWorkClose implements WindowListener
	{
		
	}*/
}
