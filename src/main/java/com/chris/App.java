package com.chris;

import org.apache.cassandra.db.ColumnFamilyStoreMBean;
import org.apache.cassandra.service.StorageServiceMBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    static String ColumnFamilyStatsString = "org.apache.cassandra.db:type=StorageService";

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new ClassPathResource("context.xml").getPath());
        int a = 5;

        String serviceURL = "service:jmx:rmi:///jndi/rmi://10.0.1.43:7199/jmxrmi";
        JMXServiceURL jmxUrl = new JMXServiceURL(serviceURL);
        Map<String, String[]> authenticationInfo = new HashMap<String, String[]>();
        authenticationInfo.put(JMXConnector.CREDENTIALS, new String[]{"cassandra","cassandra"});
        JMXConnector jmxCon = JMXConnectorFactory.connect(jmxUrl, authenticationInfo);
        try {
            MBeanServerConnection catalogServerConnection = jmxCon.getMBeanServerConnection();
            StorageServiceMBean cfstatMbean = locateMBean(new ObjectName(ColumnFamilyStatsString),
                    StorageServiceMBean.class, catalogServerConnection);
            int b = 5;
        } catch (Exception e) {
            if (jmxCon != null) {
                jmxCon.close();
            }
            e.printStackTrace();
        }

    }

    private static <T> T locateMBean(ObjectName name, Class<T> mBeanClass, MBeanServerConnection catalogServerConnection) {
        return JMX.newMBeanProxy(catalogServerConnection, name, mBeanClass);
    }
}
