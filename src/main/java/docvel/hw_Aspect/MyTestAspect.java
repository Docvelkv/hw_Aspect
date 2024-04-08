package docvel.hw_Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyTestAspect {

    private static final Logger log = LoggerFactory.getLogger(MyTestAspect.class);

    @Pointcut("@annotation(docvel.hw_Aspect.Timer)")
    public void methodsFromConvertFileClass(){}

    @Around("methodsFromConvertFileClass()")
    public Object measuringExecutionTimeOfMethod(ProceedingJoinPoint point) throws Throwable{
        long start = System.currentTimeMillis();
        Object obj = point.proceed();
        long elapsedTime = System.currentTimeMillis() - start;

        log.info("время выполнения {}.{}: {} ms",
                point.getTarget().getClass().getName(),
                point.getSignature().getName(),
                elapsedTime);

        return obj;
    }
}
