package org.example.base.di;

import org.example.exception.DependencyNotInjectedException;

import java.util.HashMap;
import java.util.Map;

public class DependencyContainer {

    private static final Map<Class<?>, Object> dependencyContainer = new HashMap<>();

    DependencyContainer() { }

    public void addDependency(Class<?> dependencyType, Object implementation) {
        this.dependencyContainer.put(dependencyType, implementation);
    }

    public static <T> T locateDependency(Class<T> dependencyClass) {
        T dependencyResult = (T) dependencyContainer.get(dependencyClass);
        if (dependencyResult == null) {
            throw new DependencyNotInjectedException();
        }
        return dependencyResult;
    }
}
