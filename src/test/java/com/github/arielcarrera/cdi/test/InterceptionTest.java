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
import com.github.arielcarrera.cdi.test.services.FooBean;
import com.github.arielcarrera.cdi.test.services.FooGenericsBean;
import com.github.arielcarrera.cdi.test.services.FooInterfaceGenerics;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedClassLevelBean;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedClassMethodLevelBean;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceDefaultMethod;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceLevelAnnotated;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedMethodLevelAnnotated;

/**
 * Tests with CDI Interceptors
 * @author Ariel Carrera
 *
 */
public class InterceptionTest {

	@ClassRule
	public static JtaEnvironment jtaEnvironment = new JtaEnvironment();
	@Rule
	public WeldInitiator weld = WeldInitiator.from(new Weld()).activate(RequestScoped.class, ApplicationScoped.class)
				.inject(this).build();
	
	
    @Produced("noProxy")
    @Inject
    FooBean noProxy;
    
    @Produced("noProxyGenerics")
    @Inject
    FooGenericsBean<String,String> noProxyGenerics;
    
    @Produced("noProxyInterfaceGenerics")
    @Inject
    FooInterfaceGenerics<String,String> noProxyInterfaceGenerics;
    
    @Produced("noProxyClassLevelAnnotation")
    @Inject
    Foo noProxyClassLevelAnnotation;
    
    @Produced("noProxyClassMethodLevelAnnotation")
    @Inject
    Foo noProxyClassMethodLevelAnnotation;
    
    @Produced("addClassLevelInterceptor")
    @Inject
    FooBean addClassLevelInterceptor;
    
    @Produced("addMethodLevelInterceptor")
    @Inject
    FooBean addMethodLevelInterceptor;
    
    @Produced("addClassLevelInterceptorGenerics")
    @Inject
    FooGenericsBean<String, String> addClassLevelInterceptorGenerics;
    
    @Produced("addClassLevelInterceptorInterfaceGenerics")
    @Inject
    FooInterfaceGenerics<String, String> addClassLevelInterceptorInterfaceGenerics;
    
    @Produced("classLevelInterceptor")
    @Inject
    FooAnnotatedClassLevelBean classLevelInterceptor;
    
    @Produced("classLevelMethodInterceptor")
    @Inject
    FooAnnotatedClassMethodLevelBean classLevelMethodInterceptor;
    
    @Produced("interfaceLevelInterceptor")
    @Inject
    FooAnnotatedInterfaceLevelAnnotated interfaceLevelInterceptor;
    
    @Produced("interfaceMethodLevelInterceptor")
    @Inject
    FooAnnotatedMethodLevelAnnotated interfaceMethodLevelInterceptor;
    
    @Produced("interfaceDefaultMethodInterceptor")
    @Inject
    FooAnnotatedInterfaceDefaultMethod interfaceDefaultMethodInterceptor;
    
    @Test
	public void noProxy() {
    	assertEquals("ping", noProxy.ping());
    	assertFalse(noProxy.getIntercepted());
	}
    @Test
	public void noProxyGenerics() {
    	assertEquals("ping->pong", noProxyGenerics.ping("ping","pong"));
    	assertFalse(noProxyGenerics.getIntercepted());
	}
    @Test
	public void noProxyInterfaceGenerics() {
    	assertEquals("ping->pong", noProxyInterfaceGenerics.ping("ping","pong"));
    	assertFalse(noProxyInterfaceGenerics.getIntercepted());
	}
    @Test
	public void noProxyClassLevelAnnotation() {
    	assertEquals("ping", noProxyClassLevelAnnotation.ping());
    	assertFalse(noProxyClassLevelAnnotation.getIntercepted());
	}
    @Test
	public void noProxyClassMethodLevelAnnotation() {
    	assertEquals("ping", noProxyClassMethodLevelAnnotation.ping());
    	assertFalse(noProxyClassMethodLevelAnnotation.getIntercepted());
	}
    @Test
	public void addClassLevelInterceptor() {
    	assertEquals("Intercepted: ping", addClassLevelInterceptor.ping());
    	assertTrue(addClassLevelInterceptor.getIntercepted());
	}
    @Test
	public void addMethodLevelInterceptor() {
    	assertEquals("Intercepted: ping", addMethodLevelInterceptor.ping());
    	assertTrue(addMethodLevelInterceptor.getIntercepted());
	}
    @Test
	public void addClassLevelInterceptorGenerics() {
    	assertEquals("Intercepted: ping->pong", addClassLevelInterceptorGenerics.ping("ping","pong"));
    	assertTrue(addClassLevelInterceptorGenerics.getIntercepted());
	}
    @Test
	public void addClassLevelInterceptorInterfaceGenerics() {
    	assertEquals("Intercepted: ping->pong", addClassLevelInterceptorInterfaceGenerics.ping("ping","pong"));
    	assertTrue(addClassLevelInterceptorInterfaceGenerics.getIntercepted());
	}
    @Test
	public void classLevelInterceptor() {
    	assertEquals("Intercepted: ping", classLevelInterceptor.ping());
    	assertTrue(classLevelInterceptor.getIntercepted());
	}
    @Test
	public void classLevelMethodInterceptor() {
    	assertEquals("Intercepted: ping", classLevelMethodInterceptor.ping());
    	assertTrue(classLevelMethodInterceptor.getIntercepted());
	}
    @Test
	public void interfaceLevelInterceptor() {
    	assertEquals("Intercepted: ping", interfaceLevelInterceptor.ping());
    	assertTrue(interfaceLevelInterceptor.getIntercepted());
	}
    @Test
	public void interfaceMethodLevelInterceptor() {
    	assertEquals("Intercepted: ping", interfaceMethodLevelInterceptor.ping());
    	assertTrue(interfaceMethodLevelInterceptor.getIntercepted());
	}
    @Test
	public void interfaceDefaultMethodInterceptor() {
    	assertEquals("Intercepted: ping", interfaceDefaultMethodInterceptor.ping());
    	assertTrue(interfaceDefaultMethodInterceptor.getIntercepted());
	}
    

}