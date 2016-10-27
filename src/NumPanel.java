import java.awt.BorderLayout;

import javax.swing.*;

public class NumPanel extends JPanel
{
	JLabel num1Label, num2Label;
	JTextField num1Field, num2Field;
	
	public NumPanel()
	{
		num1Label = new JLabel("Number 1");
		num2Label = new JLabel("Number 2");
		num1Field = new JTextField(10);
		num2Field = new JTextField(10);
		
		add(num1Label, BorderLayout.NORTH);
		add(num1Field, BorderLayout.EAST);
		add(num2Label, BorderLayout.SOUTH);
		add(num2Field, BorderLayout.WEST);
		
	}
	
	public double getNum1()
	{
		return Double.parseDouble(num1Field.getText());
	}
	
	public double getNum2()
	{
		return Double.parseDouble(num2Field.getText());
	}

	
}
