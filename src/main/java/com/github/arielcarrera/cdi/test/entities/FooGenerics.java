package com.github.arielcarrera.cdi.test.entities;

public class FooGenerics<X extends Object,Y extends Object> {

	public String ping(X parameterX, Y parameterY) {
		return parameterX.toString() + "pong" + parameterY.toString();
	}

}