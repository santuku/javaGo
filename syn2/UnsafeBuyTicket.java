package syn;

/**
 * 排队买票时候，并没有排队买，所有线程都将对象的数据拷贝到自己的内存中进行操作，就可能使最后总的票数呈现负数
 * 1.定义一个买票的多线程类，实现Runnable接口，重写run()里面的买票
 * 2.定义票数的变量ticketNums = 10
 * 3.写一个买票的方法buy(),首先判断有没有票，如果没有票就return,否则就买票，ticketNums--
 * 4.设置标志位flag停止线程，外部有一个while循环，调用方法buy()买票
 * 5.主方法里new一下拿到对象的引用b,三个线程操作同一个对象,开启线程，就可以买票
 */

public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket b = new BuyTicket();
        new Thread(b,"小明").start();
        new Thread(b,"老师").start();
        new Thread(b,"黄牛党").start();
    }
}

class BuyTicket implements Runnable{

    private int ticketNums = 10;
    boolean flag = true;//设置标志位停止线程
    @Override
    public void run() {
        while(flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private  void  buy() throws InterruptedException {
        if(ticketNums <= 0){
            flag = false;
            return;
        }
        //模拟延时，放大的作用
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+"拿到"+ ticketNums--);
    }
}
