package com.github.arielcarrera.cdi.test.services;
import com.github.arielcarrera.cdi.test.interceptors.MyInterceptor;

/**
 * 
 * @author Ariel Carrera
 *
 */
public interface FooAnnotatedInterfaceDefaultMethod extends InterceptionStatus {

	@MyInterceptor
    default String ping() {
        return "ping";
    }

    String pong();
}
