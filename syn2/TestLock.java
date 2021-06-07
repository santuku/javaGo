package syn;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();

        new Thread(testLock2,"a").start();
        new Thread(testLock2,"b").start();
        new Thread(testLock2,"c").start();
    }
}

class TestLock2 implements  Runnable{
    int ticketNums = 10;
    //定义lock🔒
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while(true){
            try{
                lock.lock();//加锁
                if(ticketNums > 0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "拿到" +ticketNums--);
                }else{
                    break;
                }
            }finally {
                //解锁
                lock.unlock();
            }
        }
    }
}