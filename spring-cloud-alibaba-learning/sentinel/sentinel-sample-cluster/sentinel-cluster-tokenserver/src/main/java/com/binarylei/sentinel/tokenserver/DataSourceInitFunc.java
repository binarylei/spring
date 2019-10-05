package com.binarylei.sentinel.tokenserver;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * @author binarylei
 * @version 2019-09-17
 * @since 2.0.0
 */
public class DataSourceInitFunc implements InitFunc {

    private static final String remoteAddress = "192.168.139.101:8848";
    private static final String groupId = "SENTINEL-GROUP";
    private static final String FLOW_POSTFIX = "-flow-rules";

    @Override
    public void init() throws Exception {
        ClusterFlowRuleManager.setPropertySupplier(namespace -> {
            ReadableDataSource<String, List<FlowRule>> rds =
                    new NacosDataSource<>(remoteAddress,
                            groupId, namespace + FLOW_POSTFIX,
                            source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                            })
                    );
            return rds.getProperty();
        });
    }
}
