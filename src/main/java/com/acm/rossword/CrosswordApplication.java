package com.acm.rossword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.acm.rossword"})
@EnableJpaRepositories
@EnableAutoConfiguration
public class CrosswordApplication {

    public static void main(String[] args) {
//        SpringApplication.run(CrosswordApplication.class, args);

        SpringApplicationBuilder builder = new SpringApplicationBuilder(CrosswordApplication.class);

        builder.headless(false);

        ConfigurableApplicationContext context = builder.run(args);
     }
}
