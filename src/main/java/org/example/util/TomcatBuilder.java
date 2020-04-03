package org.example.util;

import javax.servlet.http.HttpServlet;
import java.util.List;

public interface TomcatBuilder {
    TomcatBuilder defaultConfigure();
    TomcatBuilder registerServlets(List<Class<HttpServlet>> servlets);
    void startServer();
}
