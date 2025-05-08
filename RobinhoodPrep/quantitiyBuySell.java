package RobinhoodPrep;

import java.util.*;

/*
 * 
 * Given a stream of incoming "buy" and "sell" orders (as lists of limit price, quantity, and side, like ["155", "3", "buy"]), determine the total quantity (or number of "shares") executed.

A "buy" order can be executed if there is a corresponding "sell" order with a price that is less than or equal to the price of the "buy" order.
Similarly, a "sell" order can be executed if there is a corresponding "buy" order with a price that is greater than or equal to the price of the "sell" order.
It is possible that an order does not execute immediately if it isn't paired to a counterparty. In that case, you should keep track of that order and execute it at a later time when a pairing order is found.
You should ensure that orders are filled immediately at the best possible price. That is, an order should be executed when it is processed, if possible. Further, "buy" orders should execute at the lowest possible price and "sell" orders at the highest possible price at the time the order is handled.

Note that orders can be partially executed.

--- Sample Input ---

orders = [
['150', '5', 'buy'], # Order A
['190', '1', 'sell'], # Order B
['200', '1', 'sell'], # Order C
['100', '9', 'buy'], # Order D
['140', '8', 'sell'], # Order E
['210', '4', 'buy'], # Order F
]

Sample Output
9

 */

public class quantitiyBuySell {
    private static int quantitiyBuySell(String[][] orders){
        PriorityQueue<int[]> sells = new PriorityQueue<>((a,b)->a[0]-b[0]);
        PriorityQueue<int[]> buys = new PriorityQueue<>((a,b)->b[0]-a[0]);
        int shares = 0;

        for(String[] order : orders){
            int price = Integer.parseInt(order[0]);
            int amount = Integer.parseInt(order[1]);
            System.out.println(order[2]);
            System.out.println(order[1]);
            System.out.println("buys: "+buys);
            System.out.println("sells: "+sells);

            if (order[2].equals("buy")){


                while(amount > 0 && !sells.isEmpty() && sells.peek()[0] <= price){
                    int[] sellOrder = sells.peek();
                    int processed = Math.min(amount, sellOrder[1]);

                    shares += processed;
                    amount -= processed;
                    sellOrder[1] -= processed;

                    if (sellOrder[1] == 0) sells.poll(); 
                }

                if (amount > 0) buys.add(new int[]{price, amount});
            }
            else {

                System.out.println("made it here");
                System.out.println( amount > 0 && !buys.isEmpty() && buys.peek()[0] >= price);
                while(amount > 0 && !buys.isEmpty() && buys.peek()[0] >= price){

                    System.out.println("in here");
                    int[] buyOrder = buys.peek();
                    int processed = Math.min(amount, buyOrder[1]);

                    shares += processed;
                    amount -= processed;
                    buyOrder[1] -= processed;

                    if (buyOrder[1] == 0) sells.poll(); 
                }

                System.out.println("and here");
                if (amount > 0) sells.add(new int[]{price, amount});
                
            }
        }
        System.out.println("res: "+shares);
        return shares;
    }

    public static void main(String[] args){
        int res = quantitiyBuySell(new String[][]{
            {"150","5","buy"},
            {"190","1","sell"},
            {"200","1","sell"},
            {"100","9","buy"},
            {"140","8","sell"},
            {"210","4","buy"}
        });

        System.out.println(res);
    }
}
