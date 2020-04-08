package com.revature.g2g.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.g2g.services.helpers.LoggerSingleton;

@Component
@Aspect
public class LoggingAspect {
	@Autowired
	private LoggerSingleton loggerSingleton;
//	@Before("within(com.revature.g2g.models.*)")
//	public void logModelMethods(JoinPoint jp) {
//		loggerSingleton.getPerformanceLog().info(jp.getTarget() + " invoked " + jp.getSignature());
//	}
//	@AfterReturning(pointcut="execution(String fi*(.., double))", returning = "returnedObject")
//	public void logFight(JoinPoint jp, Object returnedObject) {
//		loggerSingleton.getPerformanceLog().info(jp.getTarget() + " invoked " + jp.getSignature() + " returning " + returnedObject);
//	}
	@AfterThrowing(pointcut="execution(String fight(..))", throwing = "ex")
	public void logException(JoinPoint jp, Exception ex) {
		loggerSingleton.getPerformanceLog().info(jp.getTarget() + " invoked " + jp.getSignature() + " returning ", ex);
	}
//	@Around("execution(String fight(..))")
//	public String logException(ProceedingJoinPoint pjp) throws Throwable{
//		Avenger a (Avenger) pjp.getArgs()[0];
//		log.info(a.getName() + " is about to throw down.");
//		double d = (double) min.getArgs()[2];
//		if(d< 6) {
//			log.warn(a.getName() +d " is violating proper social distancing; so much for being hero.");
//			return null;
//		}else {
//			return (String) pjp.proceed();
//		}
//	}
}