package org.yousuowei.base.imp.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.yousuowei.base.imp.entity.BaseEntity;

@Aspect
@Component
public class TaskAspect {
	private Logger logger = Logger.getLogger(TaskAspect.class);

	public TaskAspect() {
		// TODO Auto-generated constructor stub
	}

	// @Pointcut("execution(* com.test.spring.aop.pointcutexp..JoinPointObjP2.*(..))")
	// @Pointcut("within(com.test.spring.aop.pointcutexp..*)")
	// @Pointcut("this(com.test.spring.aop.pointcutexp.Intf)")
	// @Pointcut("target(com.test.spring.aop.pointcutexp.Intf)")
	// @Pointcut("@within(org.springframework.transaction.annotation.Transactional)")
	// @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
	// @Pointcut("args(String)")
	@Pointcut("execution(* org.yousuowei.base.ifc.*.*(..)) || execution(* org.yousuowei.*.imp.service.delete(..))")
	public void pointcut() {
	}// 定义一个pointcut，下面用Annotation标注的通知方法可以公用这个pointcut

	// 前置通知
	// 拦截参数为一个String类型的方法
	@Before("pointcut() && args(temp)")
	public void myBeforeAdvice(BaseEntity temp) {// 如果需要知道拦截的方法的信息，也可以需添加JoinPoint参数
		String logInfoText = "这是前置通知" + temp.toString();
		// 将日志信息写入配置的文件中
		logger.info(logInfoText);
	}

	// 后置通知
	// 拦截 返回类型为String 的方法
	@AfterReturning(pointcut = "pointcut()", returning = "result")
	public void myAfterReturnAdvice(String result) {
		logger.info("这是后置通知  " + " result: " + result);
	}

	// 最终置通知
	@After("pointcut()")
	public void doAfter() {
		logger.info("这是最终通知");
	}

	// 异常通知
	@AfterThrowing(pointcut = "pointcut()", throwing = "e")
	public void myThrowingAdvice(JoinPoint jionpoint, Exception e) {
		// 获取被调用的类名
		String targetClassName = jionpoint.getTarget().getClass().getName();
		// 获取被调用的方法名
		String targetMethodName = jionpoint.getSignature().getName();
		// 日志格式字符串
		String logInfoText = "异常通知：执行" + targetClassName + "类的"
				+ targetMethodName + "方法时发生异常";
		// 将日志信息写入配置的文件中
		logger.info(logInfoText);
	}

	// 环绕通知
	// @Around(value="pointcut()")
	@Around("pointcut()")
	public Object myAroundAdvice(ProceedingJoinPoint jionpoint)
			throws Throwable {
		// 获取被调用的方法名
		String targetMethodName = jionpoint.getSignature().getName();
		Object o = jionpoint.proceed();
		String logInfoText = "这是环绕通知：" + targetMethodName;
		logger.info(logInfoText);
		// Object o = jionpoint.proceed();//注意写到这儿的话，环绕通知和其它通知的顺序
		return o;
	}
}