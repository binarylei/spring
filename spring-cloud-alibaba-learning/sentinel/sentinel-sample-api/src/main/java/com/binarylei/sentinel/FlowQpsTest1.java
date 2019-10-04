package com.binarylei.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.statistic.base.WindowWrap;
import com.alibaba.csp.sentinel.slots.statistic.data.MetricBucket;
import com.alibaba.csp.sentinel.slots.statistic.metric.occupy.FutureBucketLeapArray;
import com.alibaba.csp.sentinel.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * -Dcsp.sentinel.dashboard.server=192.168.139.101:8081 -Dproject.name=sentinel-sample1
 *
 * @author binarylei
 * @version 2019-09-14
 * @since 2.0.0
 */
public class FlowQpsTest1 {

    private static final int windowLengthInMs = 200;
    private static final int intervalInSec = 2;
    private static final int intervalInMs = intervalInSec * 1000;
    private static final int sampleCount = intervalInMs / windowLengthInMs;

    public static void main(String[] args) {
        FutureBucketLeapArray array = new FutureBucketLeapArray(sampleCount, intervalInMs);

        long currentTime = TimeUtil.currentTimeMillis();
        WindowWrap<MetricBucket> currentWindow = array.currentWindow(currentTime);
        System.out.println("=========");
        System.out.println("currentWindow: " + currentWindow.windowStart());

        currentWindow.value().addPass(1);
//            List<WindowWrap<MetricBucket>> list = array.list();
//            list.forEach(w-> System.out.println(w.windowStart()));
        assertEquals(array.values(currentTime).size(), 0);
    }

}
