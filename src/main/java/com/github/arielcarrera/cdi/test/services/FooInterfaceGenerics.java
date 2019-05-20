package com.github.arielcarrera.cdi.test.services;

public interface FooInterfaceGenerics<X extends Object,Y extends Object> extends InterceptionStatus {

	public String ping(X parameterX, Y parameterY);

}