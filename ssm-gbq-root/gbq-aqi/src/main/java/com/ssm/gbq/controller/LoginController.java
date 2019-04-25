package com.ssm.gbq.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.gbq.model.Manager;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.ValidateCode;
/**
 * 登录Controller
 * @author 阿前
 * 2019年1月4日09:48:21
 */
@Controller
@RequestMapping("")
public class LoginController {
    @ResponseBody
    @RequestMapping(value = "/login.zhtml", method = RequestMethod.POST)
//    public HashMap<String, Object> loginVeri(@Valid LoginForm loginForm, BindingResult bindingResult,
    public HashMap<String, Object> loginVeri(Manager loginForm, BindingResult bindingResult,HttpServletRequest request,
            HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); 
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            System.out.println(session.getId());
            if (subject.isAuthenticated()) {
                result.put("token", session.getId());
                return result;
            }
            if (bindingResult.hasErrors()) {
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                for (ObjectError error : allErrors) {
                    result.put("error", error.getDefaultMessage());
                }
                return result;
            }
            // managerService.getManagerByName(loginForm.getUsername());
            // 身份验证
            subject.login(new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassword()));
            result.put("token", session.getId());
        } catch (AuthenticationException e) {
            // 身份验证失败
            result.put("error", "用户名或密码错误 ！");
            return result;
        } catch (BusinessException e) {
            result.put("error", e.getErrorCode());
            return result;
        }
        return result;
    }
    
    /** 
     * 响应验证码页面 
     * @return 
     */  
    @ResponseBody
    @RequestMapping(value="/validateCode", method = RequestMethod.GET)  
    public String validateCode(HttpServletRequest request,HttpServletResponse response) throws Exception{  
        // 设置响应的类型格式为图片格式  
        response.setContentType("image/jpeg");  
        //禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  

        HttpSession session = request.getSession();  

        ValidateCode vCode = new ValidateCode(120,40,5,100);  
        session.setAttribute("code", vCode.getCode());  
        vCode.write(response.getOutputStream());  
        return null;  
    } 
}
