package com.jorda.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLogginAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution( * com.jorda.springdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution( * com.jorda.springdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution( * com.jorda.springdemo.dao.*.*(..))")
    private void forDAOPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void appFlow() {
    }

    @Before("appFlow()")
    public void before(JoinPoint theJointPoint) {
	String theMethod = theJointPoint.getSignature().toShortString();
	this.myLogger.info("===>>> in the @Before: calling method: " + theMethod);

	Object[] args = theJointPoint.getArgs();

	for (Object tmpArg : args) {
	    this.myLogger.info("=====> arg: " + tmpArg);
	}

    }

    @AfterReturning(pointcut = "appFlow()", returning = "theResult")
    public void afterReturning(JoinPoint theJointPoint, Object theResult) {
	String theMethod = theJointPoint.getSignature().toShortString();
	this.myLogger.info("===>>> in the @AfeterReturning from method: " + theMethod);

	Object[] args = theJointPoint.getArgs();

	for (Object tmpArg : args) {
	    this.myLogger.info("=====> arg: " + tmpArg);
	}

    }
}
