import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Button;

public class OperatorPanel extends JPanel
{
	private JButton addButton;
	private JButton subButton;
	private JButton mulButton;
	private JButton divButton;
	
	public OperatorPanel()
	{
		addButton = new JButton();
		subButton = new JButton();
		mulButton = new JButton();
		divButton = new JButton();
		
		addButton.setText("+");
		subButton.setText("-");
		mulButton.setText("*");
		divButton.setText("/");
		
		add(addButton, BorderLayout.NORTH);
		add(subButton, BorderLayout.SOUTH);
		add(mulButton, BorderLayout.EAST);
		add(divButton, BorderLayout.WEST);
		
	}

}
