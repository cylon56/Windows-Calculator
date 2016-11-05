import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.Math.*;

public class MyCalcFrame extends JFrame implements ActionListener, MouseListener
{
	private double lastNum;//last number entered into calculator
	private String lastOp = "0"; //last operator entered in to calculator
	
	private int MAX_INPUT = 20; //max number of digits that can be entered at once
	
	//use currentStatus to determine the output's status
	private int ERROR_STATUS = 2;
	private int RESULT_STATUS = 1;
	private int INPUT_STATUS = 0;
	private int currentStatus;
	 
	private JButton Buttons[];//array to store buttons

	private boolean clearedNext; //the current output field will be cleared for the next operation if true
	
	private JPanel outPanel, numPanel;
	private JTextArea outputField;
	
	public MyCalcFrame()
	{
		System.out.println("MyCalc created");
		outPanel = new JPanel();
		numPanel = new JPanel();
		add(outPanel, BorderLayout.NORTH);
		
		
		//OutPut field
		outputField = new JTextArea("0", 1, 1);
		outputField.setFont(new Font("Courier New", Font.ITALIC, 18));
		outputField.setEditable(false);
		outputField.setOpaque(true);
		
		outPanel.add(outputField, BorderLayout.EAST);
		
		//setup Buttons
		Buttons = new JButton[28];		
		
		//first 9 numeric buttons
		for(int i = 0; i <= 9; i++)
		{
			Buttons[i] = new JButton(String.valueOf(i));
		}
		
				
		//Operator buttons
		Buttons[10] = new JButton("+/-");//left column right of numpad
		Buttons[11] = new JButton("/");
		Buttons[12] = new JButton("*");
		Buttons[13] = new JButton("-");
		Buttons[14] = new JButton("+");
		Buttons[15]	= new JButton("√");//right column right of numpad
		Buttons[16] = new JButton("%");
		Buttons[17] = new JButton("1/x");
		Buttons[18] = new JButton("=");
		Buttons[19] = new JButton(".");//next to 0 below numpad
		//function buttons
		Buttons[20] = new JButton("<-");//row above numpad
		Buttons[21] = new JButton("CE");
		Buttons[22] = new JButton("C");
		Buttons[23] = new JButton("MC");//two rows above numpad
		Buttons[24] = new JButton("MR");
		Buttons[25] = new JButton("MS");
		Buttons[26] = new JButton("M+");
		Buttons[27] = new JButton("M-");
		
		
		
		//setup numPanel for the numpanel buttons
		add(numPanel, BorderLayout.EAST);
		/*numPanel.add(addButton, BorderLayout.NORTH);
		numPanel.add(subButton, BorderLayout.SOUTH);
		numPanel.add(mulButton, BorderLayout.EAST);
		numPanel.add(divButton, BorderLayout.WEST);*/
		
		//add panel3 to display results
		
		//use actionlistener on buttons to record button pushes
		for(int i = 0; i < Buttons.length; i++)
		{
			Buttons[i].addActionListener(this);
		}
		
		//setup layout
		GroupLayout numLayout = new GroupLayout(numPanel);
		numPanel.setLayout(numLayout);
		
		numLayout.setAutoCreateGaps(true);
		numLayout.setAutoCreateContainerGaps(true);
		
		
		numLayout.setHorizontalGroup(
				   numLayout.createSequentialGroup()
				   	  .addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				           .addComponent(Buttons[23])
				           .addComponent(Buttons[20])
				           .addComponent(Buttons[7])
				           .addComponent(Buttons[4])
				           .addComponent(Buttons[1])
				           .addComponent(Buttons[0]))
				   	.addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					           .addComponent(Buttons[24])
					           .addComponent(Buttons[21])
					           .addComponent(Buttons[8])
					           .addComponent(Buttons[5])
					           .addComponent(Buttons[2]))
				   	.addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					           .addComponent(Buttons[25])
					           .addComponent(Buttons[22])
					           .addComponent(Buttons[9])
					           .addComponent(Buttons[6])
					           .addComponent(Buttons[3])
					           .addComponent(Buttons[19]))
				   	.addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					           .addComponent(Buttons[26])
					           .addComponent(Buttons[10])
					           .addComponent(Buttons[11])
					           .addComponent(Buttons[12])
					           .addComponent(Buttons[13])
					           .addComponent(Buttons[14]))
				   	.addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					           .addComponent(Buttons[27])
					           .addComponent(Buttons[15])
					           .addComponent(Buttons[16])
					           .addComponent(Buttons[17])
					           .addComponent(Buttons[18]))
				      
				);
				numLayout.setVerticalGroup(
				   numLayout.createSequentialGroup()
				      .addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				           .addComponent(Buttons[23])
				           .addComponent(Buttons[24])
				           .addComponent(Buttons[25])
				           .addComponent(Buttons[26])
				           .addComponent(Buttons[27]))
				      .addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					           .addComponent(Buttons[20])
					           .addComponent(Buttons[21])
					           .addComponent(Buttons[22])
					           .addComponent(Buttons[10])
					           .addComponent(Buttons[15]))
				      .addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					           .addComponent(Buttons[7])
					           .addComponent(Buttons[8])
					           .addComponent(Buttons[9])
					           .addComponent(Buttons[11])
					           .addComponent(Buttons[16]))
				      .addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					           .addComponent(Buttons[4])
					           .addComponent(Buttons[5])
					           .addComponent(Buttons[6])
					           .addComponent(Buttons[12])
					           .addComponent(Buttons[17]))
				      .addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					           .addComponent(Buttons[1])
					           .addComponent(Buttons[2])
					           .addComponent(Buttons[3])
					           .addComponent(Buttons[13])
					           .addComponent(Buttons[18]))
				      .addGroup(numLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					           .addComponent(Buttons[0])
					           .addComponent(Buttons[19])
					           .addComponent(Buttons[14]))
				      
				);
		
	}

	//actions performed when buttons are pressed
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//actions for numeric buttons
		if(e.getSource() == Buttons[0]) addNumToOutput(0);
		else if(e.getSource() == Buttons[1]) {addNumToOutput(1);}
		else if(e.getSource() == Buttons[2]) addNumToOutput(2);
		else if(e.getSource() == Buttons[3]) addNumToOutput(3);
		else if(e.getSource() == Buttons[4]) addNumToOutput(4);
		else if(e.getSource() == Buttons[5]) addNumToOutput(5);
		else if(e.getSource() == Buttons[6]) addNumToOutput(6);
		else if(e.getSource() == Buttons[7]) addNumToOutput(7);
		else if(e.getSource() == Buttons[8]) addNumToOutput(8);
		else if(e.getSource() == Buttons[9]) addNumToOutput(9);
		
		//actions for operator buttons
		else if(e.getSource() == Buttons[10])changeSign();
		
		else if(e.getSource() == Buttons[11])operatorProcess("/");
		else if(e.getSource() == Buttons[12])operatorProcess("*");
		else if(e.getSource() == Buttons[13])operatorProcess("-");
		else if(e.getSource() == Buttons[14])operatorProcess("+");
		else if(e.getSource() == Buttons[15])operatorProcess("sqrt");
		else if(e.getSource() == Buttons[16])operatorProcess("%");
		else if(e.getSource() == Buttons[17])operatorProcess("1/x");
		else if(e.getSource() == Buttons[18])processEquals();
		else if(e.getSource() == Buttons[19]);
		else if(e.getSource() == Buttons[20])backspace();
		else if(e.getSource() == Buttons[21])clearAll();
		else if(e.getSource() == Buttons[22])clearCurrent();
		
		
	}

	
	public String getOutput()//returns number in output text field
	{
		return outputField.getText();
	}
	
	
	double getOutputNum()//returns number in output text field
	{
		return Double.parseDouble(outputField.getText());
	}
	
	void setOutput(String s)//sets string in output text field
	{
		outputField.setText(s);
	}
	
	void setOutputNum(double num)//sets num converted to string in output text field
	{
		outputField.setText(Double.toString(num));
	}
	
	void addNumToOutput(int num)//adds a digit to the output
	{
		if(clearedNext)
		{
			setOutput("");
		}
		String outputString = getOutput();
		
		if(outputString.indexOf("0") == 0)//if the first digit is zero, then remove it
		{
			outputString = outputString.substring(1);
		}
		
		if((!outputString.equals("0") || num > 0) && outputString.length() < MAX_INPUT) //if less than max and num/output isn't zero
		{
			setOutput(outputString + num);//add digit to output
			
		}
		
		clearedNext = false;
		currentStatus = INPUT_STATUS;
	}
	
	void changeSign()//change the current sign of the outputNum
	{
		if(currentStatus == INPUT_STATUS)
		{
			String outputString = getOutput();
			
			if(outputString.length() > 0 && !outputString.equals("0"))
			{
				if(outputString.indexOf("-") == 0)//if negative, make positive
				{
					setOutput(outputString.substring(1));
				}
				
				else//else, make negative
				{
					setOutput("-" + outputString);
				}
			}
		}
			
	}
	void operatorProcess(String s)//process /, *, -, +, sqrt, %, 1/x
	{
		if(currentStatus != ERROR_STATUS)
		{
			double currentNum = getOutputNum();
			if(s == "+" || s == "-" || s == "*" || s=="/")
			{
				lastNum = currentNum;
				clearedNext = true;
				lastOp = s;
			}
			else if(s=="sqrt")
			{
				processResult(Math.sqrt(currentNum));			
			}
			else if (s =="%")
			{
				setOutputNum(currentNum/100);
			}
			else if (s =="1/x")
			{
				if(currentNum == 0)//for divide by zero error
				{
					currentStatus = ERROR_STATUS;
					setOutput("cannot divide by zero");
					clearedNext = true;
					lastNum = 0;
				}
				else setOutputNum(1/currentNum);
				
			}
		}
	}
	
	void processEquals()
	{
		if(currentStatus != ERROR_STATUS && lastOp != "0")
		{
			double result;
			double currentNum = getOutputNum();
			
			if(lastOp == "+")
			{
				result = lastNum + currentNum; 
				processResult(result);
			}
			else if(lastOp == "-")
			{
				result = lastNum - currentNum; 
				processResult(result);
			}
			else if(lastOp == "*")
			{
				result = lastNum * currentNum; 
				processResult(result);
			}
			else if(lastOp == "/")
			{
				if(currentNum == 0)//for divide by zero error
				{
					currentStatus = ERROR_STATUS;
					setOutput("cannot divide by zero");
					clearedNext = true;
					lastNum = 0;
				}
				else 
				{
					result = lastNum / currentNum; 
					processResult(result);
				}
			}
			lastOp = "0";
		}
	}
	
	
	void processResult(double result)
	{
		setOutputNum(result);
		lastNum = result;
		currentStatus = RESULT_STATUS;
		clearedNext = true;
	}
	
	void clearAll()
	{
		setOutput("0");
		lastNum = 0;
		lastOp = "0";
		currentStatus = INPUT_STATUS;
		clearedNext = true;
		
	}
	void clearCurrent()
	{
		setOutput("0");
		currentStatus = INPUT_STATUS;
		clearedNext = true;
		
	}
	
	void backspace()
	{
		String output = getOutput();
		output = output.substring(0, output.length()-1);//removes first digit
		System.out.println(output);
		setOutput(output);
		
	}
	
	public void setDisplayHidden()
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
