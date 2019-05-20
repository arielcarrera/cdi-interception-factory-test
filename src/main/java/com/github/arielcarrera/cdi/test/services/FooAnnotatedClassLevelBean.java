package com.github.arielcarrera.cdi.test.services;

import com.github.arielcarrera.cdi.test.interceptors.MyInterceptor;

@MyInterceptor
public class FooAnnotatedClassLevelBean implements Foo {
	
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
	@Override
	public String ping() {
		return "ping";
	}

}