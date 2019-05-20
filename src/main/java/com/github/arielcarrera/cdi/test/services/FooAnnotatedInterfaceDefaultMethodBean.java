package com.github.arielcarrera.cdi.test.services;

/**
 * 
 * @author Ariel Carrera
 *
 */
public class FooAnnotatedInterfaceDefaultMethodBean implements FooAnnotatedInterfaceDefaultMethod{
	
	boolean intercepted = false;
	
	@Override
	public void markAsIntercepted() {
		intercepted = true;
	}

	@Override
	public boolean getIntercepted() {
		return intercepted;
	}
	
    public String pong() {
    	return "pong";
    	
    }
}
