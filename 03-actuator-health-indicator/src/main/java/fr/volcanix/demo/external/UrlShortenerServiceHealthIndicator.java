package fr.volcanix.demo.external;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class UrlShortenerServiceHealthIndicator implements HealthIndicator, HealthContributor {

    private static final String URL = "https://cleanuri.com/api/v1/shorten";

    @Override
    public Health health() {
        // check if url shortener service url is reachable
        try (Socket socket = new Socket(new java.net.URL(URL).getHost(),80)) {
        } catch (Exception e1) {
            return Health.down().withDetail("error", e1.getMessage()).build();
        }
        return Health.up().build();
    }
}