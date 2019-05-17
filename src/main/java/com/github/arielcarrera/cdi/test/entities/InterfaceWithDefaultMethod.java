package com.github.arielcarrera.cdi.test.entities;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author <a href="mailto:manovotn@redhat.com">Matej Novotny</a>
 */
@Vetoed
public interface InterfaceWithDefaultMethod {

    default String ping() {
        return InterfaceWithDefaultMethod.class.getSimpleName();
    }

    String pong();
}
