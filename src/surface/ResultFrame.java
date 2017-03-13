package surface;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int score;
	public ResultFrame(int score){
		this.setScore(score);
		JPanel infopanel = new JPanel();
		JLabel infolabel = new JLabel("您的得分是:");
		infolabel.setFont(new Font("楷体", Font.BOLD, 20));
		infopanel.add(infolabel);
		add(infopanel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		panel.add(label);
		add(panel,BorderLayout.CENTER);
		label.setText(score+"分");
		label.setFont(new Font("楷体", Font.BOLD, 25));
		JPanel control_panel = new JPanel();
		JButton button = new JButton("Return");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ResultFrame.this.dispose();
			}
		});
		control_panel.add(button);
		add(control_panel,BorderLayout.SOUTH);
		setTitle("Rhythm Master");
		setVisible(true);
		setBounds(400, 200, 400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
