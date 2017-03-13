package data_structure;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JLabel;


public class PicRunnable implements Runnable {
	private JLabel pic;
	private Component comp;
	private int i;
	private int x;
	private int border; //每两块图片之间的间距
	private static final int DELAY = 200;
	private int count = 20;
	private int SPEED;
	private Random generator;
	private boolean flag;
	private int sum; //返回总的分数 
	private long start_time;
	private static boolean timeover;	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public PicRunnable(Component comp,JLabel pic,int i,int border,boolean flag,int SPEED,boolean timeover) {
		this.pic = pic;
		this.comp = comp;
		this.i = i;
		this.border = border;
		this.flag = flag;
		this.SPEED = SPEED;
		PicRunnable.timeover = timeover;
		
	}
	public static boolean isTimeover() {
		return timeover;
	}
	public static void setTimeover(boolean timeover) {
		PicRunnable.timeover = timeover;
	}
	@Override
	public void run() {
		start_time = System.currentTimeMillis();
		x = pic.getX();
		generator = new Random();
		int RandomMills = generator.nextInt(3000);
		try {
			Thread.sleep(RandomMills);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		
		while(flag&&!PicRunnable.isTimeover()){
			pic.setLocation(x+i*border,count);
			try{
				Thread.sleep(DELAY);
			}catch(Exception e){
				e.printStackTrace();
			}
			count = count+SPEED;
			if(count-20 > comp.getHeight()){
				sum = sum+5;
				count = 20;
				try {
					Thread.sleep(new Random().nextInt(5000));
				} catch (InterruptedException e) {
				}
			}
			if(start_time+30000<=System.currentTimeMillis()){
				//System.out.println("结束");
				setTimeover(true);
				setFlag(false);
				
			}
			if(!flag){
				pic.setVisible(false);
				pic.setLocation(x+i*border,20);
				pic.setVisible(true);
			}
			
		}
		
		
	}
	
	public int getSPEED() {
		return SPEED;
	}
	public void setSPEED(int sPEED) {
		SPEED = sPEED;
	}
	public Dimension getPreferredSize(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		return d;
	}
}



