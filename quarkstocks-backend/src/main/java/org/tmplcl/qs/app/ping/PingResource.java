package org.tmplcl.qs.app.ping;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/ping")
public class PingResource {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @GET
    public Ping getPing() {
        return new Ping(atomicInteger.incrementAndGet(), "ping successful", LocalDateTime.now());
    }
}
