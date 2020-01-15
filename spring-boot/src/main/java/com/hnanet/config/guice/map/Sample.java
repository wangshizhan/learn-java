package com.hnanet.config.guice.map;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.multibindings.MapBinder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

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
        MapBinder<String, IHelloPrinter> printers = MapBinder.newMapBinder(binder(), String.class, IHelloPrinter.class);
        printers.addBinding("simple").to(SimpleHelloPrinter.class);
        printers.addBinding("complex").to(ComplexHelloPrinter.class);
    }

}

@Singleton
public class Sample {

    @Inject
    private Map<String, IHelloPrinter> printers;

    public void hello() {
        for (String name : printers.keySet()) {
            printers.get(name).print();
        }
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SampleModule());
        Sample sample = injector.getInstance(Sample.class);
        sample.hello();
    }

}
