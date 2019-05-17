package com.github.arielcarrera.cdi.test.entities;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;
import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface Hello {

    @SuppressWarnings("all")
    public static class Literal extends AnnotationLiteral<Hello> implements Hello {

        private Literal() {
        }

        public static final Literal INSTANCE = new Literal();
    }
}