package com.ditraacademy.travelagency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
/*
* configuration for the audible class
* Jpa will come here to get the user name
*
* */
public class AudibleConfig {
    @Bean
    public AuditorAware<String> auditorAware(){
        return ()-> Optional.ofNullable("Wael");
    }
}
