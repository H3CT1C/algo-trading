package algo;

import source.data.LocalOrderBook;

import java.math.BigDecimal;
import java.util.Map;

public class Math {
    public static double weightedAverage(LocalOrderBook orderBook) {
        double[] weightedAsk = weightedAsk(orderBook);
        double[] weightedBid = weightedBid(orderBook);
        double totalQuantity = weightedAsk[1] + weightedBid[1];
        double totalPrice = weightedAsk[0] * weightedAsk[1]
                + weightedBid[0] * weightedBid[1];
        return totalPrice / totalQuantity;
    }

    public static double[] weightedAsk(LocalOrderBook orderBook) {
        Map.Entry<BigDecimal, BigDecimal> bestAsk = orderBook.getBestAsk();
//        Map.Entry<BigDecimal, BigDecimal> secondAsk = orderBook.getSecondBestAsk();
//        Map.Entry<BigDecimal, BigDecimal> thirdAsk = orderBook.getThirdBestAsk();
//        double totalQuantity = bestAsk.getValue().doubleValue()
//                + secondAsk.getValue().doubleValue() + thirdAsk.getValue().doubleValue();
//        double totalAskPrice = bestAsk.getKey().doubleValue() * bestAsk.getValue().doubleValue()
//                + secondAsk.getKey().doubleValue() * secondAsk.getValue().doubleValue()
//                + thirdAsk.getKey().doubleValue() * thirdAsk.getValue().doubleValue();
//        return new double[]{totalAskPrice / totalQuantity, totalQuantity};
        return new double[]{bestAsk.getKey().doubleValue(), bestAsk.getValue().doubleValue()};
    }

    public static double[] weightedBid(LocalOrderBook orderBook) {
        Map.Entry<BigDecimal, BigDecimal> bestBid = orderBook.getBestBid();
//        Map.Entry<BigDecimal, BigDecimal> secondBid = orderBook.getSecondBestBid();
//        Map.Entry<BigDecimal, BigDecimal> thirdBid = orderBook.getThirdBestBid();
//        double totalQuantity = bestBid.getValue().doubleValue()
//                + secondBid.getValue().doubleValue() + thirdBid.getValue().doubleValue();
//        double totalBidPrice = bestBid.getKey().doubleValue() * bestBid.getValue().doubleValue()
//                + secondBid.getKey().doubleValue() * secondBid.getValue().doubleValue()
//                + thirdBid.getKey().doubleValue() * thirdBid.getValue().doubleValue();
//        return new double[]{totalBidPrice / totalQuantity, totalQuantity};
        return new double[]{bestBid.getKey().doubleValue(), bestBid.getValue().doubleValue()};
    }
}
