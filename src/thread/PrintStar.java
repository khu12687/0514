package thread;

public class PrintStar {
	/*
	 *�ϳ��� ���μ��� �ȿ��� thread1,thread2 �� ���� ������� �����Ǿ� ����ȴ�!!
	 *�� �������� ����ǰ�, ������ �� ���� ���������� �ý����� JVM�� �����ϴ� ���̴�!! 
	 */
	public static void main(String[] args) {
		Thread thread =new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.print("��");
				}	
			}
		};
		
		Thread thread2 =new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.print("��");
				}
			}
		};
		
		thread.start();
		thread2.start();
	}
}
