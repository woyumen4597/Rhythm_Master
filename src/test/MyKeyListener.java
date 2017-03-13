package test;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter {

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("你释放了"+e.getKeyChar()+"键");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char charA = e.getKeyChar();
		System.out.println("你按了"+charA+"键");
	}

	

}
