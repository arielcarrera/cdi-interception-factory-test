package com.github.arielcarrera.cdi.test.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InterceptionFactory;

import com.github.arielcarrera.cdi.test.interceptors.MyInterceptor;
import com.github.arielcarrera.cdi.test.services.Foo;
import com.github.arielcarrera.cdi.test.services.FooBean;
import com.github.arielcarrera.cdi.test.services.FooGenericsBean;
import com.github.arielcarrera.cdi.test.services.FooInterfaceGenerics;
import com.github.arielcarrera.cdi.test.services.FooInterfaceGenericsBean;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedClassLevelBean;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedClassMethodLevelBean;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceDefaultMethod;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceDefaultMethodBean;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceLevelAnnotated;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedInterfaceLevelAnnotatedBean;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedMethodLevelAnnotated;
import com.github.arielcarrera.cdi.test.services.FooAnnotatedMethodLevelAnnotatedBean;

/**
 * Producer Methods for interception tests
 * @author Ariel Carrera
 *
 */
@ApplicationScoped
public class Producer {

    @Produced("noProxy")
    @Produces
    public FooBean noProxy() {
        return new FooBean();
    }
    
    @Produced("noProxyGenerics")
    @Produces
    public FooGenericsBean<String,String> noProxyGenerics() {
        return new FooGenericsBean<String,String>();
    }
    
    @Produced("noProxyInterfaceGenerics")
    @Produces
    public FooInterfaceGenerics<String,String> noProxyInterfaceGenerics() {
        return new FooInterfaceGenericsBean<String,String>();
    }
    
    @Produced("noProxyClassLevelAnnotation")
    @Produces
    public Foo noProxyClassLevelAnnotation() {
        return new FooAnnotatedClassLevelBean();
    }
    
    @Produced("noProxyClassMethodLevelAnnotation")
    @Produces
    public Foo noProxyClassMethodLevelAnnotation() {
        return new FooAnnotatedClassMethodLevelBean();
    }
    
    @Produced("addClassLevelInterceptor")
    @Produces
    public FooBean addClassLevelInterceptor(InterceptionFactory<FooBean> interceptionFactory) {
        interceptionFactory.configure().add(MyInterceptor.Literal.INSTANCE);
        return interceptionFactory.createInterceptedInstance(new FooBean());
    }
    
    @Produced("addMethodLevelInterceptor")
    @Produces
    public FooBean addMethodLevelInterceptor(InterceptionFactory<FooBean> interceptionFactory) {
 		interceptionFactory.configure().filterMethods(p -> p.getJavaMember().getName().equals("ping")).forEach(p -> p.add(MyInterceptor.Literal.INSTANCE));
        return interceptionFactory.createInterceptedInstance(new FooBean());
    }
    
    @Produced("addClassLevelInterceptorGenerics")
    @Produces
    public FooGenericsBean<String, String> addClassLevelInterceptorGenerics(InterceptionFactory<FooGenericsBean<String, String>> interceptionFactory) {
        interceptionFactory.configure().add(MyInterceptor.Literal.INSTANCE);
        return interceptionFactory.createInterceptedInstance(new FooGenericsBean<String, String>());
    }
    
    @Produced("addClassLevelInterceptorInterfaceGenerics")
    @Produces
    public FooInterfaceGenerics<String, String> addClassLevelInterceptorInterfaceGenerics(InterceptionFactory<FooInterfaceGenerics<String, String>> interceptionFactory) {
        interceptionFactory.configure().add(MyInterceptor.Literal.INSTANCE);
        return interceptionFactory.createInterceptedInstance(new FooInterfaceGenericsBean<String, String>());
    }
    
    @Produced("classLevelInterceptor")
    @Produces
    public FooAnnotatedClassLevelBean classLevelInterceptor(InterceptionFactory<FooAnnotatedClassLevelBean> interceptionFactory) {
        return interceptionFactory.createInterceptedInstance(new FooAnnotatedClassLevelBean());
    }
    
    @Produced("classLevelMethodInterceptor")
    @Produces
    public FooAnnotatedClassMethodLevelBean classLevelMethodInterceptor(InterceptionFactory<FooAnnotatedClassMethodLevelBean> interceptionFactory) {
        return interceptionFactory.createInterceptedInstance(new FooAnnotatedClassMethodLevelBean());
    }
    
    @Produced("interfaceLevelInterceptor")
    @Produces
    public FooAnnotatedInterfaceLevelAnnotated interfaceLevelInterceptor(InterceptionFactory<FooAnnotatedInterfaceLevelAnnotated> interceptionFactory) {
        return interceptionFactory.createInterceptedInstance(new FooAnnotatedInterfaceLevelAnnotatedBean());
    }
    
    @Produced("interfaceMethodLevelInterceptor")
    @Produces
    public FooAnnotatedMethodLevelAnnotated interfaceMethodLevelInterceptor(InterceptionFactory<FooAnnotatedMethodLevelAnnotated> interceptionFactory) {
        return interceptionFactory.createInterceptedInstance(new FooAnnotatedMethodLevelAnnotatedBean());
    }
    
    @Produced("interfaceDefaultMethodInterceptor")
    @Produces
    public FooAnnotatedInterfaceDefaultMethod interfaceDefaultMethodInterceptor(InterceptionFactory<FooAnnotatedInterfaceDefaultMethod> interceptionFactory) {
        return interceptionFactory.createInterceptedInstance(new FooAnnotatedInterfaceDefaultMethodBean());
    }
    
}
