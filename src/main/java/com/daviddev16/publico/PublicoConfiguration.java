package com.daviddev16.publico;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("crm.application.publico")
@Configuration
public class PublicoConfiguration {

    @Value("${versao-api}")
    public String versaoApi;

}
