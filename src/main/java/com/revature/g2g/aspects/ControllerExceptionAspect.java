package com.revature.g2g.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.g2g.services.helpers.LoggerSingleton;

@ControllerAdvice
@Component
@Aspect
public class ControllerExceptionAspect {
	@Autowired
	private LoggerSingleton loggerSingleton;

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
    	loggerSingleton.getExceptionLogger().warn("Error caught inside of the ControllerExceptionAspect:", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Custom Error Message\"}");
    }

}
