import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.Math.*;

public class MyCalcFrame extends JFrame implements ActionListener, MouseListener
{
	private double lastNum;//last number entered into calculator
	private double decNum;//Dec equivalent of current num in output used for conversion
	private String lastOp = "0"; //last operator entered in to calculator
	private String binary[];//holds binary values
	
	private int MAX_INPUT = 16; //max number of digits that can be entered at once
	
	//use currentStatus to determine the output's status
	private int ERROR_STATUS = 2;
	private int RESULT_STATUS = 1;
	private int INPUT_STATUS = 0;
	private int currentStatus;
	 
	private JButton Buttons[];//array to store buttons
	private JRadioButton radioB[];//array to store radio buttons

	private boolean clearedNext; //the current output field will be cleared for the next operation if true
	
	private JPanel outPanel, outputPanel, binaryPanel, numPanel, radioPanel, radioPanel1, radioPanel2;
	private JTextArea outputField;
	private JLabel binaryField[];
	
	public MyCalcFrame()
	{
		System.out.println("MyCalc created");
		outPanel = new JPanel();
		outPanel.setSize(600, 80);
		outputPanel = new JPanel();
		numPanel = new JPanel();
		binaryPanel = new JPanel();
		binaryPanel.setSize(600, 80);
		radioPanel = new JPanel();
		radioPanel1 = new JPanel();
		radioPanel2 = new JPanel();
		add(outPanel, BorderLayout.NORTH);
		add(binaryPanel);
		
		binary = new String[16];//declare binary string array
		
		
		//OutPut field
		outputField = new JTextArea("0", 1, 1);
		outputField.setFont(new Font("Courier New", Font.ITALIC, 18));
		outputField.setEditable(false);
		outputField.setOpaque(true);
		
		outputPanel.add(outputField, BorderLayout.NORTH);
		
		
		
		//binary field
		binaryField = new JLabel[16];
		
		for(int i = 0; i < 16; i++)
		{
			binaryField[i] = new JLabel("0000");
			binaryField[i].setFont(new Font("Courier New", Font.ITALIC, 12));
			binaryField[i].setOpaque(true);
			binaryField[i].setSize(1, 4);
			//binaryPanel.add(binaryField[i], BorderLayout.AFTER_LAST_LINE);
		}
	

		
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
		
		
		//use actionlistener on buttons to record button pushes
		for(int i = 0; i < Buttons.length; i++)
		{
			Buttons[i].addActionListener(this);
			Buttons[i].addMouseListener(this);
		}
		
		//setup radio panel and radio buttons
		radioB = new JRadioButton [8];
		
		radioB[0] = new JRadioButton("Hex");
		radioB[1] = new JRadioButton("Dec");
		radioB[2] = new JRadioButton("Oct");
		radioB[3] = new JRadioButton("Bin");
		radioB[4] = new JRadioButton("Qword");
		radioB[5] = new JRadioButton("Dword");
		radioB[6] = new JRadioButton("Word");
		radioB[7] = new JRadioButton("Byte");
		
		ButtonGroup group1 = new ButtonGroup();
		for(int i = 0; i < 4; i++)
		{
			group1.add(radioB[i]);
		}
		ButtonGroup group2 = new ButtonGroup();
		for(int i = 4; i < 8; i++)
		{
			group2.add(radioB[i]);
		}
		
		
		//setup layout for numpanel
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
				
				
				//setup layout for binaryPanel
				GroupLayout binaryLayout = new GroupLayout(binaryPanel);
				binaryPanel.setLayout(binaryLayout);
				
				binaryLayout.setAutoCreateGaps(true);
				binaryLayout.setAutoCreateContainerGaps(true);
				
				
				binaryLayout.setHorizontalGroup(
						binaryLayout.createSequentialGroup()
						  .addGroup(binaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    		  .addComponent(binaryField[15])
					    		  .addComponent(binaryField[7]))
					      .addGroup(binaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    		  .addComponent(binaryField[14])
					    		  .addComponent(binaryField[6]))
					      .addGroup(binaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    		  .addComponent(binaryField[13])
					    		  .addComponent(binaryField[5]))
					      .addGroup(binaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    		  .addComponent(binaryField[12])
					    		  .addComponent(binaryField[4]))
					      .addGroup(binaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    		  .addComponent(binaryField[11])
					    		  .addComponent(binaryField[3]))
					      .addGroup(binaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    		  .addComponent(binaryField[10])
					    		  .addComponent(binaryField[2]))
					      .addGroup(binaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    		  .addComponent(binaryField[9])
					    		  .addComponent(binaryField[1]))
					      .addGroup(binaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    		  .addComponent(binaryField[8])
					    		  .addComponent(binaryField[0]))
						  
						      
						);
				binaryLayout.setVerticalGroup(
						binaryLayout.createSequentialGroup()
						.addGroup(binaryLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    		  .addComponent(binaryField[15])
					    		  .addComponent(binaryField[14])
					    		  .addComponent(binaryField[13])
					    		  .addComponent(binaryField[12])
					    		  .addComponent(binaryField[11])
					    		  .addComponent(binaryField[10])
					    		  .addComponent(binaryField[9])
					    		  .addComponent(binaryField[8]))
						.addGroup(binaryLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								  .addComponent(binaryField[7])
					    		  .addComponent(binaryField[6])
					    		  .addComponent(binaryField[5])
					    		  .addComponent(binaryField[4])
								  .addComponent(binaryField[3])
					    		  .addComponent(binaryField[2])
					    		  .addComponent(binaryField[1])
					    		  .addComponent(binaryField[0]))
						      
						);		
				
				

		
				GroupLayout parentRadioLayout = new GroupLayout(radioPanel);
				binaryPanel.setLayout(binaryLayout);
				
				parentRadioLayout.setAutoCreateGaps(true);
				parentRadioLayout.setAutoCreateContainerGaps(true);
				
				
				parentRadioLayout.setHorizontalGroup(
						parentRadioLayout.createSequentialGroup()
						.addGroup(parentRadioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(radioPanel1)
					    		.addComponent(radioPanel2))
					    		  .addComponent(radioPanel1)
					    		  .addComponent(radioPanel2)
						  
						      
						);
				parentRadioLayout.setVerticalGroup(
						parentRadioLayout.createSequentialGroup()
						.addComponent(radioPanel1)
			    		  .addComponent(radioPanel2)
						      
						);		
				
				
		add(radioPanel, BorderLayout.WEST);
		radioPanel.setLayout(parentRadioLayout);
		
		
		//setup layout for radioPanel	
		GridLayout radioLayout = new GridLayout(4,1);
		radioPanel1.setLayout(radioLayout);
		radioPanel1.setBorder(BorderFactory.createLineBorder(Color.black)); 
		radioPanel.add(radioPanel1);
		
		for(int i = 0; i < 4; i++)
		{
			radioPanel1.add(radioB[i]);
			radioB[i].addActionListener(this);
		}
		
		radioB[1].setSelected(true);
		
		radioPanel2.setLayout(radioLayout);
		radioPanel2.setBorder(BorderFactory.createLineBorder(Color.black)); 
		radioPanel.add(radioPanel2);
				
		
		for(int i = 4; i < 8; i++)
		{
			radioPanel2.add(radioB[i]);
			radioB[i].setEnabled(false);
		}
		
		radioB[4].setSelected(true);
		radioB[5].setEnabled(false);
		radioB[6].setEnabled(false);
		radioB[7].setEnabled(false);
		
		//setouputPanel layout
		GridLayout outputLayout = new GridLayout(2, 1);
		outPanel.setLayout(outputLayout);
		
		
		outPanel.add(outputPanel);
		outPanel.add(binaryPanel);

		
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
		
		
		if(currentStatus != ERROR_STATUS)
		{
			//changes binary output
			binaryCalculate();
			
			if(e.getSource() != radioB[0] && e.getSource() != radioB[1] && e.getSource() != radioB[2] && e.getSource() 
					!= radioB[3])
			{
				decNum = getOutputNum(); 
			}
			else convertOutput(e);
		}
		
	}

	public void convertOutput(ActionEvent e)
	{
		double output = getOutputNum();
		if(e.getSource() == radioB[1])
		{
			setOutputNum(decNum);
			for(int i =0; i < 28; i++)
			{
				Buttons[i].setEnabled(true);
			}
		}
		else if(e.getSource() == radioB[0] || e.getSource() == radioB[2] || e.getSource() == radioB[3])
		{
			if(e.getSource() == radioB[0])setOutput(Double.toHexString(decNum));
			else if(e.getSource() == radioB[2])setOutput(Integer.toOctalString((int) decNum));
			else if(e.getSource() == radioB[3])setOutput(Integer.toBinaryString((int) decNum));
			
			
			for(int i =0; i < 28; i++)
			{
				Buttons[i].setEnabled(false);
			}
		}
	}
	
	public void binaryCalculate()
	{
		
			String fullBinaryNum = Integer.toBinaryString((int) getOutputNum());
			
			System.out.println(fullBinaryNum);
			
			for(int k = 0; k < 16; k++)
			{
				//if this binary chunk will fully fill the array slot
				if((k+1)*4 <= fullBinaryNum.length())
				{
					binary[k] = fullBinaryNum.substring(fullBinaryNum.length()-(k+1)*4, fullBinaryNum.length()-k*4);
				}
				//if the binary chunk will only partially fill the array
				else if(fullBinaryNum.length() > k*4  && fullBinaryNum.length() < (k+1)*4)
				{
					binary[k] = fullBinaryNum.substring(0, fullBinaryNum.length()-k*4);
					for(int i = 0; i < (k+1)*4 - fullBinaryNum.length(); i++)
					{
						binary[k] = "0"+ binary[k];
					}
				}
				else
				{
					binary[k] = "0000";
				}
	
			}
			
			for(int i = 0; i < 16; i ++)
			{
				binaryField[i].setText(binary[i]);
			}
		
	}
	
	
	public void setView(boolean state)
	{
		outPanel.setVisible(state);
		numPanel.setVisible(state);
		radioPanel1.setVisible(state);
		radioPanel2.setVisible(state);
		
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
	
	
	void operatorProcess(String s)//process /, *, -, +, sqrt, %, 1/x, .
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
			else if(s == ".")
			{
				if(currentNum % 1 == 0)//checks if dot is already present
				{
					setOutput(getOutput() + ".");
				}
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
		if(output.length() > 1)
		{
		output = output.substring(0, output.length()-1);//removes first digit
		}
		else output= "0";
		setOutput(output);
		
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
		for(int i = 0; i < Buttons.length; i++)
		{
			if(e.getSource() == Buttons[i])
			{
				Buttons[i].setBackground(Color.YELLOW);
				return;
			}
			
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		for(int i = 0; i < Buttons.length; i++)
		{
			if(e.getSource() == Buttons[i])
			{
				Buttons[i].setBackground(UIManager.getColor("control"));
				return;
			}
			
		}
		
	}
	
}