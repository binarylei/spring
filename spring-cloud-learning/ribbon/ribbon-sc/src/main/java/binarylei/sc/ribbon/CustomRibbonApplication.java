package binarylei.sc.ribbon;

import org.springframework.boot.SpringApplication;

/**
 * 自定义负载均衡算法 RibbonConfig，默认为轮询方法，修改为随机算法
 *
 * @author leigang
 * @version 2019-03-20
 */
//@SpringBootApplication
// 注意 configuration 对应的配置文件不能被 ComponentScan 扫描到
//@RibbonClient(name = "user-provider", configuration = RibbonConfig.class)
public class CustomRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomRibbonApplication.class);
    }
}
