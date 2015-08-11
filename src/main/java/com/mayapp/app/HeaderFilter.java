package com.mayapp.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by aakture on 6/8/15.
 */
@Provider
@PreMatching
@Priority(Priorities.HEADER_DECORATOR)
public class HeaderFilter implements ContainerRequestFilter{

    private static final Logger LOG = LoggerFactory.getLogger(HeaderFilter.class);
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        LOG.info("-- filter --");
        System.out.println("-- filter --");
    }
}
