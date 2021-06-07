package ProducerAndCustomer;

/**
 * 使用一个标志位flag,通过flag判断执行消费还是生产
 * 演员只用演，观众只需要看，中间媒介是节目，（这里认为节目是录播的，所以演员表演时，观众等待T；观众观看，演员等待F）
 */
//测试生产者消费者问题2：信号灯法，通过标志位解决
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

class Player extends Thread{
    TV tv;
    public Player(TV tv){
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i % 2 == 0){
                this.tv.play("中国诗词大会正在播放中");
            }else{
                this.tv.play("快手：看见世界，也看见你");
            }
        }
    }
}

class Watcher extends Thread{
    TV tv;
    public Watcher(TV tv){
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

class TV{
    String chant;//节目
    boolean flag = true;

    //表演
    public synchronized void play(String chant){
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了"+chant);
        //通知观众观看
        this.notifyAll();//通知唤醒
        this.chant = chant;
        this.flag = !this.flag;
    }

    //观看
    public synchronized  void watch(){
        if(flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看了"+chant);
        //通知演员表演
        this.notifyAll();
        this.flag = !this.flag;
    }
}
