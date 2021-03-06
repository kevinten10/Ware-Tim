package com.ten.cim.server.kit;

import com.ten.cim.server.config.AppConfiguration;
import com.ten.cim.server.util.SpringBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Zookeeper客户端注册
 */
public class RegistryZK implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(RegistryZK.class);

    /**
     * ZK工具
     */
    private ZKit zKit;

    private AppConfiguration appConfiguration;

    private String ip;
    private int cimServerPort;
    private int httpPort;

    public RegistryZK(String ip, int cimServerPort, int httpPort) {
        this.ip = ip;
        this.cimServerPort = cimServerPort;
        this.httpPort = httpPort;
        zKit = SpringBeanFactory.getBean(ZKit.class);
        appConfiguration = SpringBeanFactory.getBean(AppConfiguration.class);
    }

    @Override
    public void run() {
        // 创建父节点
        zKit.createRootNode();

        // 是否要将自己注册到 ZK
        if (appConfiguration.isZkSwitch()) {
            String path = appConfiguration.getZkRoot() + "/ip-" + ip + ":" + cimServerPort + ":" + httpPort;
            zKit.createNode(path);
            logger.info("注册 zookeeper 成功，msg=[{}]", path);
        }
    }

}
