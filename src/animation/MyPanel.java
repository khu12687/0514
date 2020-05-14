package animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * ������ �̹����� ��� ���� ����� �츮�� Toolkit�� �̿��Ͽ� �ϵ��ũ�� �ִ� �ڿ��� Ǯ��θ� �� ����� �ߴ�..
 * ������, �� ����� os �������̴�!! D:/~~~
 * �ý��ۿ� ���������� ���� ��θ� ����Ϸ���?
 * -���������� ������!! ��Ű���ȿ��� �ݵ�� Ŭ������ �� �� �ִ°� �ƴϴ�
 * �� Ŭ�����н��� �����ֱ� ������ �Ϲ� �̹���, ���ϵ ��Ű����η� ���� �� �� �ִ�!!
 * */

public class MyPanel extends JPanel {
	BufferedImage img;// is Image Type
	Thread thread; //repaint() ������ ȣ���� ������!!
	int x,y;
	int velX,velY;
	
	public MyPanel() {
		setPreferredSize(new Dimension(600, 500));
		// setBackground(Color.YELLOW);

		// Ŭ������ ���� ������ ���� ��ü!!
		Class myClass = this.getClass();
		//System.out.println("����Ŭ���� �̸��� : " + myClass.getName());

		// �̹����� ��Ű�� �ȿ� ������, �������� ����Ѵ�. �� ���� Ŭ�������� .(��)�� �̿��Ѵ�!!
		URL url = this.getClass().getResource("/res/e5.gif");
		try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		thread = new Thread() {
			//���������� �ڵ带 �����Ѵ�!! �츮�� ��� repaint()ȣ��
			public void run() {
				while(true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tick();
					repaint();
				}
			}
		};
		thread.start();
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
	}
	// �̹��� �׸���!!
	public void paint(Graphics g) {
		//������ �׷��� �׸��� �ִٸ� , �Ʒ��� Ŀ�ٶ� �簢���������� �о������!
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 500); //���� �ο�������
		g.drawImage(img, x, y, 100, 100, this);
	}
}
