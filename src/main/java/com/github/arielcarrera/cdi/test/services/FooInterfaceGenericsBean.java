package com.github.arielcarrera.cdi.test.services;

public class FooInterfaceGenericsBean<X extends Object,Y extends Object> implements FooInterfaceGenerics<X,Y> {
	
	boolean intercepted = false;
	
	@Override
	public void markAsIntercepted() {
		intercepted = true;
	}

	@Override
	public boolean getIntercepted() {
		return intercepted;
	}
	
	public String ping(X parameterX, Y parameterY) {
		return parameterX.toString() + "->" + parameterY.toString();
	}

}