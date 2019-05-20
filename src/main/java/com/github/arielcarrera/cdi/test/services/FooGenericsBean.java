package com.github.arielcarrera.cdi.test.services;

public class FooGenericsBean<X extends Object,Y extends Object> implements InterceptionStatus {
	
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