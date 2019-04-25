package com.ssm.gbq.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.alibaba.fastjson.JSONObject;

public class loginFilter extends AccessControlFilter {

    public static final String ACCESS_TOKEN = "Access-Token";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        if (isLoginRequest(request, response)) {
            return Boolean.TRUE;
        }
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login_status", "300");
        jsonObject.put("message", "无效的Token.");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toJSONString());
        return Boolean.FALSE;
    }
}
