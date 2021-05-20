package algo;

import org.junit.Test;
import org.junit.Assert;

public class SimpleMovingAverageTest {
    @Test
    public void getMovingAverage_test_period1() {
        SimpleMovingAverage simpleMovingAverage = new SimpleMovingAverage(1);
        simpleMovingAverage.addValue(3.0);
        Assert.assertEquals(simpleMovingAverage.getMovingAverage(), 3.0, 0.001);
    }

    @Test
    public void getMovingAverage_test_period3() {
        SimpleMovingAverage simpleMovingAverage = new SimpleMovingAverage(3);
        simpleMovingAverage.addValue(1.0);
        Assert.assertEquals(simpleMovingAverage.getMovingAverage(), -1, 0.001);
        simpleMovingAverage.addValue(2.0);
        Assert.assertEquals(simpleMovingAverage.getMovingAverage(), -1, 0.001);
        simpleMovingAverage.addValue(3.0);
        Assert.assertEquals(simpleMovingAverage.getMovingAverage(), 2, 0.001);
        simpleMovingAverage.addValue(4.0);
        Assert.assertEquals(simpleMovingAverage.getMovingAverage(), 3, 0.001);
        simpleMovingAverage.addValue(5.0);
        Assert.assertEquals(simpleMovingAverage.getMovingAverage(), 4, 0.001);
    }
}
