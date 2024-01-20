package com.daviddev16.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

import static com.daviddev16.core.util.DateUtil.*;

@Component
public class JwtProviderService {

    private final JwtAppConfiguration tokenConfig;

    public JwtProviderService(JwtAppConfiguration tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    public String gerarTokenJwt(String login) {

        final Date dataExpiracao = criarDateComOperador(LocalDateTime.now(),
                dtTm -> dtTm.plusMinutes( tokenConfig.getDiasExpiracao() ));

        return Jwts
                .builder()
                .setSubject(login)
                .setExpiration(dataExpiracao)
                .signWith( SignatureAlgorithm.HS512, tokenConfig.getChaveSegredo() )
                .compact();
    }

    public boolean verificarSeTokenEhValido(String tokenJwt) {
        try {
            Claims claims = obterClaimsDeJwt(tokenJwt);
            LocalDateTime lcDateTime = coverterDateToLocalDateTime(claims.getExpiration());
            return LocalDateTime.now().isBefore(lcDateTime);
        } catch (Exception e) {
            return false;
        }
    }

    private Claims obterClaimsDeJwt(String tokenJwt) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey( tokenConfig.getChaveSegredo() )
                .parseClaimsJws(tokenJwt)
                .getBody();
    }

    public String obterLoginUsuarioDeTokenJwt(String tokenJwt) throws ExpiredJwtException {
        return obterClaimsDeJwt(tokenJwt).getSubject();
    }


}