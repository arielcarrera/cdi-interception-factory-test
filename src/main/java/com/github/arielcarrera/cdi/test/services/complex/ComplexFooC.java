package com.github.arielcarrera.cdi.test.services.complex;

import com.github.arielcarrera.cdi.test.services.InterceptionStatus;

public interface ComplexFooC extends ComplexFooA, ComplexFooB, InterceptionStatus {

	String pingpong();
}