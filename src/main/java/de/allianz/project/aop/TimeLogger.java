package de.allianz.project.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Aspect
@Log
@Component
public class TimeLogger {

    private static final Logger LOG = LoggerFactory.getLogger(TimeLogger.class);
    private static final String LOGGING_MESSAGE = "method {} in class {} took {} milliseconds to execute";

    @Before("bean(ToDoController)")  // wo soll es ausgeführt werden
    public Object logControllerExecutionTime(JoinPoint joinPoint) {        // bevor Controller ausgeführt wird, soll info ausgeloggt werden
        log.log(Level.INFO, "Controller started" + joinPoint.getSignature().getName());
        return null;
    }

    // before + after
    @Around("bean(ToDo*)")  // wo soll es ausgeführt werden; toDoController oder alle ToDo (*)
    public Object logControllerExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {       // jointPoint proceeding, den wir beinflussen können
        Long start = System.currentTimeMillis(); // aktuelle Systemzeit
        Object proceed = joinPoint.proceed(); // nachdem methode getriggert haben und ausgeführt wird
        Long end = System.currentTimeMillis(); // Systemzeit wieder holen

        Long difference = end - start;

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();

        log.log(Level.INFO, className + " : " + methodName + " : " + difference);
        log.log(Level.SEVERE, className + " : " + methodName + " : " + difference);
        LOG.info(LOGGING_MESSAGE, methodName, className, difference);
        return proceed;
    }
}
