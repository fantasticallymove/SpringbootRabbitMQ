package com.example.test_mulitple_schedule;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class LogAop {

    //切点(第一个*表示返回值，第二个*表示任意类，第三个*表示任意方法，(..)表示任意参数)
    @Pointcut("execution(* com.example.test_mulitple_schedule.jpa.*.*(..))")
    public void cut() {
    }

    //前置通知
    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法开始执行..." + ",時間:" + new Date());
    }

    // 后置通知
    @After(value = "cut()")
    public void after(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法执行结束..." + ",時間:" + new Date());
    }
}
