package com.jmt.v1.config.logging;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect // Aspect 를 꼭 명시!
@Log4j2
class LogAspect {

    @Around("@annotation(com.jmt.v1.config.logging.annotation.LogExecutionTime)") // 해당 어노테이션에 대해서
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        Object thisObj = joinPoint.getTarget();
        String className = thisObj.getClass().getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start(className + " - " +joinPoint.getSignature().getName());

        Object proceed = joinPoint.proceed();

        stopWatch.stop();

        log.debug("\n" + stopWatch.prettyPrint());

        return proceed;
    }
}