package com.github.binarylei.jdk.jmx;

import org.junit.Test;

/**
 * @author leigang
 * @version 2019-04-30
 * @since 2.0.0
 */
public class JmxTest {

    @Test
    public void test1() {
        /*List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        for (VirtualMachineDescriptor desc : vms) {
            VirtualMachine vm;
            try {
                System.out.println("desc: " + desc);
                System.out.println("进程 id: " + desc.id());
                vm = VirtualMachine.attach(desc);
            } catch (Exception e) {
//                e.printStackTrace();
                continue;
            }
            JMXConnector connector = null;
            try {
                Properties props = vm.getAgentProperties();
                for (Map.Entry<Object, Object> entry : props.entrySet()) {
                    System.out.println(desc.id() + " " + entry.getKey() + "->" + entry.getValue());
                }

                String connectorAddress = props.getProperty(
                        "com.sun.management.jmxremote.localConnectorAddress");
                if (connectorAddress == null) {
                    System.out.println("connectorAddress  is  null");
                    continue;
                }
                System.out.println("conn:" + connectorAddress);
                //以下代码用于连接指定的jmx，本地或者远程
                JMXServiceURL url = new JMXServiceURL(connectorAddress);
                //JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/TestJMXServer");
                connector = JMXConnectorFactory.connect(url);

                MBeanServerConnection mbeanConn = connector.getMBeanServerConnection();
                Set<ObjectName> beanSet = mbeanConn.queryNames(null, null);
                // ...
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connector != null) connector.close();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
}
