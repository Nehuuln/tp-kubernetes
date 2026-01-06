package com.ingnum.rentalservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class BonjourController {

    private static final Logger logger = LoggerFactory.getLogger(BonjourController.class);

    @Value("${php.service.url:http://php-service}")
    private String phpServiceUrl;

    @GetMapping("/bonjour")
    public String bonjour() {
        return "bonjour";
    }

    @GetMapping("/customer/{name}")
    public String customer(@PathVariable String name) {
        RestTemplate restTemplate = new RestTemplate();
        String url = phpServiceUrl + "/index.php?name=" + name;
        logger.info("Requesting URL: " + url);
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }
}
