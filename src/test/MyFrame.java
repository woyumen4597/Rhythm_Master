package test;


import javax.swing.JFrame;

public class MyFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyFrame(){
		addKeyListener(new MyKeyListener());
	}
	public static void main(String[] args) {
		JFrame frame = new MyFrame();
		frame.setVisible(true);
		frame.setBounds(0,0,400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

