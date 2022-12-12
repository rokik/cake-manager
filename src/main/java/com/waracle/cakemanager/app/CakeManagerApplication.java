package com.waracle.cakemanager.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = "com.waracle.cakemanager.domain")
@EnableJpaRepositories(basePackages = "com.waracle.cakemanager.repository")
@ComponentScan(basePackages = "com.waracle.cakemanager")
@EnableFeignClients(basePackages = "com.waracle.cakemanager.init")
public class CakeManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeManagerApplication.class, args);
    }

}
