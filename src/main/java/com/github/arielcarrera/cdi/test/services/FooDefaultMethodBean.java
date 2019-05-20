package com.github.arielcarrera.cdi.test.services;

public class FooDefaultMethodBean implements FooDefaultMethod {
	
	boolean intercepted = false;
	
	@Override
	public void markAsIntercepted() {
		intercepted = true;
	}

	@Override
	public boolean getIntercepted() {
		return intercepted;
	}

}