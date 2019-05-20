package com.github.arielcarrera.cdi.test.config;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InterceptionFactory;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;

import com.github.arielcarrera.cdi.test.interceptors.MyInterceptor;
import com.github.arielcarrera.cdi.test.services.Foo;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceDefaultMethod;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceDefaultMethodBean;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceLevelAnnotated;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceLevelAnnotatedBean;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedMethodLevelAnnotated;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedMethodLevelAnnotatedBean;
import com.github.arielcarrera.cdi.test.services.FooBean;
import com.github.arielcarrera.cdi.test.services.FooDefaultMethod;
import com.github.arielcarrera.cdi.test.services.FooDefaultMethodBean;
import com.github.arielcarrera.cdi.test.services.complex.ComplexFooA;
import com.github.arielcarrera.cdi.test.services.complex.ComplexFooABean;
import com.github.arielcarrera.cdi.test.services.complex.ComplexFooAnnotatedA;
import com.github.arielcarrera.cdi.test.services.complex.ComplexFooAnnotatedABean;
import com.github.arielcarrera.cdi.test.services.complex.ComplexFooAnnotatedC;
import com.github.arielcarrera.cdi.test.services.complex.ComplexFooC;

/**
 * Producer methods for dynamic proxies
 * @author Ariel Carrera
 *
 */
@ApplicationScoped
public class DynamicProxyProducer {

    @Produced("dynamicProxyInterface")
    @Produces
    public FooAnnotatedInterfaceLevelAnnotated dynamicProxyInterfaceLevel(InterceptionFactory<FooAnnotatedInterfaceLevelAnnotated> interceptionFactory) {
    	// Create AOP proxy
 		ProxyFactory result = new ProxyFactory();
 		result.setTarget((FooAnnotatedInterfaceLevelAnnotated) new FooAnnotatedInterfaceLevelAnnotatedBean());
 		result.setInterfaces(FooAnnotatedInterfaceLevelAnnotated.class);

 		FooAnnotatedInterfaceLevelAnnotated instance = (FooAnnotatedInterfaceLevelAnnotated) result.getProxy(DynamicProxyProducer.class.getClassLoader());
     		
        return interceptionFactory.createInterceptedInstance(instance);
    }
    
    @Produced("dynamicProxyInterfaceMethod")
    @Produces
    public FooAnnotatedMethodLevelAnnotated dynamicProxyInterfaceMethodLevel(InterceptionFactory<FooAnnotatedMethodLevelAnnotated> interceptionFactory) {
    	// Create AOP proxy
 		ProxyFactory result = new ProxyFactory();
 		result.setTarget((FooAnnotatedMethodLevelAnnotated) new FooAnnotatedMethodLevelAnnotatedBean());
 		result.setInterfaces(FooAnnotatedMethodLevelAnnotated.class);

 		FooAnnotatedMethodLevelAnnotated instance = (FooAnnotatedMethodLevelAnnotated) result.getProxy(DynamicProxyProducer.class.getClassLoader());
     		
        return interceptionFactory.createInterceptedInstance(instance);
    }
    
    @Produced("dynamicProxyInterfaceDefaultMethod")
    @Produces
    public FooAnnotatedInterfaceDefaultMethod dynamicProxyInterfaceDefaultMethod(InterceptionFactory<FooAnnotatedInterfaceDefaultMethod> interceptionFactory) {
    	// Create AOP proxy
 		ProxyFactory result = new ProxyFactory();
 		result.setTarget((FooAnnotatedInterfaceDefaultMethod) new FooAnnotatedInterfaceDefaultMethodBean());
 		result.setInterfaces(FooAnnotatedInterfaceDefaultMethod.class);

 		FooAnnotatedInterfaceDefaultMethod instance = (FooAnnotatedInterfaceDefaultMethod) result.getProxy(DynamicProxyProducer.class.getClassLoader());
     		
        return interceptionFactory.createInterceptedInstance(instance);
    }
    
    @Produced("addMyAnnotationDynamicProxyInterface")
    @Produces
    public Foo addMyAnnotationDynamicProxyInterface(InterceptionFactory<Foo> interceptionFactory) {
    	// Create AOP proxy
 		ProxyFactory result = new ProxyFactory();
 		result.setTarget((Foo) new FooBean());
 		result.setInterfaces(Foo.class);

 		Foo instance = (Foo) result.getProxy(DynamicProxyProducer.class.getClassLoader());
 		interceptionFactory.configure().add(MyInterceptor.Literal.INSTANCE);
        return interceptionFactory.createInterceptedInstance(instance);
    }
    
    @Produced("addMyAnnotationDynamicProxyInterfaceMethod")
    @Produces
    public Foo addMyAnnotationDynamicProxyInterfaceMethod(InterceptionFactory<Foo> interceptionFactory) {
    	// Create AOP proxy
 		ProxyFactory result = new ProxyFactory();
 		result.setTarget((Foo) new FooBean());
 		result.setInterfaces(Foo.class);

 		Foo instance = (Foo) result.getProxy(DynamicProxyProducer.class.getClassLoader());
 		interceptionFactory.configure().filterMethods(p -> p.getJavaMember().getName().equals("ping")).forEach(p -> p.add(MyInterceptor.Literal.INSTANCE));
        return interceptionFactory.createInterceptedInstance(instance);
    }
    
    @Produced("addMyAnnotationDynamicProxyInterfaceDefaultMethod")
    @Produces
    public FooDefaultMethod addMyAnnotationDynamicProxyInterfaceDefaultMethod(InterceptionFactory<FooDefaultMethod> interceptionFactory) {
    	// Create AOP proxy
 		ProxyFactory result = new ProxyFactory();
 		result.setTarget((FooDefaultMethod) new FooDefaultMethodBean());
 		result.setInterfaces(FooDefaultMethod.class);

 		FooDefaultMethod instance = (FooDefaultMethod) result.getProxy(DynamicProxyProducer.class.getClassLoader());
 		interceptionFactory.configure().filterMethods(p -> p.getJavaMember().getName().equals("ping")).forEach(p -> p.add(MyInterceptor.Literal.INSTANCE));
        return interceptionFactory.createInterceptedInstance(instance);
    }
    
    @Produced("complexDynamicProxy")
    @Produces
    public ComplexFooC complexDynamicProxy(InterceptionFactory<ComplexFooC> interceptionFactory) {
    	// Create AOP proxy
 		ProxyFactory result = new ProxyFactory();
 		result.setTarget((ComplexFooA) new ComplexFooABean());
 		result.setInterfaces(ComplexFooC.class);
 		result.addAdvisor(new Advisor() {
			@Override
			public boolean isPerInstance() {
				return false;
			}
			
			@Override
			public Advice getAdvice() {
				
				return new MyAdviseInterceptor();
			}
		});
 		ComplexFooC instance = (ComplexFooC) result.getProxy(DynamicProxyProducer.class.getClassLoader());
        return interceptionFactory.createInterceptedInstance(instance);
    }
    
    @Produced("complexDynamicProxyAnnotated")
    @Produces
    public ComplexFooAnnotatedC complexDynamicProxyAnnotated(InterceptionFactory<ComplexFooAnnotatedC> interceptionFactory) {
    	// Create AOP proxy
 		ProxyFactory result = new ProxyFactory();
 		result.setTarget((ComplexFooAnnotatedA) new ComplexFooAnnotatedABean());
 		result.setInterfaces(ComplexFooAnnotatedC.class);
 		result.addAdvisor(new Advisor() {
			@Override
			public boolean isPerInstance() {
				return false;
			}
			
			@Override
			public Advice getAdvice() {
				
				return new MyAdviseInterceptor();
			}
		});
 		ComplexFooAnnotatedC instance = (ComplexFooAnnotatedC) result.getProxy(DynamicProxyProducer.class.getClassLoader());
 		interceptionFactory.configure().filterMethods(p -> p.getJavaMember().getName().startsWith("p")).forEach(p -> p.add(MyInterceptor.Literal.INSTANCE));
        return interceptionFactory.createInterceptedInstance(instance);
    }
    
    @Produced("complexDynamicProxyAddMethodAnnotations")
    @Produces
    public ComplexFooC complexDynamicProxyAddMethodAnnotations(InterceptionFactory<ComplexFooC> interceptionFactory) {
    	// Create AOP proxy
 		ProxyFactory result = new ProxyFactory();
 		result.setTarget((ComplexFooA) new ComplexFooABean());
 		result.setInterfaces(ComplexFooC.class);
 		result.addAdvisor(new Advisor() {
			@Override
			public boolean isPerInstance() {
				return false;
			}
			
			@Override
			public Advice getAdvice() {
				
				return new MyAdviseInterceptor();
			}
		});
 		ComplexFooC instance = (ComplexFooC) result.getProxy(DynamicProxyProducer.class.getClassLoader());
 		interceptionFactory.configure().filterMethods(p -> p.getJavaMember().getName().startsWith("p")).forEach(p -> p.add(MyInterceptor.Literal.INSTANCE));
        return interceptionFactory.createInterceptedInstance(instance);
    }
    
    private static class MyAdviseInterceptor implements Advice, MethodInterceptor, Serializable {

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable{
			try {
				return invocation.proceed();
			} catch (Throwable e) {
				if (invocation.getMethod().getReturnType().equals(String.class)) {
					return "ok";
				} else {
					return false;
				}
			}
		}
		
	}
    
}
