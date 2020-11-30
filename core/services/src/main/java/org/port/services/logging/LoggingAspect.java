package org.port.services.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* org.port..*(..))")
    public void logBeforeExecution(JoinPoint joinPoint) {
        logger(joinPoint).debug("[START] " + methodOf(joinPoint) + " arguments: " + argumentsOf(joinPoint));
    }

    @AfterReturning(pointcut = "execution(* org.port..*(..)))", returning = "returnValue")
    public void logAfterReturning(JoinPoint joinPoint, Object returnValue) {
        logger(joinPoint).debug("[END] " + methodOf(joinPoint) + " return value: " + (returnValue == null ? "(null)" : returnValue.toString()));
    }

    @AfterThrowing(pointcut = "execution(* org.port..*(..))", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger(joinPoint).error("[ERROR] " + methodOf(joinPoint), e);
    }

    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    }

    private String methodOf(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    private String argumentsOf(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();

        if (arguments == null) {
            return "(null)";
        } else {
            return Arrays.stream(arguments).map(arg ->
                    arg == null ? "(null)" : arg.toString()
            ).collect(Collectors.joining(","));
        }
    }
}
