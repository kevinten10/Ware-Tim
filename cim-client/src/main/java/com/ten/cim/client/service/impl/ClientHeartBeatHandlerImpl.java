package com.ten.cim.client.service.impl;

import com.ten.cim.client.client.CIMClient;
import com.ten.cim.common.kit.HeartBeatHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Function:
 *
 * @author ten
 * Date: 2019-01-20 17:16
 * @since JDK 1.8
 */
@Service
public class ClientHeartBeatHandlerImpl implements HeartBeatHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientHeartBeatHandlerImpl.class);

    @Autowired
    private CIMClient cimClient;


    @Override
    public void process(ChannelHandlerContext ctx) throws Exception {

        //重连
        cimClient.reconnect();

    }


}
