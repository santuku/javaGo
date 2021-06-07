package ProducerAndCustomer;
//测试：生产者消费者模型，利用缓冲区解决：管程法

/**需要四个类，分别表示生产者、消费者、产品和缓冲区
 * 缓冲区中需要一个容器，用数组实现，数组大小为10；实现生产者放入产品的push()方法和消费者消费产品的pop()方法
 * 对于生产者：没有生产产品之前，要通知消费者等待。生产了产品之后，需要马上通知消费者消费
 * 对于消费者：在消费之后，要通知生产者已经结束消费，需要生产新的产品以供消费
 */
public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Producer(container).start();
        new Customer(container).start();
    }
}
//生产者
class Producer extends Thread{
    SynContainer container = new SynContainer();

    public Producer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            container.push(new Chicken(i));
            System.out.println("生产了" + i + "只鸡");
        }
    }
}

//消费者
class Customer extends Thread{
    SynContainer container;

    public Customer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了"+container.pop().id+"只鸡");
        }
    }
}

class Chicken{
    int id;//编号

    public Chicken(int id) {
        this.id = id;
    }
}

//缓冲区
class SynContainer{

    //容器大小
    Chicken[] chickens = new Chicken[10];
    int count = 0;//容器计数器

    //生产者放入产品
    public synchronized void push(Chicken chicken){
        //如果容器满了，就需要等待消费者消费
        if(count == chickens.length){
            //通知消费者消费，生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果容器没有满，就丢入产品
        chickens[count] = chicken;
        count++;
        //通知消费者消费
        this.notifyAll();
    }

    public synchronized Chicken pop(){
        if(count == 0){
            //等待生产者生产，消费者等待（等待生产者通知，就可以解除等待）
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //有东西，消费者消费
        count --;
        Chicken chicken = chickens[count];
        //消费者消费完了，就通知生产者生产
        this.notifyAll();
        return chicken;
    }
}
