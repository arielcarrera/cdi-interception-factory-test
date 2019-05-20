package com.github.arielcarrera.cdi.test.services.complex;

public interface ComplexFooB {

	default String pong() {
		return "pong";
	}

}