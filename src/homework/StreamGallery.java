package homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StreamGallery extends JFrame implements ActionListener{
	JPanel p_west;
	JPanel p_copy;
	JPanel p_canvas;
	JPanel p_container;
	
	JTextField t_ori;
	JTextField t_dest;
	
	JButton bt_ori;
	JButton bt_dest;
	JButton bt_copy;

	/*
	 * ��Ʈ�� ����
	 * ��Ʈ���� ������ ���ؿ� ���� �з��� ����
	 * 1)������ ���� 
	 * 	 �Է�(Input) : �������� ���α׷����� �����Ͱ� ���ö�
	 * 	 ���(Output) : �������� ���α׷����� �����Ͱ� ������
	 * 	 IO
	 * 2)������ ó�� ���
	 *   - ����Ʈ��� ��Ʈ�� : 1byte�� ó���ϰ� ������
	 *   - ���ڱ�� ��Ʈ�� : 2byte�� ��� ���� �� �� �ִ� �ɷ�
	 *   - ���۱�� ��Ʈ�� : ���پ� ��,����� ó��
	 * */
	JFileChooser chooser;
	//���ڱ���� ������ ���� ������ ����Ʈ ��� ��Ʈ���� �̿�����
	FileInputStream fis;
	FileOutputStream fos;
	
	Border border;
	Toolkit kit = Toolkit.getDefaultToolkit(); //static �޼���!!
	Image detailImg; //ū �̹���
	String destName; //��� ������ ���纻�� ���
	public StreamGallery() {
		p_west = new JPanel();
		p_copy= new JPanel();
		
		//���뼺�� ��������
		p_canvas = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(detailImg, 0, 0, 400, 300,this);
			}
		};
		
		p_container = new JPanel();
		
		t_ori = new JTextField(25);
		t_dest = new JTextField(25);
		
		bt_ori = new JButton("����");
		bt_dest= new JButton("����");
		bt_copy = new JButton("����");
		
		chooser = new JFileChooser("C:/images");
		
		//��������
		border = BorderFactory.createLineBorder(Color.RED,5,false);
		
		
		//��Ÿ������
		p_west.setPreferredSize(new Dimension(100,500));
		p_west.setBackground(Color.YELLOW);
		
		p_copy.setPreferredSize(new Dimension(400,150));
		p_copy.setBackground(Color.GREEN);
		
		p_canvas.setPreferredSize(new Dimension(400,300));
		p_canvas.setBackground(Color.WHITE);
		
		p_container.setBackground(Color.WHITE);
		
		//ī�ǿ����� ����
		p_copy.add(t_ori);
		p_copy.add(bt_ori);

		p_copy.add(t_dest);
		p_copy.add(bt_dest);
		p_copy.add(bt_copy);
		
		//������ ���ʿ� �гκ���
		add(p_west, BorderLayout.WEST);
		
		//�����̳� �гο� ī���г�, ���̹��� �г� ����
		p_container.add(p_copy);
		p_container.add(p_canvas);
		
		//�������� ���Ϳ� �����̳� p_container ����
		add(p_container);
		
		
		setSize(600,550);
		setVisible(true);
		setLocationRelativeTo(null); //��ũ���� ����� ������ js�� ���� ���� ����
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeStream();
			}
		});
		
		//��ư��� ������ ����!!
		bt_ori.addActionListener(this);
		bt_dest.addActionListener(this);
		bt_copy.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		/*�ڹٿ����� Ŭ���� Action�̶�� �ϰ�, Action�� ��� �̺�Ʈ ������
		 * JVM�� ���� AActionEvent ��ü�� ���޵ȴ�*/
		Object obj=e.getSource(); //�̺�Ʈ ����Ų ������Ʈ����ȯ!!
		JButton btn = (JButton)obj;
		
		if(btn.equals(bt_ori)) {
			System.out.println("���ⴭ����?");
			open();
		}else if(btn.equals(bt_dest)) {
			System.out.println("���� ������?");
			save();
		}else if(btn.equals(bt_copy)) {
			System.out.println("���� ������?");
			copy();
		}
	}
	public void open() {
		int result = chooser.showOpenDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			//����ڰ� ������ ������ ������!!
			File file = chooser.getSelectedFile();
			
			//��Ʈ�� ����, ��ε� �ؽ�Ʈ�ʵ忡 ���!!
			String ori_path = file.getAbsolutePath();
			t_ori.setText(ori_path); //��� ���!!
			
			//��Ʈ�� ����
			try {
				fis = new FileInputStream(file);
				} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public void save() {
		int result = chooser.showSaveDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			//��½�Ʈ�� ����!!
			try {
				fos = new FileOutputStream(file);
				t_dest.setText(file.getAbsolutePath());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void copy() {
		byte[] buff = new byte[1024]; // 1kbyte
		//������ �����͸� ���̸�����!!
		int data=-1;
		
		try {
			while(true) {
				//data = fis.read(); �о���� �����Ͱ� 1�˰��̱⶧���� data������ ����
				
				//������ �Ʒ��� ���� �迭�� �۸����� �о���� �����Ͱ� data�� ������ �ƴ� buff�迭�� ä������
				//�׷� data������ �뵵��? ������ ���� �����ߴ��� üũ�� �ϸ�ȴ�
				data=fis.read(buff); //�ѹ��� read()�� 1024���� ����Ʈ�� �۸Դ´�!!
				if(data==-1) break;
				//�۸����� �ٽ� ������
				fos.write(buff);
			}
			JOptionPane.showMessageDialog(this, "����Ϸ�");
			
			//���� �������� �����Ͽ� ���ʿ� �߰�!!
			
			String destName = t_dest.getText(); //��� ������ ���ϰ��
			createThumb(destName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeStream();
		}
	}
	//�����ִ� ��Ʈ���� �ݴ� �޼���
	public void closeStream() {
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(fos!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void createThumb(String destName) {
		this.destName=destName;
		//������ �����Ͽ� �󺧿� �ֱ�
		ImageIcon icon = new ImageIcon(destName);
		JLabel thumb = new JLabel(resizeIcon(icon, 75, 65));
		thumb.setPreferredSize(new Dimension(80,70));
		thumb.setBorder(border);
		
		//���ʿ� ����!!
		p_west.add(thumb); //�гο� ���� ����!! updateUI();
		p_west.updateUI();
		
		//repaint() �޼���� �׷���ó���� �׸��� �׷����� �ٽ� �׷��޶�� ��û
		//paint �޼���� �׷����۾� �������� ���!!
		//updateUI() �޼���� ������Ʈ ���ſ�û
		thumb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showDetail();
			}
		});
	}
	
	//������ �������� ���� ���ϴ� ũ��� ���� �� ��ȯ���ִ� �޼���
	//��ȯ��Ű�⸦ ���ϴ� �������� �ŰԺ����� �Ѱ��ָ� �ȴ�!!
	public ImageIcon resizeIcon(ImageIcon icon,int width, int height) {
		Image scaledImage =  icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}
	
	//���̹��� ������ ó���ϱ�
	public void showDetail() {
		//������ ����ϰ� ���� ��ο� �ִ� �̹��� ���!!
		//p_canvas�� �׸� �̹����� �����Ͽ�, �ٽ� �׸��� �Ѵ�!!
		detailImg = kit.getImage(destName);
		p_canvas.repaint();
	}
	public static void main(String[] args) {
		new StreamGallery();
	}
}
