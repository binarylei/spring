package com.binarylei.dubbosentinel.provider;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        initFlowRule();
        SpringApplication.run(ProviderApplication.class, args);
    }

    // 注意：限流规则配置在服务端
    private static void initFlowRule() {
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("com.binarylei.dubbosentinel.api.SentinelService:sayHello(java.lang.String)");
        flowRule.setCount(10);
        // 根据QPS进行限流
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 拒绝策略
        flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        flowRule.setLimitApp("sentinel-web");

        FlowRuleManager.loadRules(Arrays.asList(flowRule));
    }

}
