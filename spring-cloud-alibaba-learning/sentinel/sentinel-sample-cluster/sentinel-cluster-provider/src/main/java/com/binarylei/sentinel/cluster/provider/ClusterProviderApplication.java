package com.binarylei.sentinel.cluster.provider;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -Dcsp.sentinel.dashboard.server=192.168.139.101:8081 -Dproject.name=sentinel-sample1
 */
@SpringBootApplication
public class ClusterProviderApplication {

    public static void main(String[] args) {
        ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT);
        SpringApplication.run(ClusterProviderApplication.class, args);
    }

}
