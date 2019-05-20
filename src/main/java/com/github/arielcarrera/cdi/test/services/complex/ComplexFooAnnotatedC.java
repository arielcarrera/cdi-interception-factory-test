package com.github.arielcarrera.cdi.test.services.complex;

import com.github.arielcarrera.cdi.test.interceptors.MyInterceptor;
import com.github.arielcarrera.cdi.test.services.InterceptionStatus;

public interface ComplexFooAnnotatedC extends ComplexFooAnnotatedA, ComplexFooAnnotatedB, InterceptionStatus {

	@MyInterceptor
	String pingpong();
}