package com.birdben.log.springaop;

import com.birdben.log.annotation.Log;
import com.birdben.log.annotation.LogParam;
import com.birdben.log.bean.LogInfo;
import com.birdben.log.exception.LogBindingException;
import com.birdben.log.handler.DefaultLogHandler;
import com.birdben.log.utils.DateUtils;
import com.google.common.base.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author birdben
 * @version V1.0
 * @name LogAopWithPointcut
 * @description Aop切面处理
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
@Aspect
@Component
public class LogAopWithPointcut {

    /** 定义系统级别的日志,并且从JoinPoint中获取这些参数,并且传给LogHandler */
    ThreadLocal<Long> startTimestamp = new ThreadLocal<Long>();
    ThreadLocal<Long> endTimestamp = new ThreadLocal<Long>();
    ThreadLocal<Date> startDateTime = new ThreadLocal<Date>();
    ThreadLocal<Date> endDateTime = new ThreadLocal<Date>();
    ThreadLocal<Long> time = new ThreadLocal<Long>();
    ThreadLocal<Float> runTime = new ThreadLocal<Float>();
    ThreadLocal<String> tag = new ThreadLocal<String>();
    ThreadLocal<String> className = new ThreadLocal<String>();
    ThreadLocal<String> methodName = new ThreadLocal<String>();
    ThreadLocal<List<String>> args = new ThreadLocal<List<String>>();
    ThreadLocal<String> userMessage = new ThreadLocal<String>();

    /**
     * 在所有标注@Log的地方切入
     */
    @Pointcut("@annotation(com.birdben.log.annotation.Log)")
    public void log() {
        System.out.println("切入点");
    }

    /**
     * 在Pointcut之前执行
     * @param joinPoint
     */
    @Before("log()")
    public void beforeExec(JoinPoint joinPoint) {
        long currentStartTime = System.currentTimeMillis();
        System.out.println("currentStartTime:" + DateUtils.format(new Date(currentStartTime), DateUtils.DEFAULT_PATTERN));

        // 保存方法执行开始时间
        time.set(currentStartTime);
        startTimestamp.set(currentStartTime);
        startDateTime.set(new Date(currentStartTime));

        // 保存方法唯一标记
        tag.set(UUID.randomUUID().toString());

        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        System.out.println(method.getAnnotation(Log.class).message() + "标记" + tag.get());
    }

    /**
     * 在Pointcut之后执行
     * @param joinPoint
     */
    @After("log()")
    public void afterExec(JoinPoint joinPoint) {

        long currentEndTime = System.currentTimeMillis();
        System.out.println("currentEndTime:" + DateUtils.format(new Date(currentEndTime), DateUtils.DEFAULT_PATTERN));

        // 保存方法执行结束时间
        endTimestamp.set(currentEndTime);
        endDateTime.set(new Date(currentEndTime));

        float currentRunTime = (endTimestamp.get().longValue() - startTimestamp.get().longValue()) / 1000F;

        // 保存方法执行时间
        runTime.set(currentRunTime);

        // 保存方法的其他信息
        LogInfo logInfo = buildLogInfo(joinPoint);
        System.out.println("===================LogInfo开始=====================");
        System.out.println(logInfo.toString());
        System.out.println("===================LogInfo结束=====================");

        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        System.out.println("标记为" + tag.get() + "的方法" + method.getName() + "运行消耗 " + runTime.get().toString() + " 秒");

        // 方式二:多参数方式处理日志
        handlerLogWithMultipleParam(method, joinPoint);

        // 方式三:Map参数方式处理日志
        //handlerLogWithMapParam(method, joinPoint);
    }

    private LogInfo buildLogInfo(JoinPoint joinPoint) {
        System.out.println("--------------------recordLogInfo开始------------------------------");
        System.out.println("King:\t\t\t\t\t" + joinPoint.getKind());
        System.out.println("Target:\t\t\t\t\t" + joinPoint.getTarget().toString());
        Object[] os = joinPoint.getArgs();
        System.out.print("Args:");
        for (int i = 0; i < os.length; i++) {
            System.out.println("\t\t\t\t\t参数[" + i + "]:\t" + os[i].toString());
        }
        System.out.println("Signature:\t\t\t\t" + joinPoint.getSignature());
        System.out.println("SourceLocation:\t\t\t" + joinPoint.getSourceLocation());
        System.out.println("StaticPart:\t\t\t\t" + joinPoint.getStaticPart());

        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        List<String> argList = new ArrayList<String>();
        for (int i = 0; i < os.length; i++) {
            System.out.println("\t\t\t\t\t\t参数[" + i + "]:\t" + os[i].toString());
            argList.add(os[i].toString());
        }

        // 保存方法的Class信息
        className.set(joinPoint.getTarget().toString());

        // 保存方法的Method信息
        methodName.set(method.getName());

        // 保存方法的Args信息
        args.set(argList);

        // 保存方法的自定义日志消息
        userMessage.set(method.getAnnotation(Log.class).message());

        LogInfo logInfo = new LogInfo(startTimestamp.get(), endTimestamp.get(), startDateTime.get(), endDateTime.get(), runTime.get(), className.get(), methodName.get(), args.get(), userMessage.get());
        System.out.println("--------------------recordLogInfo结束------------------------------");
        return logInfo;
    }

    /**
     * 第二种方式:将所有参数原封不动的传给LogHandler
     * @param method
     * @param joinPoint
     */
    private void handlerLogWithMultipleParam(Method method, JoinPoint joinPoint) {
        System.out.println("--------------------handlerLogWithMultipleParam开始------------------------------");
        if (!Strings.isNullOrEmpty(method.getAnnotation(Log.class).method())) {
            String logHandlerMethodName = method.getAnnotation(Log.class).method();
            Class<?> logHandlerClass = method.getAnnotation(Log.class).handler();
            if (logHandlerClass == DefaultLogHandler.class) {
                // 如果LogHandler是默认的DefaultLogHandler.class,就使用当前调用的Class
                logHandlerClass = joinPoint.getTarget().getClass();
            }
            Method[] methods = logHandlerClass.getMethods();
            boolean notFoundMethod = true;
            for (int i = 0; i < methods.length; ++i) {
                String currentMethodName = methods[i].getName();
                if (Strings.isNullOrEmpty(logHandlerMethodName)) {
                    continue;
                }
                if (!Strings.isNullOrEmpty(currentMethodName) && !Strings.isNullOrEmpty(logHandlerMethodName)) {
                    if (currentMethodName.equals(logHandlerMethodName)) {
                        try {
                            methods[i].invoke(logHandlerClass.newInstance(), joinPoint.getArgs());
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new LogBindingException("Calling Class: '" + logHandlerClass + "', Method: '" + logHandlerMethodName + "' error.");
                        }
                        notFoundMethod = false;
                        break;
                    }
                }
            }
            if (notFoundMethod) {
                throw new LogBindingException("Method '" + logHandlerMethodName + "' not found in '" + logHandlerClass + "' Class .");
            }
        }
        System.out.println("--------------------handlerLogWithMultipleParam结束------------------------------");
    }

    /**
     * 第三种方式:将所有参数转化成一个Map参数传给LogHandler
     * @param method
     * @param joinPoint
     */
    private void handlerLogWithMapParam(Method method, JoinPoint joinPoint) {
        System.out.println("--------------------handlerLogWithMapParam开始------------------------------");
        if (!Strings.isNullOrEmpty(method.getAnnotation(Log.class).method())) {
            String logHandlerMethodName = method.getAnnotation(Log.class).method();
            Class<?> logHandlerClass = method.getAnnotation(Log.class).handler();
            if (logHandlerClass == DefaultLogHandler.class) {
                // 如果LogHandler是默认的DefaultLogHandler.class,就使用当前调用的Class
                logHandlerClass = joinPoint.getTarget().getClass();
            }
            SortedMap<Integer, String> params = Collections.unmodifiableSortedMap(getParams(method, hasNamedParams(method)));
            Method[] methods = logHandlerClass.getMethods();
            boolean notFoundMethod = true;
            if (!Strings.isNullOrEmpty(logHandlerMethodName)) {
                for (int i = 0; i < methods.length; ++i) {
                    Object param = convertArgsToMapParam(params, method, joinPoint.getArgs());
                    String currentMethodName = methods[i].getName();
                    if (Strings.isNullOrEmpty(logHandlerMethodName)) {
                        continue;
                    }
                    if (!Strings.isNullOrEmpty(currentMethodName)) {
                        if (currentMethodName.equals(logHandlerMethodName)) {
                            try {
                                methods[i].invoke(logHandlerClass.newInstance(), param);
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new LogBindingException("Calling Class: '" + logHandlerClass + "', Method: '" + logHandlerMethodName + "' error.");
                            }
                            notFoundMethod = false;
                            break;
                        }
                    }
                }
            }
            if (notFoundMethod) {
                throw new LogBindingException("Method '" + logHandlerMethodName + "' not found in '" + logHandlerClass + "' Class .");
            }
        }
        System.out.println("--------------------handlerLogWithMapParam结束------------------------------");
    }

    /**
     * 在Pointcut开始和结束执行
     * @param pjp
     */
    @Around("log()")
    public void aroundExec(ProceedingJoinPoint pjp) throws Throwable {
        //System.out.println("Around Before");
        pjp.proceed();
        //System.out.println("Around After");
    }

    private Object convertArgsToMapParam(SortedMap<Integer, String> params, Method method, Object[] args) {
        final int paramCount = params.size();
        if (args == null || paramCount == 0) {
            return null;
        } else if (!hasNamedParams(method) && paramCount == 1) {
            return args[params.keySet().iterator().next()];
        } else {
            final Map<String, Object> param = new ParamMap<Object>();
            int i = 0;
            for (Map.Entry<Integer, String> entry : params.entrySet()) {
                param.put(entry.getValue(), args[entry.getKey()]);
                final String genericParamName = "param" + String.valueOf(i + 1);
                if (!param.containsKey(genericParamName)) {
                    param.put(genericParamName, args[entry.getKey()]);
                }
                i++;
            }
            return param;
        }
    }

    private SortedMap<Integer, String> getParams(Method method, boolean hasNamedParameters) {
        final SortedMap<Integer, String> params = new TreeMap<Integer, String>();
        final Class<?>[] argTypes = method.getParameterTypes();
        for (int i = 0; i < argTypes.length; i++) {
            String paramName = String.valueOf(params.size());
            if (hasNamedParameters) {
                paramName = getParamNameFromAnnotation(method, i, paramName);
            }
            params.put(i, paramName);
        }
        return params;
    }

    private String getParamNameFromAnnotation(Method method, int i, String paramName) {
        final Object[] paramAnnos = method.getParameterAnnotations()[i];
        for (Object paramAnno : paramAnnos) {
            if (paramAnno instanceof LogParam) {
                paramName = ((LogParam) paramAnno).value();
            }
        }
        return paramName;
    }

    private boolean hasNamedParams(Method method) {
        boolean hasNamedParams = false;
        final Object[][] paramAnnos = method.getParameterAnnotations();
        for (Object[] paramAnno : paramAnnos) {
            for (Object aParamAnno : paramAnno) {
                if (aParamAnno instanceof LogParam) {
                    hasNamedParams = true;
                    break;
                }
            }
        }
        return hasNamedParams;
    }

    /**
     * 定义一个Map参数,将所有参数存储在这个Map,用来给LogHandler传参
     * @param <V>
     */
    public static class ParamMap<V> extends HashMap<String, V> {

        private static final long serialVersionUID = -2212268410512043556L;

        @Override
        public V get(Object key) {
            if (!super.containsKey(key)) {
                throw new LogBindingException("Parameter '" + key + "' not found. Available parameters are " + keySet());
            }
            return super.get(key);
        }

    }


}