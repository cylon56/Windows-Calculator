import javax.swing.*;

public class TestMyCalc {

	public static void main(String[] args) 
	{
		MyCalcFrame calc =  new MyCalcFrame();
		System.out.println("testing");
		
		calc.setSize(400, 400);
		calc.setVisible(true);
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calc.setTitle("My Calculator");
		
		
	}

}
