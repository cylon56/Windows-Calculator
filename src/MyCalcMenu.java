import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MyCalcMenu implements ActionListener
{
	//Menu items
	private JMenuBar menuBar;
	private JMenu viewMenu, editMenu, helpMenu;
	private JMenuItem copyTextItem, helpItem;
	private JCheckBoxMenuItem viewCheckbox;
	private MyCalcFrame calc;
	
	public MyCalcMenu(MyCalcFrame calcIn)
	{
		calc = calcIn;
		
		//menubar and items
		menuBar = new JMenuBar();
		viewMenu = new JMenu("View");
		viewMenu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(viewMenu);
		
		editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(editMenu);
		
		copyTextItem = new JMenuItem("Copy Text",KeyEvent.VK_T);
		copyTextItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		copyTextItem.getAccessibleContext().setAccessibleDescription("Copies the current string in output to clipboard");
		
		copyTextItem.addActionListener(this);
		editMenu.add(copyTextItem);
		
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(helpMenu);
		
		
		calc.setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == copyTextItem)//copies output into clipboard
		{
			 StringSelection selection = new StringSelection(calc.getOutput());
			 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			 clipboard.setContents(selection, selection);
		}
		
	}
		

}
