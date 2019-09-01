package com.github.binarylei.jdk.jmx;

/**
 * -XX:+StartAttachListener
 *
 * @author leigang
 * @version 2019-04-30
 * @since 2.0.0
 */
public class AbstractJmxCommand {
    private static final String CONNECTOR_ADDRESS = "com.sun.management.jmxremote.localConnectorAddress";

    public static String getJVM() {
        return System.getProperty("java.vm.specification.vendor");
    }

    public static boolean isSunJVM() {
        // need to check for Oracle as that is the name for Java7 onwards.
        return getJVM().equals("Sun Microsystems Inc.") || getJVM().startsWith("Oracle");
    }

    /*public static void main(String[] args) {
        if (isSunJVM()) {
            try {
                String javaHome = System.getProperty("java.home");
                String tools = javaHome + File.separator + ".." + File.separator + "lib" + File.separator + "tools.jar";
                URLClassLoader loader = new URLClassLoader(new URL[]{new File(tools).toURI().toURL()});

                Class virtualMachine = Class.forName("com.sun.tools.attach.VirtualMachine", true, loader);
                Class virtualMachineDescriptor = Class.forName(
                        "com.sun.tools.attach.VirtualMachineDescriptor", true,
                        loader);

                Method getVMList = virtualMachine.getMethod("list", (Class[]) null);
                Method attachToVM = virtualMachine.getMethod("attach", String.class);
                Method getAgentProperties = virtualMachine.getMethod("getAgentProperties", (Class[]) null);
                Method getVMId = virtualMachineDescriptor.getMethod("id", (Class[]) null);

                List allVMs = (List) getVMList.invoke(null, (Object[]) null);

                for (Object vmInstance : allVMs) {
                    String id = (String) getVMId.invoke(vmInstance, (Object[]) null);

                    Object vm = null;
                    try {
                        vm = attachToVM.invoke(null, id);
                    } catch (Exception e) {
                        continue;
                    }

                    Properties agentProperties = (Properties) getAgentProperties.invoke(vm, (Object[]) null);
                    String connectorAddress = agentProperties.getProperty(CONNECTOR_ADDRESS);

                    if (connectorAddress != null) {
                        System.out.println(connectorAddress);
                    } else {
                        break;
                    }
                }

                // 上面的尝试都不成功，则尝试让agent加载management-agent.jar
                Method getSystemProperties = virtualMachine.getMethod("getSystemProperties", (Class[]) null);
                Method loadAgent = virtualMachine.getMethod("loadAgent", String.class, String.class);
                Method detach = virtualMachine.getMethod("detach", (Class[]) null);
                for (Object vmInstance : allVMs) {
                    String id = (String) getVMId.invoke(vmInstance, (Object[]) null);

                    Object vm = null;
                    try {
                        vm = attachToVM.invoke(null, id);
                    } catch (Exception e) {
                        continue;
                    }

                    Properties systemProperties = (Properties) getSystemProperties.invoke(vm, (Object[]) null);
                    String home = systemProperties.getProperty("java.home");

                    // Normally in ${java.home}/jre/lib/management-agent.jar
                    // but might
                    // be in ${java.home}/lib in build environments.

                    String agent = home + File.separator + "jre" + File.separator + "lib" + File.separator
                            + "management-agent.jar";
                    File f = new File(agent);
                    if (!f.exists()) {
                        agent = home + File.separator + "lib" + File.separator + "management-agent.jar";
                        f = new File(agent);
                        if (!f.exists()) {
                            throw new IOException("Management agent not found");
                        }
                    }

                    agent = f.getCanonicalPath();

                    loadAgent.invoke(vm, agent, "com.sun.management.jmxremote");

                    Properties agentProperties = (Properties) getAgentProperties.invoke(vm, (Object[]) null);
                    String connectorAddress = agentProperties.getProperty(CONNECTOR_ADDRESS);

                    // detach 这个vm
                    detach.invoke(vm, (Object[]) null);

                    if (connectorAddress != null) {
                        System.out.println(id + " : " + connectorAddress);
                        JMXServiceURL url = new JMXServiceURL(connectorAddress);
                        JMXConnector connector = JMXConnectorFactory.connect(url);

                        MBeanServerConnection mbeanConn = connector.getMBeanServerConnection();
                        Set<ObjectName> beanSet = mbeanConn.queryNames(null, null);
                        for (ObjectName objectName : beanSet) {
                            System.out.println(objectName.toString());
                        }
                    }
                }
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
        }
    }*/
}
