package animation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AniApp extends JFrame implements ActionListener{
	MyPanel myPanel; //�׸� �׷�����
	JPanel p_north;
	JButton bt_left,bt_up,bt_right,bt_down;
	
	public AniApp() {
		myPanel = new MyPanel();
		p_north = new JPanel();
		
		bt_left = new JButton("��");
		bt_up = new JButton("��");
		bt_right = new JButton("��");
		bt_down = new JButton("��");
		
		p_north.add(bt_left);
		p_north.add(bt_up);
		p_north.add(bt_right);
		p_north.add(bt_down);
		
		add(p_north,BorderLayout.NORTH);
		add(myPanel);
		
		pack(); //������ ���빰���� �پ���...
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//��ư�� ������ ����
		bt_left.addActionListener(this);
		bt_up.addActionListener(this);
		bt_right.addActionListener(this);
		bt_down.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==bt_left) {
			myPanel.velX=-5;
		}else if(obj==bt_up) {
			myPanel.velY=-5;
		}else if(obj==bt_right) {
			myPanel.velX=5;
		}else if(obj==bt_down) {
			myPanel.velY=5;
		}
		
	}
	
	public static void main(String[] args) {
		new AniApp();
	}
}
