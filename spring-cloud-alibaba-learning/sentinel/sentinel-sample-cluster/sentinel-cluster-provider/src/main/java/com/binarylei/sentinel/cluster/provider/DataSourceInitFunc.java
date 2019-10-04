package com.binarylei.sentinel.cluster.provider;

import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * @author binarylei
 * @version 2019-09-17
 * @since 2.0.0
 */
public class DataSourceInitFunc implements InitFunc {


    private static final String host = "localhost";
    private static final int port = 9999;
    private static final int timeout = 200000;

    private static final String remoteAddress = "192.168.139.101:8848";
    private static final String groupId = "SENTINEL-GROUP";
    private static final String FLOW_POSTFIX = "-flow-rules";
    private static final String namespace = "-flow-rules";

    @Override
    public void init() throws Exception {
        loadClusterClientConfig();
        registerClusterFlowRuleConfig();
    }

    public void loadClusterClientConfig() throws Exception {
        ClusterClientAssignConfig assignConfig = new ClusterClientAssignConfig();
        assignConfig.setServerHost(host);
        assignConfig.setServerPort(port);
        ClusterClientConfigManager.applyNewAssignConfig(assignConfig);

        ClusterClientConfig clientConfig = new ClusterClientConfig();
        clientConfig.setRequestTimeout(timeout);
        ClusterClientConfigManager.applyNewConfig(clientConfig);
    }

    public void registerClusterFlowRuleConfig() throws Exception {

        ReadableDataSource<String, List<FlowRule>> rds =
                new NacosDataSource<>(remoteAddress,
                        groupId, namespace + FLOW_POSTFIX,
                        source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                        })
                );
        FlowRuleManager.register2Property(rds.getProperty());

    }
}
