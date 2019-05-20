package com.github.arielcarrera.cdi.test.services;

public class FooBean implements Foo {
	
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