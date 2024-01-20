package com.daviddev16.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@NoArgsConstructor
@ConfigurationProperties("crm.application.jwt")
@Configuration
public class JwtAppConfiguration {

    @Value("${chave-segredo}")
    private String chaveSegredo;

    @Value("${dias-expiracao}")
    private String diasExpiracao;

    @Value("${prefixo-token}")
    private String prefixoToken;

    public Long getDiasExpiracao() {
        return Long.parseLong(diasExpiracao);
    }

}
