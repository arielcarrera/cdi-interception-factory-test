package com.github.arielcarrera.cdi.test.services;

public class FooAnnotatedMethodLevelAnnotatedBean implements FooAnnotatedMethodLevelAnnotated {
	
	boolean intercepted = false;
	
	@Override
	public void markAsIntercepted() {
		intercepted = true;
	}

	@Override
	public boolean getIntercepted() {
		return intercepted;
	}
	
	@Override
	public String ping() {
		return "ping";
	}

}