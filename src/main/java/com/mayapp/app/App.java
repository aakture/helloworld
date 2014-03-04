
package com.mayapp.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
            Server server = new Server(8112);
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);
            ServletHolder h = new ServletHolder(new ServletContainer());
            h.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "com.myapp.resources");
            h.setInitOrder(1);
            context.addServlet(h, "/*");
        server.setDumpAfterStart(true);
            try
            {
                server.start();
            server.start();
        }catch(Exception ex) {
           ex.printStackTrace();
        }
    }

}
