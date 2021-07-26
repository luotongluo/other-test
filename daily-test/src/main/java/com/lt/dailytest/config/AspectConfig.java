package com.lt.dailytest.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author tong.luo
 * @description AspectController
 * https://www.cnblogs.com/wangshen31/p/9379197.html
 * @date 2021/7/22 17:57
 */
@Aspect
@Component
public class AspectConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectConfig.class);

    /**
     * 1）execution(public * *(..))——表示匹配所有public方法
     * 2）execution(* set*(..))——表示所有以“set”开头的方法
     * 3）execution(* com.xyz.service.AccountService.*(..))——表示匹配所有AccountService接口的方法
     * 4）execution(* com.xyz.service.*.*(..))——表示匹配service包下所有的方法
     * 5）execution(* com.xyz.service..*.*(..))——表示匹配service包和它的子包下的方法
     *签名，可以理解成这个切入点的一个名称
     */
    @Pointcut("execution(public * com.lt.dailytest..*Controller.*(..))")//切入点描述 这个是controller包的切入点
    public void controllerLog() {
    }

    @Before("controllerLog()") //在切入点的方法run之前要干的
    public void logBeforeController(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 记录下请求内容
        LOGGER.info("################URL : " + request.getRequestURL().toString());
        LOGGER.info("################HTTP_METHOD : " + request.getMethod());
        LOGGER.info("################IP : " + request.getRemoteAddr());
        LOGGER.info("################THE ARGS OF THE CONTROLLER : " + Arrays.toString(joinPoint.getArgs()));

        //下面这个getSignature().getDeclaringTypeName()是获取包+类名的   然后后面的joinPoint.getSignature.getName()获取了方法名
        LOGGER.info("################CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("################TARGET: " + joinPoint.getTarget());//返回的是需要加强的目标类的对象
        LOGGER.info("################THIS: " + joinPoint.getThis());//返回的是经过加强后的代理类的对象
    }


   /* @Around("controllerLog()")
    public void assableBeforeArgs(ProceedingJoinPoint pjp) {
        try {
            Object[] args = pjp.getArgs();
            JoinPoint.StaticPart staticPart = pjp.getStaticPart();
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        LOGGER.info("assableBeforeArgs Around");
    }*/

    @After("controllerLog()")
    public void afterAssable(JoinPoint joinPoint) {
        LOGGER.info("assableBeforeArgs after");
    }

    @AfterReturning(returning = "returnOb", pointcut = "controllerLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnOb) {
        Map parseObject = JSON.parseObject(returnOb.toString(), Map.class);
        Object[] args = joinPoint.getArgs();
        System.out.println("##################### the return of the method is : " + returnOb);
    }
}
