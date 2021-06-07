package syn;

/**银行取钱
 * 1.取钱需要定义一个Account类，name属性表示账户的用途，money属性表示账户里有多少钱
 * 2.定义多线程类Drawing，除了账户属性，还有要取多少钱drawingMoney,手里的钱nowMoney两个属性，定义构造方法初始化（name属性继承自Account类）
 * 3.重写run()方法:
 * 如果账户里的钱-你要取得钱<0，就return并且打印相关信息;
 * 取钱的具体操作：账户里的钱 account.money = account.money - drawingMoney
 *             手里的钱 nowMoney = nowMoney+ drawingMoney
 *  再打印账户余额和手里的钱
 *  4.定义main方法实现类，开启两个线程，即两个人取同一个账户里钱
 *  5.写一个延迟Thread.sleep（）,放大问题的发生性
 *  6.线程不安全就体现在两个人都去取钱，账户有100万，两个人看到100万都是可取的，但是一操作就会有负数的情况，造成了不安全的取钱
 */

/**定义draw（）方法基于面向对象里流行的设计方式，DDD
 * */
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account("结婚基金",100);
        Drawing you = new Drawing(account,50,"你");
        Drawing boyFriend = new Drawing(account,100,"boyFriend");
        you.start();
        boyFriend.start();
    }
}

class Account{
    String name;
    int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }
}

class Drawing extends Thread{
    //不涉及多个线程操作同一个对象，所以继承Thread类
    Account account;
    int drawingMoney;
    int nowMoney;

    public Drawing(Account account,int drawingMoney,String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }
    @Override
    public void run() {
        //判断有没有钱
        if(account.money - drawingMoney < 0){
            System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        account.money = account.money- drawingMoney;
        nowMoney = nowMoney + drawingMoney;

        System.out.println(account.name + "余额为"+account.money);
        //Thread.currentThread().getName() = this.getName()
        System.out.println(this.getName() + "手里的钱"+ nowMoney);
    }
}
