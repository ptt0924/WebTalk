package com.application.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @Author chenLiang
 * @Date 2022/6/10 21:08
 */
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response){
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        if(httpSession!=null){
            int account = Integer.parseInt((String)httpSession.getAttribute("account"));
            System.out.println("account:"+account);
            //将该httpsession对象存入配置对象中
            sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
        }else{
            System.out.println("空session");
        }
    }
}
