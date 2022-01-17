package com.lt.dailytest.config;

import com.google.common.base.Stopwatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

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

    private ThreadLocal<Long> threadLocalTime = new ThreadLocal<>();

    /**
     * 1）execution(public * *(..))——表示匹配所有public方法
     * 2）execution(* set*(..))——表示所有以“set”开头的方法
     * 3）execution(* com.xyz.service.AccountService.*(..))——表示匹配所有AccountService接口的方法
     * 4）execution(* com.xyz.service.*.*(..))——表示匹配service包下所有的方法
     * 5）execution(* com.xyz.service..*.*(..))——表示匹配service包和它的子包下的方法
     * 签名，可以理解成这个切入点的一个名称
     */
    @Pointcut("execution(public * com.lt.dailytest..*Controller.*(..))")//切入点描述 这个是controller包的切入点
    public void controllerLog() {
    }

    @Before("controllerLog()") //在切入点的方法run之前要干的
    public void logBeforeController(JoinPoint joinPoint) {
        this.threadLocalTime.set(System.currentTimeMillis());

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 记录下请求内容
        LOGGER.info("################URL : " + request.getRequestURL().toString());
        LOGGER.info("################HTTP_METHOD : " + request.getMethod());
        LOGGER.info("################IP : " + request.getRemoteAddr());
        LOGGER.info("################THE ARGS OF THE CONTROLLER : " + Arrays.toString(joinPoint.getArgs()));
        LOGGER.info("################THE getQueryString OF THE CONTROLLER : " + request.getQueryString());

        //下面这个getSignature().getDeclaringTypeName()是获取包+类名的   然后后面的joinPoint.getSignature.getName()获取了方法名
        LOGGER.info("################CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("################TARGET: " + joinPoint.getTarget());//返回的是需要加强的目标类的对象
        LOGGER.info("################THIS: " + joinPoint.getThis());//返回的是经过加强后的代理类的对象
    }


    @Around("controllerLog()")
    public Object assableBeforeArgs(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }

    @After("controllerLog()")
    public void afterAssable(JoinPoint joinPoint) {
        LOGGER.info("##################### assableBeforeArgs after");
    }

    /**
     * 如果包含返回参数的时候 会走这个方法
     *
     * @param joinPoint
     * @param returnOb  返回参数的数据
     */
    @AfterReturning(returning = "returnOb", pointcut = "controllerLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnOb) {
        //Map parseObject = JSON.parseObject(returnOb.toString(), Map.class);
        //Object[] args = joinPoint.getArgs();
        LOGGER.info("##################### the return of the method is : " + returnOb + " cost time :"
                + (System.currentTimeMillis() - this.threadLocalTime.get()));

    }

    @AfterThrowing(pointcut = "controllerLog()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        LOGGER.info("连接点方法为：" + methodName + ",参数为：" + args + ",异常为：" + ex);

    }
}
