package com.github.arielcarrera.cdi.test.services;

public class FooAnnotatedInterfaceLevelAnnotatedBean implements FooAnnotatedInterfaceLevelAnnotated{
	
	boolean intercepted = false;
	
	@Override
	public void markAsIntercepted() {
		intercepted = true;
	}

	@Override
	public boolean getIntercepted() {
		return intercepted;
	}
	
	public String ping() {
		return "ping";
	}

}