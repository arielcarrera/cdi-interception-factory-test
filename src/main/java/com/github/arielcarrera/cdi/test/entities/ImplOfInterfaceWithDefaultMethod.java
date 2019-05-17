package com.github.arielcarrera.cdi.test.entities;
import javax.enterprise.inject.Vetoed;

/**
 *
 * @author <a href="mailto:manovotn@redhat.com">Matej Novotny</a>
 */
@Vetoed
public class ImplOfInterfaceWithDefaultMethod implements InterfaceWithDefaultMethod {

    @Override
    public String pong() {
        return ImplOfInterfaceWithDefaultMethod.class.getSimpleName();
    }

}