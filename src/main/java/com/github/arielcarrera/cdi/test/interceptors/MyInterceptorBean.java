package com.github.arielcarrera.cdi.test.interceptors;
import java.io.Serializable;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.github.arielcarrera.cdi.test.services.InterceptionStatus;

@Priority(1)
@Dependent
@Interceptor
@MyInterceptor
public class MyInterceptorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @AroundInvoke
    public Object intercept(final InvocationContext context) throws Exception {
    	if (context.getTarget() instanceof InterceptionStatus) {
    		((InterceptionStatus)context.getTarget()).markAsIntercepted();
    	} else {
    		throw new RuntimeException("Target is not an InterceptionStatus instance");
    	}
    	if (context.getMethod().getReturnType().equals(String.class)) {
    		return "Intercepted: " + context.proceed();
    	}
    	return context.proceed();
    }
}
