package tech.jusan.weather.aop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* tech.jusan.weather.services.db.impl..*(..))")
    public void dbMethods() {
    }

    @Pointcut("execution(* tech.jusan.weather.services.impl..*(..))")
    public void serviceMethods() {
    }

    @Before("serviceMethods() || dbMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Executing method: {} args: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "serviceMethods() || dbMethods()", throwing = "exception")
    public void logExceptions(JoinPoint joinPoint, Throwable exception) {
        log.error("Exception in method: {} error: {}", joinPoint.getSignature(), exception.getMessage(), exception);
    }
}