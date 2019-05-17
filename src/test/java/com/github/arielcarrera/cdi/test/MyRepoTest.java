package com.github.arielcarrera.cdi.test;

import static org.junit.Assert.assertNotNull;

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

import com.github.arielcarrera.cdi.test.entities.FooEntity;
import com.github.arielcarrera.cdi.test.entities.FooRepository;

/**
 * Tests for Writable Repository with soft delete operations
 * 
 * @author Ariel Carrera
 *
 */
public class MyRepoTest {

	@ClassRule
	public static JtaEnvironment jtaEnvironment = new JtaEnvironment();
	@Rule
	public WeldInitiator weld = WeldInitiator.from(new Weld()).activate(RequestScoped.class, ApplicationScoped.class)
				.inject(this).build();
	
	@Inject
	protected EntityManager entityManager;
	
	@Inject
	FooRepository repo;
	
    @Test
    public void testFooClassLevelHello() {
    	entityManager.getTransaction().begin();
    	repo.save(new FooEntity(1,1,1));
    	entityManager.getTransaction().commit();
        assertNotNull(entityManager.find(FooEntity.class, 1));
    }

}