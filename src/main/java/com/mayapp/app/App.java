package com.mayapp.app;

import com.myapp.resources.HelloWorldResource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * Hello world! URL  http://localhost:8112/hello
 *
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {

        Server server = new Server(8112);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(new HeaderFilter());
        resourceConfig.register(new HelloWorldResource());
        server.setHandler(context);
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        //servletContainer.getConfiguration().register(new HeaderFilter());
        ServletHolder h = new ServletHolder(servletContainer);
        h.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "com.myapp.resources");
        h.setInitOrder(1);
        context.addServlet(h, "/*");
//        context.addDecorator(new ServletContextHandler.Decorator() {
//            @Override
//            public <T> T decorate(T t) {
//                LOG.info("decorate");
//                return null;
//            }
//
//            @Override
//            public void destroy(Object o) {
//
//            }
//        });
        server.setDumpAfterStart(true);
        try  {
            server.start();
        }catch(Exception ex) {
           ex.printStackTrace();
        }
    }
}
