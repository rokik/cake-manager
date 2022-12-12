package com.waracle.cakemanager.init;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager.contract.Cake;
import com.waracle.cakemanager.service.api.CakeManagerService;
import io.micrometer.core.instrument.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Component
public class InitInMemoryDatabaseRunner implements ApplicationRunner {
    private static Logger logger = LoggerFactory.getLogger(InitInMemoryDatabaseRunner.class);
    private final CakeManagerService cakeManagerService;
    private final String baseUrl;
    private final String cakesEndpoint;


    @Autowired
    protected InitInMemoryDatabaseRunner(
            CakeManagerService cakeManagerService,
            @Value("${feign.cake.init.baseUrl}") String baseUrl,
            @Value("${feign.cake.init.cakes}") String cakesEndpoint) {
        this.cakeManagerService = cakeManagerService;
        this.baseUrl = baseUrl;
        this.cakesEndpoint = cakesEndpoint;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = new URL(baseUrl + cakesEndpoint).openStream()) {
            String cakePayload = IOUtils.toString(inputStream);
            List<Cake> seedCakes = mapper.readValue(cakePayload, new TypeReference<List<Cake>>() {
            });
            seedCakes.forEach(cake -> {
                try {
                    cakeManagerService.addNewCake(cake);
                } catch (Exception e) {
                    logger.warn("Failed to add cake with title " + cake.title());
                }
            });
        }
    }
}
