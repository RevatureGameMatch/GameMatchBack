package com.revature.g2g.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.g2g.services.helpers.LoggerSingleton;

@Component
@Aspect
public class LoggingAspect {
//	private LoggerSingleton loggerSingleton;
//	@Autowired
//	public LoggingAspect(LoggerSingleton loggerSingleton) {
//		super();
//		this.loggerSingleton = loggerSingleton;
//	}
//	@Around("execution(* com.revature.g2g.services..*.*(..))")
//	public Object logTime(ProceedingJoinPoint jp) throws Throwable {
//		long startTime = System.currentTimeMillis();
//		Object result = jp.proceed();
//		String timeMsg = jp.getTarget() + " invoked " + jp.getSignature() + " taking " + (System.currentTimeMillis() - startTime) + " ms to run";
//		loggerSingleton.getPerformanceLog().trace(timeMsg);
//		return result;
//	}
}