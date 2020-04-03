package org.example;

import lombok.SneakyThrows;
import org.example.base.di.DependencyContainer;
import org.example.base.di.DependencyInjectorBuilder;
import org.example.domain.model.Employee;
import org.example.domain.repository.Repository;
import org.example.repository.EmployeeRepository;
import org.example.util.DependencyLoader;

public class Startup {

    private DependencyContainer container;

    @SneakyThrows
    public void configureDependencies() {
        container = DependencyInjectorBuilder.createBuilder()
                        .addDependency((Repository.class), new EmployeeRepository())
                        .build();
        DependencyLoader.loadDependencies();
        System.out.println("type: " + Repository.class + "\nimplementation: " + DependencyContainer.locateDependency(Repository.class));
    }

}
