import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Button;

public class ResultPanel extends JPanel
{
	JLabel resultLabel;
	JTextField resultField;
	
	public ResultPanel()
	{
		resultLabel = new JLabel("Result: ");
		resultField = new JTextField(10);
		
		add(resultLabel, BorderLayout.WEST);
		add(resultField, BorderLayout.EAST);
	}
	
	public void setText(String s)
	{
		resultField.setText(s);
	}

}
