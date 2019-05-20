package com.github.arielcarrera.cdi.test.services;

public interface FooDefaultMethod extends InterceptionStatus {

    default String ping() {
        return "ping";
    }

}