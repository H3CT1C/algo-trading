package algo;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class SimpleMovingAverage {
    private int period;
    private DescriptiveStatistics descriptiveStatistics;

    public SimpleMovingAverage(int period) {
        this.period = period;
        this.descriptiveStatistics = new DescriptiveStatistics(period);
    }

    public void addValue(double value) {
        descriptiveStatistics.addValue(value);
    }

    public double getMovingAverage() {
        if (descriptiveStatistics.getN() < period) {
            return -1;
        } else {
            return descriptiveStatistics.getMean();
        }
    }
}
