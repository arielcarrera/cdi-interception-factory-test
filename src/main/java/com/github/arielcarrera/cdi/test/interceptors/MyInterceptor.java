package com.github.arielcarrera.cdi.test.interceptors;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;
import javax.interceptor.InterceptorBinding;

/**
 * Interceptor Annotation 
 * @author Ariel Carrera
 *
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface MyInterceptor {

    @SuppressWarnings("all")
    public static class Literal extends AnnotationLiteral<MyInterceptor> implements MyInterceptor {

        private Literal() {
        }

        public static final Literal INSTANCE = new Literal();
    }
}