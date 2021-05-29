package Demo01;

public class TestThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("我在看代码");
        }
    }

    public static void main(String[] args) {
        TestThread testThread1 = new TestThread();
        testThread1.start();
        for (int i = 0; i < 100 ; i++) {
            System.out.println("我在学习多线程");
        }
    }
}
