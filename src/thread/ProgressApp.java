package thread;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

//�����Ȳ�� ǥ���ϴ� ������Ʈ�� JProgressBar�� ����غ���!!
public class ProgressApp extends JFrame implements ActionListener{
	JProgressBar bar;
	JProgressBar bar2;
	JButton bt_manual,bt_auto;
	int n,n2; //������ ����
	public ProgressApp() {
		setLayout(new FlowLayout());
		bar = new JProgressBar();
		bar2 = new JProgressBar();
		bt_manual = new JButton("����");
		bt_auto = new JButton("�ڵ�");
		
		//��Ÿ��
		bar.setPreferredSize(new Dimension(350,45));
		bar.setBackground(Color.YELLOW);
		bar.setForeground(Color.RED);
		
		bar2.setPreferredSize(new Dimension(350,45));
		bar2.setBackground(Color.YELLOW);
		bar2.setForeground(Color.RED);
		
		add(bar);
		add(bar2);
		add(bt_manual);
		add(bt_auto);
		
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//��ư�� ������ ����
		bt_manual.addActionListener(this);
		bt_auto.addActionListener(this);
	}
	
	//���α׷����ٿ� ���� ä���!!
	public void fillColor() {
		n++;
		bar.setValue(n);
	}
	public void fillColor2() {
		n2++;
		bar2.setValue(n2);
	}
	public void manual() {
		fillColor();
	}
	public void auto() {
		//�����ڴ� ������� �����Ű�� ���� ������ run�� �ۼ��Ѵ�!!
		Thread thread = new Thread() {
			@Override
			public void run() {
				//�Ʒ��� �޼���� JVM�� ȣ���ϴ� ���̴�. why? �׷��� �ý��ۿ� ���� �����ϰ� ���� ȣ���ϱ� ������..
				//���� �̷��� �ؾ� �������� �����带 ������� JVM�� ��Ƽ�׽�ŷ�� ���ֱ⶧����..
				//��ġ OS�� ���μ������� ������� ���� ��Ƽ�׽�ŷ�ϴ°Ͱ� ������ ����..
				while(true) {
					//���������� �ڵ带 �ۼ�!!
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fillColor();
				}
			}
		};
		thread.start(); //Runnable �������� �о����!!
		//�� Runnable�̶�� �ϳ�?
		//start() �޼��忡 Runnable�� ������ ������� jvm�� ���࿩�θ� �����ϴ� �ĺ� �� �ϳ��̹Ƿ� 
		//running�̶� ���� �ʰ� '����'�Ҽ��ִ� �ĺ���� �Ͽ� Runnable ���¿� �ִٰ� �Ѵ�, �̶�
		//���������� JVM�� ���� ������ ������� running�̶� �ϰ� �� �����尡 ������ run()�޼��带 ȣ���ϰԵȴ�.
		//���� JVM�� ȣ���� run�� �����ڰ� ȣ������ ����
		
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					fillColor2();
				}
			}
		};
		thread2.start();
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource(); //������ ��ư�� �ּҸ� ��������
			//��ưŬ�������� ������ �Ӽ�, �޼��带 ȣ���ϰ� �ʹٸ�, �׶� ����ȯ..
			if(obj.equals(bt_manual)) {
				manual();
			}else if(obj.equals(bt_auto)) {
				auto();
			}
	}
	public static void main(String[] args) {
		new ProgressApp();
	}
}
