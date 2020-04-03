package org.example.base.di;

import java.util.HashMap;
import java.util.Map;

public class DependencyInjectorBuilder {

    private DependencyContainer dependencyContainer;

    private DependencyInjectorBuilder() {
        dependencyContainer = new DependencyContainer();
    }

    /**
     * Add a lifetime dependency to this list of dependency
     *
     * @param implementation the object that will lives for the whole lifecycle
     * @param <TDependency>  the dependency type
     * @param <TImpl>        the implementation type
     * @return {@link #this}
     */
    public <TDependency, @Dependency TImpl extends TDependency>
        DependencyInjectorBuilder addDependency(Class<TDependency> dependencyClass, TImpl implementation) {
        this.dependencyContainer.addDependency(dependencyClass, implementation);
        return this;
    }

    public DependencyContainer build() {
        return this.dependencyContainer;
    }

    public static DependencyInjectorBuilder createBuilder() {
        return new DependencyInjectorBuilder();
    }
}
