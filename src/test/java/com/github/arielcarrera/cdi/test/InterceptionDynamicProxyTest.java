package com.github.arielcarrera.cdi.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.demos.jpacditesting.support.JtaEnvironment;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import com.github.arielcarrera.cdi.test.config.Produced;
import com.github.arielcarrera.cdi.test.services.Foo;
import com.github.arielcarrera.cdi.test.services.FooDefaultMethod;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceDefaultMethod;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceLevelAnnotated;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedMethodLevelAnnotated;
import com.github.arielcarrera.cdi.test.services.complex.ComplexFooAnnotatedC;
import com.github.arielcarrera.cdi.test.services.complex.ComplexFooC;

/**
 * Tests with CDI Interceptors wrapping dynamic proxies
 * @author Ariel Carrera
 *
 */
public class InterceptionDynamicProxyTest {

	@ClassRule
	public static JtaEnvironment jtaEnvironment = new JtaEnvironment();
	@Rule
	public WeldInitiator weld = WeldInitiator.from(new Weld()).activate(RequestScoped.class, ApplicationScoped.class)
				.inject(this).build();
	
	
	@Produced("dynamicProxyInterface")
    @Inject
    FooAnnotatedInterfaceLevelAnnotated dynamicProxyInterfaceLevel;
    
    @Produced("dynamicProxyInterfaceMethod")
    @Inject
    FooAnnotatedMethodLevelAnnotated dynamicProxyInterfaceMethodLevel;
    
    @Produced("dynamicProxyInterfaceDefaultMethod")
    @Inject
    FooAnnotatedInterfaceDefaultMethod dynamicProxyInterfaceDefaultMethod;
    
    @Produced("addMyAnnotationDynamicProxyInterface")
    @Inject
    Foo addMyAnnotationDynamicProxyInterface;
    
    @Produced("addMyAnnotationDynamicProxyInterfaceMethod")
    @Inject
    Foo addMyAnnotationDynamicProxyInterfaceMethod;
    
    @Produced("addMyAnnotationDynamicProxyInterfaceDefaultMethod")
    @Inject
    FooDefaultMethod addMyAnnotationDynamicProxyInterfaceDefaultMethod;
	
    @Produced("complexDynamicProxy")
    @Inject
    ComplexFooC complexDynamicProxy;
    
    @Produced("complexDynamicProxyAnnotated")
    @Inject
    ComplexFooAnnotatedC complexDynamicProxyAnnotated;
    
    @Produced("complexDynamicProxyAddMethodAnnotations")
    @Inject
    ComplexFooC complexDynamicProxyAddMethodAnnotations;
    
    @Test
	public void dynamicProxyInterfaceLevel() {
    	assertEquals("Intercepted: ping", dynamicProxyInterfaceLevel.ping());
    	assertTrue(dynamicProxyInterfaceLevel.getIntercepted());
	}
    @Test
	public void dynamicProxyInterfaceMethodLevel() {
    	assertEquals("Intercepted: ping", dynamicProxyInterfaceMethodLevel.ping());
    	assertTrue(dynamicProxyInterfaceMethodLevel.getIntercepted());
	}
    @Test
	public void dynamicProxyInterfaceDefaultMethod() {
    	assertEquals("Intercepted: ping", dynamicProxyInterfaceDefaultMethod.ping());
    	assertTrue(dynamicProxyInterfaceDefaultMethod.getIntercepted());
	}
    @Test
	public void addMyAnnotationDynamicProxyInterface() {
    	assertEquals("Intercepted: ping", addMyAnnotationDynamicProxyInterface.ping());
    	assertTrue(addMyAnnotationDynamicProxyInterface.getIntercepted());
	}
    @Test
	public void addMyAnnotationDynamicProxyInterfaceMethod() {
    	assertEquals("Intercepted: ping", addMyAnnotationDynamicProxyInterfaceMethod.ping());
    	assertTrue(addMyAnnotationDynamicProxyInterfaceMethod.getIntercepted());
	}
    @Test
	public void addMyAnnotationDynamicProxyInterfaceDefaultMethod() {
    	assertEquals("Intercepted: ping", addMyAnnotationDynamicProxyInterfaceDefaultMethod.ping());
    	assertTrue(addMyAnnotationDynamicProxyInterfaceDefaultMethod.getIntercepted());
	}
   
    @Test
	public void complexDynamicProxy() {
    	assertEquals("ping", complexDynamicProxy.ping());
    	assertEquals("ok", complexDynamicProxy.pong());
    	assertEquals("ok", complexDynamicProxy.pingpong());
    	assertFalse(complexDynamicProxy.getIntercepted());
	}
    
    @Test
	public void complexDynamicProxyAnnotated() {
    	assertEquals("Intercepted: ping", complexDynamicProxyAnnotated.ping());
    	assertEquals("Intercepted: ok", complexDynamicProxyAnnotated.pong());
    	assertEquals("Intercepted: ok", complexDynamicProxyAnnotated.pingpong());
    	assertTrue(complexDynamicProxyAnnotated.getIntercepted());
	}
    
    @Test
	public void complexDynamicProxyAddMethodAnnotations() {
    	assertEquals("Intercepted: ping", complexDynamicProxyAddMethodAnnotations.ping());
    	assertEquals("Intercepted: ok", complexDynamicProxyAddMethodAnnotations.pong());
    	assertEquals("Intercepted: ok", complexDynamicProxyAddMethodAnnotations.pingpong());
    	assertTrue(complexDynamicProxyAddMethodAnnotations.getIntercepted());
	}

}