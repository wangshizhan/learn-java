package com.hnanet.config.guice.set;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.multibindings.Multibinder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

interface IHelloPrinter {
    void print();
}

@Singleton
class SimpleHelloPrinter implements IHelloPrinter {

    @Override
    public void print() {
        System.out.println("Hello, Simple World");
    }

}

@Singleton
class ComplexHelloPrinter implements IHelloPrinter {

    @Override
    public void print() {
        System.out.println("Hello, Complex World");
    }

}

class SampleModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<IHelloPrinter> printers = Multibinder.newSetBinder(binder(), IHelloPrinter.class);
        printers.addBinding().to(SimpleHelloPrinter.class);
        printers.addBinding().to(ComplexHelloPrinter.class);
    }

}

@Singleton
public class Sample {

    @Inject
    private Set<IHelloPrinter> printers;

    public void hello() {
        for (IHelloPrinter printer : printers) {
            printer.print();
        }
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SampleModule());
        Sample sample = injector.getInstance(Sample.class);
        sample.hello();
    }

}
