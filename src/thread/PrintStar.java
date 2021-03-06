package thread;

public class PrintStar {
	/*
	 *하나의 프로세스 안에서 thread1,thread2 는 서로 상관없이 독립되어 수행된다!!
	 *즉 누가먼저 수행되고, 누구를 더 많이 수행할지는 시스템인 JVM이 결정하는 것이다!! 
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
					System.out.print("★");
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
					System.out.print("☆");
				}
			}
		};
		
		thread.start();
		thread2.start();
	}
}
