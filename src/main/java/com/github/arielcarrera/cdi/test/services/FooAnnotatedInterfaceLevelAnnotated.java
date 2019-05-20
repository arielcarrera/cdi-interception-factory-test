package com.github.arielcarrera.cdi.test.services;

import com.github.arielcarrera.cdi.test.interceptors.MyInterceptor;

@MyInterceptor
public interface FooAnnotatedInterfaceLevelAnnotated extends InterceptionStatus {

	String ping();

}