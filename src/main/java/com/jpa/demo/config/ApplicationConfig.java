package com.jpa.demo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.jpa.demo")
@EnableAutoConfiguration
@EntityScan("com.jpa.demo.dto")
public class ApplicationConfig {
}
