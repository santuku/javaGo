package poker;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {
    public static  void printPlayerHandCardList(List<Player> playerList){
        for (Player player : playerList) {
            System.out.printf("玩家%s手牌是：%s%n", player.name, player.cardList);
        }
    }

    public static void main(String[] args) {
        List<Player> playerList = new ArrayList<>();

        playerList.add(new Player("小a"));
        playerList.add(new Player("小b"));
        playerList.add(new Player("小c"));
        playerList.add(new Player("小d"));
        playerList.add(new Player("小e"));

        List<Card> cardList = new ArrayList<>();

        //初始化扑克牌
        initializeCards(cardList);
        System.out.println(cardList);

        //洗牌
        Collections.shuffle(cardList);
        System.out.println("抽牌前，牌组中的牌：");
        System.out.println(cardList);

        //发牌
        int n = 4;//每名玩家发几张牌
        for (int i = 0; i < n; i++) {
            for (Player player : playerList) {
                Card card = cardList.remove(0);

                player.cardList.add(card);
            }
        }
        System.out.println("现在牌组中剩余的牌：");
        System.out.println(cardList);

        System.out.println("交换牌之前：");
        printPlayerHandCardList(playerList);

        Card toFoundCard = new Card("♠", 1);
        Player 小a = playerList.get(0);//类和以该类作为元素的链表
        aMethod(小a,toFoundCard,playerList);
        System.out.print("小a的aMethod方法：");
        printPlayerHandCardList(playerList);

        Random random = new Random();
        //交换牌
        //每名玩家抽取其下家随机一张手牌
        for(int i = 0;i < playerList.size();i++){
            Player currentPlayer = playerList.get(i);
            Player nextPlayer = playerList.get(i != playerList.size() -1?i+1:0);
            //最后一个玩家取的是零号玩家的牌

            //要取牌的下标
            int toDrawIndex = random.nextInt(nextPlayer.cardList.size());
            //取牌
            Card drawCard = nextPlayer.cardList.remove(toDrawIndex);
            //放到当前玩家手中
            currentPlayer.cardList.add(drawCard);
        }

        //变牌，手中没有黑桃，就可以把第一张牌换成黑桃
        System.out.println("交换牌之后：");
        printPlayerHandCardList(playerList);

        //要找的牌
        for (Player player : playerList) {
            for (Card card : player.cardList){
                if (card.equals(toFoundCard)) {//也可以用别的方法实现
                    System.out.println(player.name + "获胜");
                    return;//这一句好重要，找到后就跳出main方法
                }
           }
        }
         System.out.println("没找到");
    }


    public static void aMethod(Player 小a, Card toFoundCard, List<Player> playerList) {
        if(!小a.cardList.contains(toFoundCard)) {
            Card card = 小a.cardList.get(0);
            小a.cardList.set(0, toFoundCard);

            for (int i = 1; i < playerList.size(); i++) {
                if (playerList.get(i).cardList.contains(toFoundCard)) {

                    int j = playerList.get(i).cardList.indexOf(toFoundCard);
                    playerList.get(i).cardList.set(j, card);
                }
            }
        }
    }



    private static void initializeCards(List<Card> cards) {
        for(String suit :new String[] {"♣","♠","♦","♥"}){
            for (int rank = 1; rank <= 5; rank++) {
                Card card = new Card(suit,rank);
                //把扑克牌放入牌组中
                cards.add(card);
            }
        }
    }
}

