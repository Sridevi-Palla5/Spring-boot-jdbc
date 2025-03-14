package com.infomerica.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class QueryLoader {

    private final Properties queries = new Properties();

    public QueryLoader(@Value("classpath:queries.properties") org.springframework.core.io.Resource resource) {
        try (var inputStream = resource.getInputStream()) {
            queries.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load queries from file", e);
        }
    }

    public String getQuery(String key) {
        return queries.getProperty(key);
    }
}
