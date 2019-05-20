package com.github.arielcarrera.cdi.test.services;

import com.github.arielcarrera.cdi.test.interceptors.MyInterceptor;


public class FooAnnotatedClassMethodLevelBean implements Foo {
	
	boolean intercepted = false;
	
	@Override
	public void markAsIntercepted() {
		intercepted = true;
	}

	@Override
	public boolean getIntercepted() {
		return intercepted;
	}
	
	/* (non-Javadoc)
	 * @see com.github.arielcarrera.cdi.test.entities.FooTxInterface#ping()
	 */
	@MyInterceptor
	@Override
	public String ping() {
		return "ping";
	}

}