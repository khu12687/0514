/*
 * �ڹٽ�ũ��Ʈ���� �ڵ�ȣ���� �����ϱ� ���� setInterval, setTimeout�� �����ϵ�,
 * �ڹٿ����� �ڵ�ȣ���� ������ �� �ִ�!!
 * */
package thread;

public class ThreadApp {
	//Process ���μ�����? �޸𸮿� �÷��� �������� ���α׷��̴�
	/*Thread��?
	 * �ϳ��� ���μ�������  "����!!"������ ����Ǵ� ���� �������!!
	 * */
	Thread thread;
	int count=0;
	public ThreadApp() {
		//�н��ϳ� ����!!
		thread = new Thread() {
			//�����尡 �����Ű�� ���࿵��
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //1000���� 1�ʱ��� ǥ��
					System.out.println(count++);
				}
			}
		};
		//������ �н��� �����带 ����!!
		thread.start();
	}
	public static void main(String[] args) {
		new ThreadApp();
	}
}

