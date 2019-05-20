package com.github.arielcarrera.cdi.test.services.complex;

import com.github.arielcarrera.cdi.test.interceptors.MyInterceptor;

public interface ComplexFooAnnotatedB {

	@MyInterceptor
	default String pong() {
		return "pong";
	}

}