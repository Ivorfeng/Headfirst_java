import javax.swing.*;
import java.awt.event.*;
public class SimpleGui1 implements ActionListener{
	JButton button;
	private int cnt;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleGui1 gui = new SimpleGui1();
		gui.go();
		
		
		
	}
	
	public void go(){
		JFrame frame = new JFrame();
		button = new JButton("click me");
		//向按钮注册
		button.addActionListener(this);
		
		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	//实现interface上的方法，这是真正处理事件的方法
	public void actionPerformed(ActionEvent event) {
		cnt++;
		button.setText("I've been clicked " +cnt + " times!");
	}
}
