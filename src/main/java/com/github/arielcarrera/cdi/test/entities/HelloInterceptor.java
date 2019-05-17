package com.github.arielcarrera.cdi.test.entities;
import java.io.Serializable;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Priority(1)
@Dependent
@Interceptor
@Hello
public class HelloInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @AroundInvoke
    public Object intercept(final InvocationContext context) throws Exception {
        return "Hello " + context.proceed();
    }
}
