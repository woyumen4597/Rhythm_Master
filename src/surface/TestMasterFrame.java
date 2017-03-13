package surface;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TestMasterFrame{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				WelcomeFrame welcomeFrame = new WelcomeFrame();
				welcomeFrame.setTitle("Rhythm Master");
			    
				welcomeFrame.setVisible(true);
				welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				welcomeFrame.setBounds(500, 200, 400, 300);
			}
		});
	}
}