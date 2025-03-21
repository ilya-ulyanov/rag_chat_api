package com.capgemini.demos.rag.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("opensearch")
public class OpensearchProperties {
    private String url;
    private String index;
}
