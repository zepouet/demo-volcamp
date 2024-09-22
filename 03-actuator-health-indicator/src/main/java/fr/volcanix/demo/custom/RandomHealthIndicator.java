package fr.volcanix.demo.custom;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomHealthIndicator implements HealthIndicator {

    public Health health() {
        double chance = ThreadLocalRandom.current().nextDouble();
        Health.Builder status = Health.up();
        if (chance > 0.9) {
            status = Health.down();
        }

        return status
                .withDetail("chance", chance)
                .withDetail("strategy", "thread-local")
                .build();
    }
}