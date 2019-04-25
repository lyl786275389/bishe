package com.ssm.gbq.config;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import gbq.ssm.utils.BusinessException;

public class CustomDefaultWebSessionManager extends DefaultWebSessionManager {

    public static final String ACCESS_TOKEN = "Access-Token";

    /**
     * 获取session id 前后端分离将从请求头中获取jsesssionid
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String reUrl = httpServletRequest.getRequestURI().toString();
            if ("/login.zhtml".equals(reUrl)) {
                
            }else if ("/regist.zhtml".equals(reUrl)){
                
            } 
            else{
                String sessionId = httpServletRequest.getHeader(ACCESS_TOKEN);
                if (sessionId != null) {
                    try {
//                        Session session = retrieveSessionFromDataSource(sessionId);
                        System.out.println("Access-Token:" + sessionId);
                        return sessionId;
                    } catch (BusinessException e) {
                        throw new BusinessException("获取token失败！", e.getMessage());
                    }
                }
            }

        }
        // 若header获取不到token则尝试从cookie中获取
        return super.getSessionId(request, response);
    }
}
