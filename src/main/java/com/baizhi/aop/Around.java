package com.baizhi.aop;

import com.baizhi.controller.LoggerAnnotation;
import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

public class Around implements MethodInterceptor {

    @Autowired
        LogService logService;
        @Override
        public Object invoke(MethodInvocation Invocation) throws Throwable {
            //做的什么事
            Method method = Invocation.getMethod();
            LoggerAnnotation annotation = method.getAnnotation(LoggerAnnotation.class);
            String name = annotation.name();
            //什么人做
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpSession session = requestAttributes.getRequest().getSession();
            String username = (String) session.getAttribute("username");
            //什么时间
            Date date = new Date();
            Object proceed = null;
            boolean flag=false;
            try {
                proceed = Invocation.proceed();
                flag=true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            Log log = new Log();
            log.setName(username);
            log.setSetTime(date);
            log.setSetWhy(name);
            log.setStatus(flag+"");
            logService.insert(log);
            return proceed;
        }
}
