package array.slidingWindow;

public class BestTimeToBuySellStock {
    static int maxProfitSlidingWindow(int[] prices){
        int l = 0, r = 1, maxP = 0;
        while (r < prices.length) {
            if (prices[l] < prices[r])
                maxP = Math.max(maxP, prices[r] - prices[l]);
            else l = r;
            r++;
        }
        return maxP;
    }

    static int maxProfit(int[] prices){
        if (prices == null || prices.length < 2) return 0;
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {10,1,7,5,2};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfitSlidingWindow(prices));

    }
}
