package org.volcanix;

import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/hello-metrics")
public class MetricResource {

    private static final Logger LOG = Logger.getLogger(MetricResource.class);

    private final LongCounter counter;

    public MetricResource(Meter meter) {
        counter = meter.counterBuilder("hello-metrics")
                .setDescription("hello-metrics")
                .setUnit("invocations")
                .build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        counter.add(1);
        LOG.info("hello-metrics");
        return "hello-metrics";
    }
}