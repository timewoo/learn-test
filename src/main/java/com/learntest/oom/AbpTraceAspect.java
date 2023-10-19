package com.learntest.oom;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jack
 */
@Aspect
@Slf4j
@Component
@Order(-1)
public class AbpTraceAspect {

    @Resource
    @Lazy
    private Tracer jaegerTracer;

    @Pointcut("execution(* com.learntest.oom.TraceController.*(..))")
    public void web() {
    }

    @Around("web()")
    public Object aroundWeb(ProceedingJoinPoint pjp) throws Throwable {
        Object object = null;
        // 拿到参数
        Object[] args = pjp.getArgs();
        // 拿到请求
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取请求头
        Enumeration<String> enumeration = request.getHeaderNames();
        StringBuffer headers = new StringBuffer();
        if (!ObjectUtils.isEmpty(enumeration)) {
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                headers.append("{" + name + ":" + value + "}").append(" , ");
            }
        }
        final String className = pjp.getTarget().getClass().getName();
        final String methodName = pjp.getSignature().getName();
        String timeNow = LocalDateTime.now().toString();
        Span currentSpan = createNewSpan(className, methodName, timeNow, false);
        try (Scope scope = jaegerTracer.scopeManager().activate(currentSpan)) {
            String infoLog = String.format(" | log point name : WebEntryPoint  | call [%s]:[%s]  | by param : [%s]  |" +
                    " with header : [%s]  | at : [%s]", className, methodName, args, headers, timeNow);
            currentSpan.log(infoLog);
            log.info(infoLog);
            object = pjp.proceed();
        } catch (Throwable t) {
            handleThrowable(currentSpan, t);
        } finally {
            String infoLog;
            if (object != null) {
                infoLog = String.format(" | log point name : WebEndPoint  | return : [%s]  | at : [%s]", object,
                        LocalDateTime.now().toString());
            } else {
                infoLog = String.format(" | log point name : WebEndPoint  | void return  | at : [%s]",
                        LocalDateTime.now().toString());
            }
            currentSpan.log(infoLog);
            log.info(infoLog);
            if (currentSpan != null) {
                currentSpan.finish();
            }
        }
        return object;
    }

    private Span createNewSpan(String className, String methodName, String timeNow, Boolean withParent) {
        Tracer.SpanBuilder spanBuilder = jaegerTracer.buildSpan(className + ":" + methodName + ":" + timeNow)
                .withTag("class", className)
                .withTag("method", methodName)
                .withTag("time", timeNow);
        if (withParent) {
            spanBuilder.asChildOf(jaegerTracer.activeSpan());
        } else {
            spanBuilder.ignoreActiveSpan();
        }
        Span currentSpan = spanBuilder.start();
        return currentSpan;
    }

    private void handleThrowable(Span currentSpan, Throwable t) throws Throwable {
        String stackTrace = getStackTrace(t);
        log.error(stackTrace);
        Map exceptionLogs = new LinkedHashMap(4);
        exceptionLogs.put("event", Tags.ERROR.getKey());
        exceptionLogs.put("message", t.getMessage());
        exceptionLogs.put("stack", stackTrace);
        exceptionLogs.put("error.object", t);
        currentSpan.log(exceptionLogs);
        Tags.ERROR.set(currentSpan, true);
        throw t;
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

}
