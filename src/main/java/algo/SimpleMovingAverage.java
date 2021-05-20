package algo;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import source.LocalOrderBook;

public class SimpleMovingAverage {
    private int period;
    private DescriptiveStatistics descriptiveStatistics;

    public SimpleMovingAverage(int period) {
        this.period = period;
        this.descriptiveStatistics = new DescriptiveStatistics(period);
    }

    public void addValue(LocalOrderBook orderBook) {
        descriptiveStatistics.addValue(Math.weightedAverage(orderBook));
    }

    public double getMovingAverage() {
        if (descriptiveStatistics.getN() < period) {
            return -1;
        } else {
            return descriptiveStatistics.getMean();
        }
    }
}
