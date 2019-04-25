package com.ssm.gbq.interceptor;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import gbq.ssm.utils.BusinessException;

/**
 * 切面
 * 
 * @ClassName: LogRecordAspect
 * @author xph
 * @date 2018年11月8日
 */
@Aspect
@Component
@Order(1)
public class LogRecordAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);

    @Pointcut("execution(* com.ssm.gbq.controller..*(..))")
    public void excudeService() {
    }

    @SuppressWarnings("unchecked")
    @Around(value = "excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURI().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info("接收, url: {}, method: {}, uri: {}, params: {}", url, method, uri,
                queryString == null ? "" : queryString);
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            Object object = pjp.proceed();
            if (object instanceof String || object instanceof ModelAndView) {
                return object;
            } else if (object instanceof HashMap) {
                HashMap<String, Object> data = (HashMap<String, Object>) object;
                result.put("result", data);
            } else if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                result.put("result", jsonObject);
            }
            result.put("success", true);
            result.put("status", 200);
        } catch (BusinessException exception) {
            result = new HashMap<String, Object>();
            result.put("success", false);
            result.put("errorMsg", exception.getErrorMessage());
            result.put("result", null);
            exception.printStackTrace();
/*            logger.error("error, message: {}, errorMessage: {}, exception: {}", exception.getMessage(),
                    exception.getErrorMessage(), exception.getExceptionMessage());
*/        } catch (Exception e) {
            result = new HashMap<String, Object>();
            result.put("success", false);
            result.put("errorMsg", "系统错误");
            result.put("result", true);
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        logger.info("响应, url: {}, result: {}", url, result);
        return result;
    }
}
