package com.ten.cim.client;

import com.ten.cim.client.scanner.Scan;
import com.ten.cim.client.service.impl.ClientInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 客户端
 */
@SpringBootApplication
public class CIMClientApplication implements CommandLineRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(CIMClientApplication.class);

    @Autowired
    private ClientInfo clientInfo;

    public static void main(String[] args) {
        SpringApplication.run(CIMClientApplication.class, args);
        LOGGER.info("启动 Client 服务成功");
    }

    @Override
    public void run(String... args) throws Exception {
        // 启动输入扫描
        Scan scan = new Scan();
        Thread thread = new Thread(scan);
        thread.setName("scan-thread");
        thread.start();
        clientInfo.saveStartDate();
    }

}