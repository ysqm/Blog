package com.elm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 自定义切片，公共字段自动填充
 */
@Aspect
@Component
@Slf4j
public class AutoFIllAspect {

    @Pointcut("execution(* com.elm.mapper.*.*(..)) && @annotation(com.elm.annotation.AutoFill)")
    public void autoFillPointCut() {}

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始自动填充");
        // TODO
    }
}
