package thread;

public class UseThread {
	public static void main(String[] args) {
		//쓰레드를 생성하여 동작시켜보자
		MyThread t = new MyThread(); //여기까지가 개발자가 할일이다!! 더이상 관여하지말고 JVM에 맡긴다
		//t.run(); //가상머신에게 맡기게 되지 않는다.. 이렇게 하는거 아니다
		t.start(); // jvm에 맡긴다.. 이렇게 하는거다
	}
}
