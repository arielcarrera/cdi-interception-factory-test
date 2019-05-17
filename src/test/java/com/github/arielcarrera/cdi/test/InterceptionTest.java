package com.github.arielcarrera.cdi.test;

import static org.junit.Assert.assertEquals;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.demos.jpacditesting.support.JtaEnvironment;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import com.github.arielcarrera.cdi.test.entities.Foo;
import com.github.arielcarrera.cdi.test.entities.FooGenerics;
import com.github.arielcarrera.cdi.test.entities.FooTx;
import com.github.arielcarrera.cdi.test.entities.FooTxInterface;
import com.github.arielcarrera.cdi.test.entities.Produced;

/**
 * Tests for Writable Repository with soft delete operations
 * 
 * @author Ariel Carrera
 *
 */
public class InterceptionTest {

	@ClassRule
	public static JtaEnvironment jtaEnvironment = new JtaEnvironment();
	@Rule
	public WeldInitiator weld = WeldInitiator.from(new Weld()).activate(RequestScoped.class, ApplicationScoped.class)
				.inject(this).build();
	
	@Inject
	protected EntityManager entityManager;
	
	@Inject
	@Produced("classLevelHelloInterceptor")
	Foo fooClassLevelHello;
	
	@Inject
	@Produced("classLevelTxRequired")
	Foo fooClassLevelTxRequired;
	
	@Inject
	@Produced("classLevelTxRequiresNew")
	Foo fooClassLevelTxRequiresNew;
	
	@Inject
	@Produced("classLevelTxRequiredGenerics")
	FooGenerics<String, String> fooClassLevelTxRequiredGenerics;
	
	@Inject
	@Produced("classLevelChildAnnotations")
	FooTx fooClassLevelChildAnnotations;
	
	@Inject
	@Produced("classLevelChildAnnotationsSpringProxy")
	FooTxInterface fooClassLevelChildAnnotationsSpringProxy;
	
	
	@Inject
	@Produced("classLevelTxRequiredSpringProxy")
	FooTxInterface fooClassLevelTxRequiredSpringProxy;
	
	@Inject
	@Produced("noProxy")
	FooTx fooNoProxy;
	
	
    @Test
    public void testFooClassLevelHello() {
        assertEquals("Hello pong", fooClassLevelHello.ping());
    }
    
    @Test
    public void testFooClassLevelTxRequired() { //ejemplo anotandole como Transactional
        assertEquals("pong", fooClassLevelTxRequired.ping());
    }
    
    @Test
    public void testFooClassLevelTxRequiresNew() { //ejemplo anotandole como TransactionalRequiresNew
        assertEquals("pong", fooClassLevelTxRequiresNew.ping());
    }
    
    @Test
    public void testFooClassLevelTxRequiredGenerics() { //ejemplo anotandole como TransactionalRequiredGenerics
        assertEquals("holapongmundo", fooClassLevelTxRequiredGenerics.ping("hola", "mundo"));
    }
    
    @Test
    public void testFooClassLevelChildAnnotations() { //ejemplo anotandole como ChildAnnotations
        assertEquals("pong", fooClassLevelChildAnnotations.ping());
    }
    
    @Test
    public void testFooClassLevelChildAnnotationsSpringProxy() { //ejemplo anotandole como ChildAnnotationsSpringProxy
        assertEquals("pong", fooClassLevelChildAnnotationsSpringProxy.ping());
    }
    
    @Test
    public void testFooClassLevelTxRequiredSpringProxy() { //ejemplo anotandole como ChildAnnotationsSpringProxy
        assertEquals("pong", fooClassLevelTxRequiredSpringProxy.ping());
    }
    
    @Test
    public void testFooNoProxy() { //ejemplo sin proxy
        assertEquals("pong", fooNoProxy.ping());
    }

}