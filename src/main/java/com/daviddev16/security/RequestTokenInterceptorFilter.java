package com.daviddev16.security;

import com.daviddev16.usuario.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class RequestTokenInterceptorFilter extends OncePerRequestFilter {

    private final String JWT_PREFIX = "Bearer ";
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final Integer JWT_PREFIX_LENGTH = JWT_PREFIX.length();

    private final JwtProviderService jwtProvider;
    private final UsuarioService usuarioService;

    public RequestTokenInterceptorFilter(JwtProviderService jwtProvider,
                                         UsuarioService usuarioService)
    {
        this.jwtProvider = jwtProvider;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authHeader == null || !authHeader.startsWith(JWT_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenJwt = authHeader.substring(JWT_PREFIX_LENGTH);

        try {

            String usuarioLogin = jwtProvider.obterLoginUsuarioDeTokenJwt(tokenJwt);
            UserDetails userDetails = usuarioService.loadUserByUsername(usuarioLogin);

            UsernamePasswordAuthenticationToken authUserToken
                    = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authUserToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authUserToken);

        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            throw e;
        } finally {
            filterChain.doFilter(request, response);
        }
    }

}
