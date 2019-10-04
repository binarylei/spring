package com.binarylei.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * -Dcsp.sentinel.dashboard.server=192.168.139.101:8081 -Dproject.name=sentinel-sample1
 *
 * @author binarylei
 * @version 2019-09-14
 * @since 2.0.0
 */
public class FlowQpsTest2 {

    public static void main(String[] args) throws BlockException, IOException {

        Entry entry = null;
        try {
//                ContextUtil.enter("entrance1", "appA");
            entry = SphU.entry("HelloWorld_test");

            Entry entry2 = SphU.entry("HelloWorld_test2");
            entry2.exit();
        } catch (BlockException e1) {
            System.out.println("block! 被限流了");
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        System.in.read();
    }
}
