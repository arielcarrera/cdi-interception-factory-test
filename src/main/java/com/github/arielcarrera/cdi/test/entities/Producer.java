package com.github.arielcarrera.cdi.test.entities;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InterceptionFactory;
import javax.enterprise.util.AnnotationLiteral;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.aop.framework.ProxyFactory;

@ApplicationScoped
public class Producer {

    @Produced("noProxy")
    @ApplicationScoped
    @Produces
    public FooTx noProxy() {
        return new FooTx();
    }
    
    @Produced("noProxyChildAnnotations")
    @ApplicationScoped
    @Produces
    public FooTxInterface noProxyChildAnnotations() {
        return new FooTx();
    }
    
    @Produced("classLevelHelloInterceptor")
    @ApplicationScoped
    @Produces
    public Foo helloInterceptor(InterceptionFactory<Foo> interceptionFactory) {
        interceptionFactory.configure().add(Hello.Literal.INSTANCE);
        return interceptionFactory.createInterceptedInstance(new Foo());
    }
    
    @Produced("classLevelTxRequired")
    @ApplicationScoped
    @Produces
    public Foo txRequired(InterceptionFactory<Foo> interceptionFactory) {
        interceptionFactory.configure().add(new TransactionalAnnotation(TxType.REQUIRED));
        return interceptionFactory.createInterceptedInstance(new Foo());
    }
    
    @Produced("classLevelTxRequiresNew")
    @ApplicationScoped
    @Produces
    public Foo txRequiresNew(InterceptionFactory<Foo> interceptionFactory) {
        interceptionFactory.configure().add(new TransactionalAnnotation(TxType.REQUIRES_NEW));
        return interceptionFactory.createInterceptedInstance(new Foo());
    }
    
    @Produced("classLevelTxRequiredGenerics")
    @ApplicationScoped
    @Produces
    public FooGenerics<String, String> txRequiredGenerics(InterceptionFactory<FooGenerics<String, String>> interceptionFactory) {
        interceptionFactory.configure().add(new TransactionalAnnotation(TxType.REQUIRED));
        return interceptionFactory.createInterceptedInstance(new FooGenerics<String, String>());
    }
    
    @Produced("classLevelChildAnnotations")
    @ApplicationScoped
    @Produces
    public FooTx childAnnotations(InterceptionFactory<FooTx> interceptionFactory) {
        interceptionFactory.configure();
        return interceptionFactory.createInterceptedInstance(new FooTx());
    }
    
    @Produced("classLevelChildAnnotationsSpringProxy")
    @ApplicationScoped
    @Produces
    public FooTxInterface childAnnotationsSpringProxy(InterceptionFactory<FooTxInterface> interceptionFactory) {
        interceptionFactory.configure();
        
        	// Create proxy
     		ProxyFactory result = new ProxyFactory();
     		result.setTarget((FooTxInterface)new FooTx());
     		result.setInterfaces(FooTxInterface.class);

     		FooTxInterface repository = (FooTxInterface) result.getProxy(Producer.class.getClassLoader());

     		
        return interceptionFactory.createInterceptedInstance(repository);
    }
    
    @Produced("classLevelTxRequiredSpringProxy")
    @ApplicationScoped
    @Produces
    public FooTxInterface txRequiredSpringProxy(InterceptionFactory<FooTxInterface> interceptionFactory) {
    	interceptionFactory.configure().add(new TransactionalAnnotation(TxType.REQUIRED));
        
        	// Create proxy
     		ProxyFactory result = new ProxyFactory();
     		result.setTarget((FooTxInterface)new FooTx());
     		result.setInterfaces(FooTxInterface.class);

     		FooTxInterface repository = (FooTxInterface) result.getProxy(Producer.class.getClassLoader());

     		
        return interceptionFactory.createInterceptedInstance(repository);
    }
    
    public static class TransactionalAnnotation extends AnnotationLiteral<Transactional> implements Transactional {
		private static final long serialVersionUID = 1L;

		TxType value = TxType.REQUIRED;
    	public TransactionalAnnotation(TxType value) {
			super();
			if (value != null) {
				this.value = value;
			}
		}

		public TxType value() {
    		return value;
    	}

    	public Class[] rollbackOn() {
    		return new Class[]{};
    	}

    	public Class[] dontRollbackOn() {
    		return new Class[]{};
    	}
    }
    
    
}
