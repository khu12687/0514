package thread;

public class UseThread {
	public static void main(String[] args) {
		//�����带 �����Ͽ� ���۽��Ѻ���
		MyThread t = new MyThread(); //��������� �����ڰ� �����̴�!! ���̻� ������������ JVM�� �ñ��
		//t.run(); //����ӽſ��� �ñ�� ���� �ʴ´�.. �̷��� �ϴ°� �ƴϴ�
		t.start(); // jvm�� �ñ��.. �̷��� �ϴ°Ŵ�
	}
}
