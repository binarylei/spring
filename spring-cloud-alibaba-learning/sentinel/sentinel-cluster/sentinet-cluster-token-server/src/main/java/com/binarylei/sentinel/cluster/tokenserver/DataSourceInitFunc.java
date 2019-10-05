package com.binarylei.sentinel.cluster.tokenserver;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * 从 Nacos获取限流规则
 *
 * @author leigang
 * @version 2019-08-17
 * @since 2.0.0
 */
public class DataSourceInitFunc implements InitFunc {

    private final String remoteAddress = "";
    private final String groupId = "sentinel_group";
    private final String flow_postfix = "-flow-rules";

    @Override
    public void init() throws Exception {
        ClusterFlowRuleManager.setPropertySupplier(namespace -> {
            ReadableDataSource<String, List<FlowRule>> rds =
                    new NacosDataSource<>(remoteAddress, groupId, namespace + flow_postfix,
                            source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                            }));
            return rds.getProperty();
        });
    }
}
