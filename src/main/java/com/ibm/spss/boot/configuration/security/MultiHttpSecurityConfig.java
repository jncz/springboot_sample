package com.ibm.spss.boot.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:app-security.xml")
public class MultiHttpSecurityConfig {

}