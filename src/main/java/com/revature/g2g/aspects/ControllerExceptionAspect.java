package com.revature.g2g.aspects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.g2g.api.templates.MessageTemplate;
import com.revature.g2g.services.helpers.LoggerSingleton;

@ControllerAdvice
@Component
@Aspect
public class ControllerExceptionAspect {
	private LoggerSingleton loggerSingleton;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Autowired
	public ControllerExceptionAspect(LoggerSingleton loggerSingleton) {
		super();
		this.loggerSingleton = loggerSingleton;
	}
	
	@Autowired(required = false)
	public void setRequest(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Custom Error Message\"}");
    }

}
