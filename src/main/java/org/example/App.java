package org.example;

import javassist.*;
import lombok.SneakyThrows;
import org.example.base.di.DependencyContainer;
import org.example.base.di.Inject;
import org.example.domain.repository.Repository;
import org.example.repository.EmployeeRepository;
import org.example.servlet.EmployeeServlet;
import org.example.servlet.HelloServlet;
import org.example.util.ClassPathClassLoader;
import org.example.util.ServletLoader;
import org.example.util.TomcatBuilder;
import org.example.util.TomcatBuilderImpl;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.List;

public class App implements Runnable {

    private static final String SERVLET_PACKAGE_NAME = "org.example.servlet";

    @SneakyThrows
    @Override
    public void run() {
        List<Class<HttpServlet>> servlets = ServletLoader.getServletClasses(SERVLET_PACKAGE_NAME);
        TomcatBuilder appBuilder = new TomcatBuilderImpl();
        appBuilder.defaultConfigure()
                .registerServlets(servlets)
                .startServer();
    }

    public <T extends Startup> App useStartup(Class<T> startupClass)
            throws IllegalAccessException, InstantiationException {
        T startupInstance = startupClass.newInstance();
        startupInstance.configureDependencies();
        return this;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, CannotCompileException, NotFoundException, InstantiationException, IllegalAccessException {
//        CtClass cc = ClassPool.getDefault()
//                .get("org.example.servlet.EmployeeServlet");
//
//        CtConstructor ctConstructor = cc.getDeclaredConstructor(null);
//        System.out.println("repository = " + "(" + EmployeeRepository.class.getCanonicalName() + ")" + DependencyContainer.class.getCanonicalName() + ".locateDependency(org.example.domain.repository.Repository.class);");
//        ctConstructor.insertAfter("repository = " + "(" + EmployeeRepository.class.getCanonicalName() + ")" + DependencyContainer.class.getCanonicalName() + ".locateDependency(" + Repository.class.getCanonicalName() + ".class);");
//        cc.toClass();

        for (String clazz : ClassPathClassLoader.getClasses("org.example")) {
            System.out.println("Discovered class: " + clazz);
        }

        new App().useStartup(Startup.class)
                .run();
    }
}
