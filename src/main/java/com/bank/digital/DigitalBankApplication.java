package com.bank.digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;

import java.time.Duration;
import java.util.function.Supplier;

@SpringBootApplication
@EnableConfigurationProperties
@EnableRetry
@EnableAsync
@ComponentScan(basePackages = {"com.bank.digital"})
public class DigitalBankApplication {
    private static final Logger logger = LoggerFactory.getLogger(DigitalBankApplication.class);

    @Value("${core.banking.base-url}")
    private String coreBankingBaseUrl;

    @Value("${resilience4j.retry.max-attempts}")
    private int maxRetryAttempts;

    @Value("${resilience4j.circuitbreaker.failure-rate-threshold}")
    private float failureRateThreshold;

    @Value("${resilience4j.circuitbreaker.wait-duration-in-open-state}")
    private long waitDurationInOpenState;

    public static void main(String[] args) {
        try {
            var context = SpringApplication.run(DigitalBankApplication.class, args);
            logger.info("Digital Bank Application started successfully");
            context.getBean(DigitalBankApplication.class).validateConfiguration();
        } catch (Exception e) {
            logger.error("Failed to start Digital Bank Application", e);
            throw e;
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(failureRateThreshold)
                .waitDurationInOpenState(Duration.ofMillis(waitDurationInOpenState))
                .build();
        return CircuitBreakerRegistry.of(circuitBreakerConfig);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(maxRetryAttempts)
                .waitDuration(Duration.ofSeconds(1))
                .build();
        return RetryRegistry.of(retryConfig);
    }

    private void validateConfiguration() {
        if (coreBankingBaseUrl == null || coreBankingBaseUrl.isBlank()) {
            throw new IllegalStateException("core.banking.base-url must be configured");
        }
        if (maxRetryAttempts <= 0) {
            throw new IllegalStateException("resilience4j.retry.max-attempts must be positive");
        }
        if (failureRateThreshold <= 0 || failureRateThreshold > 100) {
            throw new IllegalStateException("resilience4j.circuitbreaker.failure-rate-threshold must be between 0 and 100");
        }
        if (waitDurationInOpenState <= 0) {
            throw new IllegalStateException("resilience4j.circuitbreaker.wait-duration-in-open-state must be positive");
        }
        logger.info("Configuration validation passed");
    }

    @Bean
    public Supplier<String> coreBankingHealthCheck() {
        return () -> {
            try {
                RestTemplate restTemplate = restTemplate();
                String healthUrl = coreBankingBaseUrl + "/health";
                String response = restTemplate.getForObject(healthUrl, String.class);
                logger.debug("Core banking health check response: {}", response);
                return response != null && response.contains("UP") ? "UP" : "DOWN";
            } catch (Exception e) {
                logger.error("Core banking health check failed", e);
                return "DOWN";
            }
        };
    }
}