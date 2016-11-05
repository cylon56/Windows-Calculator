import javax.swing.*;

public class TestMyCalc 
{

	public static void main(String[] args) 
	{
		MyCalcFrame calc =  new MyCalcFrame();
		MyCalcMenu menu = new MyCalcMenu(calc);
		System.out.println("testing");

		calc.setSize(500, 300);
		calc.setVisible(true);
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calc.setTitle("Calculator");
		
		
	}

}
