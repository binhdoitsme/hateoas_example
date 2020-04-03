package org.example.util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServletLoader {

    private static final String PACKAGE_SEPARATOR = ".";
    private static final String PATH_SEPARATOR = "/";
    private static final String PARENT_DIR = "./src/main/java/";

    private static String packageToPath(String packageName) {
        return packageName.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }

    private static String fullyQualifiedClassNameFrom(String packageName, String className) {
        return packageName.concat(".").concat(className.replace(".java", ""));
    }

    private static void addServletClassToCollection(List<Class<HttpServlet>> servletClasses,
                                                    Class<?> servletClass) {
        if (!servletClass.equals(HttpServlet.class) && !servletClass.getSuperclass().equals(HttpServlet.class)) {
            return;
        }
        if (servletClass.getAnnotation(WebServlet.class) == null) {
            return;
        }
        servletClasses.add((Class<HttpServlet>) servletClass);
    }

    public static List<Class<HttpServlet>> getServletClasses(String servletPackageName)
            throws ClassNotFoundException {
        String servletPkgPath = PARENT_DIR.concat(packageToPath(servletPackageName));
        String[] servletClassFileNames = new File(servletPkgPath).list();
        List<Class<HttpServlet>> servletClasses = new ArrayList<>();
        assert servletClassFileNames != null;
        for (String servletClassName : servletClassFileNames) {
            Class<?> servletClass = Class.forName(fullyQualifiedClassNameFrom(servletPackageName, servletClassName));
            addServletClassToCollection(servletClasses, servletClass);
        }
        return servletClasses;
    }
}
