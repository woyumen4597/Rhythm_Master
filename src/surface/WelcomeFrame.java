package surface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class WelcomeFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private int num=4;
	private int speed=15;
	
	public WelcomeFrame(){
		
		
		
		/*选择模式*/
		JPanel modepanel = new JPanel();
		JLabel label1 = new JLabel("模式选择");
		label1.setFont(new Font("楷体", Font.BOLD, 15));
		JButton kb4 = new JButton("四键");
		kb4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNum(4);
			}
		});
		JButton kb6 = new JButton("六键");
		kb6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNum(6);
			}
		});
		modepanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridx = 0;
		c1.gridy = 0;
		modepanel.add(label1,c1);
		c1.gridy = 1;
		modepanel.add(kb4,c1);
		c1.gridy = 2;
		modepanel.add(kb6,c1);
		add(modepanel,BorderLayout.WEST);
		
		/*速度选择*/
		JPanel speedpanel = new JPanel();
		JLabel label2 = new JLabel("速度选择");
		label2.setFont(new Font("楷体", Font.BOLD, 15));
		JButton fastButton = new JButton("Fast");
		JButton mediumButton = new JButton("Medium");
		JButton slowButton = new JButton("Slow");
		fastButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setSpeed(20);
			}
		});
		mediumButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setSpeed(15);
			}
		});
		slowButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setSpeed(10);
			}
		});
		speedpanel.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 0;
		c2.gridy = 0;
		speedpanel.add(label2,c2);
		c2.gridy = 1;
		speedpanel.add(fastButton,c2);
		c2.gridy = 2;
		speedpanel.add(mediumButton,c2);
		c2.gridy = 3;
		speedpanel.add(slowButton,c2);
		add(speedpanel,BorderLayout.EAST);
		//speedpanel.setLocation(0, 200);
		/*欢迎界面 */
		JPanel welcome_panel = new JPanel();
		JLabel welcome_label = new JLabel("欢迎游戏！");
		welcome_label.setFont(new Font("楷体", Font.BOLD, 20));
		welcome_panel.add(welcome_label);
		add(welcome_panel, BorderLayout.CENTER);
		
		
		
		/*确定按钮*/
		JPanel confirm_panel = new JPanel();
		JButton confirm_button = new JButton("Confirm");
		confirm_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MasterFrame frame = new MasterFrame(getNum(),getSpeed());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Dimension dimension = toolkit.getScreenSize();
				frame.setTitle("Rhythm Master");
				frame.setVisible(true);
				frame.setBounds(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());
				frame.setResizable(false);
			}
		});
		confirm_panel.add(confirm_button);
		add(confirm_panel,BorderLayout.SOUTH);
		/* 关于菜单*/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu aboutMenu = new JMenu("About");
		JMenuItem aboutItem = new JMenuItem("About");
		JMenuItem helpItem = new JMenuItem("Help");
		aboutMenu.add(aboutItem);
		aboutMenu.add(helpItem);
		aboutItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "版本号:1.0"+"\n"+"作者:"+"杨坚，金荣钏，张朝");
			}
		});
		helpItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "游戏说明:\n欢迎界面选择模式和速度，\n点击confirm进入主界面，\n操控键盘相应按键以实现游戏功能，\n游戏时间为30秒，时间到了，显示结果界面。");
				
			}
		});
		menuBar.add(aboutMenu);
		
	}

	public int getNum() {
		return num;
	}

	public  void setNum(int num) {
		this.num = num;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
