package com.revature.g2g.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.revature.g2g.api.templates.MessageTemplate;
import com.revature.g2g.services.helpers.LoggerSingleton;

@Component
@Aspect
public class ControllerExceptionAspect {
//	private LoggerSingleton loggerSingleton;
//	@Autowired
//	public ControllerExceptionAspect(LoggerSingleton loggerSingleton) {
//		super();
//		this.loggerSingleton = loggerSingleton;
//	}
//	@Around("execution(* *(..)) && within(com.revature.g2g.services.controllers..*)")
//	public ResponseEntity logControllers(ProceedingJoinPoint jp) {
//		ResponseEntity result = null;
//		try {
//			result = (ResponseEntity) jp.proceed();
//			return result;
//		} catch (Throwable e) {
//			String controlLog = jp.getTarget() + " invoked " + jp.getSignature() + " throwing: " + e;
//			loggerSingleton.getExceptionLogger().warn(controlLog, e);
//			MessageTemplate errorMessage = new MessageTemplate("Internal Server Error");
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
//		}
//	}
//	@AfterThrowing(pointcut = "within(com.revature.g2g.api.controllers.*)", throwing="e")
//	public ResponseEntity<MessageTemplate> logControllers(JoinPoint jp, Exception e) {
//		String controlLog = jp.getTarget() + " invoked " + jp.getSignature() + " throwing: " + e;
//		loggerSingleton.getExceptionLogger().warn(controlLog, e);
//		MessageTemplate errorMessage = new MessageTemplate("Internal Server Error");
//		jp.
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
//	}
}
