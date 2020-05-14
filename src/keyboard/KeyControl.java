package keyboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class KeyControl extends JFrame{
	JPanel panel;
	int x,y;
	int velX,velY;
	Thread thread;
	
	public KeyControl() {
		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.setColor(Color.YELLOW); //����Ʈ ���� ���� ����
				g.fillRect(0, 0, 600, 500); //ä������
				
				g.setColor(Color.RED); //����Ʈ�� ���� ����
				g.drawRect(x, y, 50, 50);
			}
		};
		thread = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gameLoop();
				}
			}
		};
		panel.setPreferredSize(new Dimension(600,500));
		add(panel);
		
		pack(); //�г��� ũ�⸸ŭ Ȯ ����
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//�гΰ� �����ʿ���
		//��Ŀ���� �����찡 �ö� ���, �Ʒ��� �гο� ���� Ű���� �̺�Ʈ�� ������� �ʴ´�!!
		//�ذ�å? �гο� ��Ŀ�� �÷������� �ȴ�!!
		panel.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch(key) {
					case KeyEvent.VK_LEFT:velX=-5 ;break;
					case KeyEvent.VK_UP:velY=-5 ;break;
					case KeyEvent.VK_RIGHT:velX=5 ;break;
					case KeyEvent.VK_DOWN:velY=5 ;break;
				}
			}
		});
		thread.start();
		
		panel.setRequestFocusEnabled(true); //�гο� 
		panel.requestFocus(); //�гο� ���ڼ� �ø���
	}
	public void gameLoop() {
		x+=velX;
		y+=velY;
		panel.repaint();
	}
	public static void main(String[] args) {
		new KeyControl();
	}
}
