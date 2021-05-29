package state;
//建议线程正常停止，利用次数，不建议死循环
//建议设置标志位
//不建议使用destory或者stop
public class TestStop  implements Runnable{

    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while(flag) {
            System.out.println("run……Thread" + i++);//只是不停刷新
        }
    }
//设置一个公开的方法停止线程，转换标志位
    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main"+ i);
            if(i == 900){
                //调用公开方法stop切换标志位，使线程停止
                testStop.stop();
                System.out.println("线程停止了");
            }
        }

    }
}
