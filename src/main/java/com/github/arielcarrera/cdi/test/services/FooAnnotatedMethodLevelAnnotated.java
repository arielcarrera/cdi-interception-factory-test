package com.github.arielcarrera.cdi.test.services;

import com.github.arielcarrera.cdi.test.interceptors.MyInterceptor;

public interface FooAnnotatedMethodLevelAnnotated extends InterceptionStatus {

	@MyInterceptor
	String ping();

}