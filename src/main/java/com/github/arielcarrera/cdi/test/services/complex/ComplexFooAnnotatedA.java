package com.github.arielcarrera.cdi.test.services.complex;

import com.github.arielcarrera.cdi.test.interceptors.MyInterceptor;

public interface ComplexFooAnnotatedA {

	@MyInterceptor
	String ping();

}