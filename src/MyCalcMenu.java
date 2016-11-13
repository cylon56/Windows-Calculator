import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MyCalcMenu implements ActionListener, ItemListener
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
		
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(helpMenu);
		
		copyTextItem = new JMenuItem("Copy Text",KeyEvent.VK_T);
		copyTextItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		copyTextItem.getAccessibleContext().setAccessibleDescription("Copies the current string in output to clipboard");
		
		helpItem = new JMenuItem("Help",KeyEvent.VK_T);
		helpItem.getAccessibleContext().setAccessibleDescription("Brings up help documentation");
		
		viewCheckbox = new JCheckBoxMenuItem("Hidden");
		viewCheckbox.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		viewCheckbox.getAccessibleContext().setAccessibleDescription("Makes calculator hidden");
		
		copyTextItem.addActionListener(this);
		viewCheckbox.addItemListener(this);
		
		viewMenu.add(viewCheckbox);
		editMenu.add(copyTextItem);
		helpMenu.add(helpItem);
		
		
		
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
		else if(e.getSource() == helpItem)//opens help link
		{
			openWebpage("https://support.microsoft.com/en-us/help/14089/windows-8-calculator-app-faq");
		}
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		
		if(e.getStateChange() == ItemEvent.SELECTED)
		{
			calc.setView(false);
		}
		else if(e.getStateChange() == ItemEvent.DESELECTED)
		{
			calc.setView(true);
		}
	}
	
	public static void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	

		

}
