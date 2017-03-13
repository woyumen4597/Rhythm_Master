				package surface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import data_structure.PicRunnable;

@SuppressWarnings("serial")
public class MasterFrame extends JFrame{
	private int KEY_NUM;
	@SuppressWarnings("unused")
	private int SPEED;
	private JPanel key_panel;
	private JPanel block_panel;
	private JPanel control_panel;
	private JPanel score_panel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private ImageIcon icon;
	private int border;
	private List<JLabel> pics = new ArrayList<JLabel>();
	private List<Thread> threads = new ArrayList<Thread>();
	private List<PicRunnable> runnables = new ArrayList<PicRunnable>();
	private List<JButton> keys = new ArrayList<JButton>();
	private List<Integer> kb4 = new ArrayList<Integer>();
	private List<Integer> kb6 = new ArrayList<Integer>();
	private List<Character> kbc4 = new ArrayList<Character>();
	private List<Character> kbc6 = new ArrayList<Character>();
	private static int score=0;
	private JTextArea textArea;
	
	public MasterFrame(int KEY_NUM,final int SPEED){
		this.KEY_NUM = KEY_NUM;
		this.SPEED = SPEED;
		key_panel = new JPanel();
		block_panel = new JPanel();
		control_panel = new JPanel();
		score_panel = new JPanel();
		JLabel score_label = new JLabel("提示信息: ");
		score_label.setFont(new Font("楷体", Font.BOLD, 14));
		textArea = new JTextArea("",1,2);
		textArea.setFont(new Font("楷体", Font.BOLD, 16));
		textArea.setBackground(getBackground());
		textArea.setEditable(false);
		score_panel.setLayout(new GridLayout(2, 1));
		score_panel.add(score_label);
		score_panel.add(textArea);
		/*四键模式*/
		kb4.add(KeyEvent.VK_S);
		kb4.add(KeyEvent.VK_D);
		kb4.add(KeyEvent.VK_J);
		kb4.add(KeyEvent.VK_K);
		kbc4.add('S');
		kbc4.add('D');
		kbc4.add('J');
		kbc4.add('K');
		/*六键模式*/
		kb6.add(KeyEvent.VK_A);
		kb6.add(KeyEvent.VK_S);
		kb6.add(KeyEvent.VK_D);
		kb6.add(KeyEvent.VK_J);
		kb6.add(KeyEvent.VK_K);
		kb6.add(KeyEvent.VK_L);
		kbc6.add('A');
		kbc6.add('S');  
		kbc6.add('D');
		kbc6.add('J');
		kbc6.add('K');
		kbc6.add('L');
		/*菜单栏*/
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		/*文件菜单*/
		fileMenu = new JMenu("File");
		JMenuItem start_item = new JMenuItem("Start");
		JMenuItem stop_item = new JMenuItem("Stop");
		JMenuItem exit_item = new JMenuItem("Exit");
		exit_item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(start_item);
		fileMenu.add(stop_item);
		fileMenu.add(exit_item);
		menuBar.add(fileMenu);
	
		
		
		/* 右边控制按钮的上下布局*/  
		JButton start_button = new JButton("Start");
		
			ActionListener start_listner = new ActionListener() {
			boolean flag = true;
			boolean timeover = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int j = 0;j<pics.size();j++){
					JLabel pic = pics.get(j);
					Runnable r = new PicRunnable(block_panel, pic,j,border,flag,SPEED,timeover);
					Thread thread = new Thread(r);
					threads.add(thread);
					runnables.add((PicRunnable) r);
					thread.start();
				}
				final java.util.Timer timer = new java.util.Timer(true);
				MyTask task = new MyTask(1);
				timer.schedule(task, 30000);
			}
		};
			start_button.addActionListener(start_listner);
			start_item.addActionListener(start_listner);
		JButton stop_button = new JButton("Stop");
		
		ActionListener stop_listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(PicRunnable r:runnables){
					r.setFlag(false);
				}
			}
		};
		stop_button.addActionListener(stop_listener);
		stop_item.addActionListener(stop_listener);
		JButton return_button = new JButton("Return");
		return_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
				MasterFrame.this.dispose(); //关闭当前窗口 
				
			}
		});
		control_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		control_panel.add(start_button,c);
		c.gridy = 2;
		control_panel.add(stop_button,c);
		c.gridy = 3;
		control_panel.add(return_button,c);
		
		/* 界面的布局*/
		add(key_panel,BorderLayout.SOUTH);
		add(block_panel,BorderLayout.CENTER);
		add(score_panel,BorderLayout.WEST);
		add(control_panel, BorderLayout.EAST);
		
		
		for(int i = 1;i<=KEY_NUM;i++){
//			addButton(i+"");
			addBlock(i+"",KEY_NUM);
		}
		/*键盘事件*/
		start_button.addKeyListener(new MyKeyListener());
		start_button.setFocusable(true);
		textArea.setText("模式："+KEY_NUM+"\n"+"速度："+SPEED);
		pack();
	}
	
	
	public void addBlock(String str,int key_num) {
		
		icon = new ImageIcon("images/3.png");
		final JLabel pic = new JLabel(icon);
		pics.add(pic);
		ArrayList<Character> kbc = new ArrayList<Character>();
		block_panel.add(pic);
		if(key_num == 4){
			kbc = (ArrayList<Character>) kbc4;  //四键模式
		}else{
			kbc = (ArrayList<Character>) kbc6;
		}
		int i = Integer.parseInt(str);
		JButton button = new JButton(kbc.get(i-1)+"");
		int picWidth = icon.getIconWidth();
		button.setPreferredSize(new Dimension(picWidth, 50)); //设置按钮的大小
		key_panel.add(button);
		keys.add(button);
		

	}
	public class MyTask extends TimerTask{
		@SuppressWarnings("unused")
		private int taskid;
		public MyTask(int taskid){
			this.taskid = taskid;
		}
		@Override
		public void run() {
			new ResultFrame(MasterFrame.getScore());
			
		}
	}
	
	public Dimension getPreferredSize(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		return d;
	}
	
	public class MyKeyListener extends KeyAdapter {
		
		@Override
		public void keyReleased(KeyEvent e) {
			
			int keycode = e.getKeyCode();
			List<Integer> kb =new ArrayList<Integer>();
			if(KEY_NUM == 4){
				kb = kb4;
			}else{
				kb = kb6;
			}
			for(Integer i:kb){
				if(i.equals((Integer)keycode)){
					int j = kb.indexOf(i);
					keys.get(j).setBackground(getBackground());
				}
			}	
		}

		@Override
		public  void keyPressed(KeyEvent e) {
			int top = block_panel.getHeight()-pics.get(1).getHeight();
			int bottom = block_panel.getHeight();
			int keycode = e.getKeyCode(); 
			//System.out.println("你按了"+keycode+"键");
			List<Integer> kb =new ArrayList<Integer>();
			if(KEY_NUM == 4){
				kb = kb4;
			}else{
				kb = kb6;
			}
			for(Integer i:kb){
				if(i.equals((Integer)keycode)){
					int j = kb.indexOf(i);
					keys.get(j).setBackground(Color.LIGHT_GRAY);
					int y = pics.get(j).getY();
					if(y>=top && y<=bottom){
						score+=5;
//						System.out.println("boom");
//						System.out.println(score);
						setScore(score);
						
					}
					
 				}
			}
			
		}
		
		

	}
	public int getKEY_NUM() {
		return KEY_NUM;
	}


	public void setKEY_NUM(int kEY_NUM) {
		this.KEY_NUM = kEY_NUM;
	}
	
	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		MasterFrame.score = score;
	}
}
