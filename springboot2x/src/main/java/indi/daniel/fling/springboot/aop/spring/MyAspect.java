package indi.daniel.fling.springboot.aop.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 2018/12/18.
 */
@Aspect
@Component
// set order for multiple aspects
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyAspect {

    //TODO:DeclareParents
//    @DeclareParents(
//            value = "indi.daniel.fling.springboot.aop.spring.AnimalServiceImpl+",
//            defaultImpl=AnimalValidatorImpl.class
//        )
//    public AnimalValidator animalValidator;

    @Pointcut("execution(* indi.daniel.fling.springboot.aop.spring.AnimalServiceImpl.adopt(..))")
    public void myPointCut() {
    }

    @Before("myPointCut() && args(arg1)")
    public void before (JoinPoint joinPoint, Object arg1) {

        System.out.println("Before execution!");
        System.out.println("Inject joinPoint:" + joinPoint.toString());
        System.out.println("Inject args:" + arg1);
    }

    @After("myPointCut()")
    public void after () {
        System.out.println("After execution!");
    }

    @AfterThrowing("myPointCut()")
    public void afterThrowing () {
        System.out.println("After Throwing!");
    }

    @AfterReturning("myPointCut()")
    public void afterReturning () {
        System.out.println("After Returning!");
    }

    @Around("myPointCut()")
    public void around (ProceedingJoinPoint jp) throws Throwable {
        System.out.println("Around before, args: " + jp.getArgs());
//
//        AnimalValidator validator = (AnimalValidator)jp.getTarget();
//        if( validator.isValid((Animal) jp.getArgs()[0])) {
            Object obj = jp.proceed();
//        } else {
//            System.out.println("Not valid animal!");
//        }

        System.out.println("Around after, returns! ");
    }
}
