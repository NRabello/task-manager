package nrabello.back.core.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import nrabello.back.core.domain.entity.User;
import nrabello.back.core.exception.ExpiredTokenException;
import nrabello.back.core.service.IJwtService;
import nrabello.back.core.service.JwtService;
import nrabello.back.core.service.LoginService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de autenticação JWT Verifica e valida o token JWT nas requisições
 */

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_PREFIX = "Bearer ";

    private static final String[] PUBLIC_ENDPOINTS = { "/api/user/registrar", "/api/user/login"};

    private final IJwtService jwtService;

    private final LoginService userDetailsService;


    public JwtAuthFilter(IJwtService jwtService, LoginService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (isPublicEndpoint(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String authHeader = request.getHeader(AUTHORIZATION_HEADER);

            if (!isValidAuthHeader(authHeader)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = extractToken(authHeader);
            processToken(token, request);
        }
        catch (ExpiredTokenException ex) {
            handleExpiredTokenException(response, ex);
            return;
        }
        catch (UsernameNotFoundException ex) {
            handleUsernameNotFoundException(response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidAuthHeader(String authHeader) {
        return authHeader != null && authHeader.startsWith(BEARER_PREFIX);
    }

    private boolean isPublicEndpoint(String requestURI) {
        for (String endpoint : PUBLIC_ENDPOINTS) {
            if (requestURI.equals(endpoint)) {
                return true;
            }
        }
        return false;
    }

    private String extractToken(String authHeader) {
        return authHeader.substring(BEARER_PREFIX.length());
    }

    private void processToken(String token, HttpServletRequest request) {
        String username = jwtService.extractUsername(token);
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        if (isAuthenticationRequired(currentAuth)) {
            authenticateUser(username, token, request);
        }
    }

    private boolean isAuthenticationRequired(Authentication currentAuth) {
        return currentAuth == null || currentAuth instanceof AnonymousAuthenticationToken;
    }

    private void authenticateUser(String username, String token, HttpServletRequest request) {
        User userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = createAuthenticationToken(userDetails, request);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(User userDetails,
                                                                          HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authToken;
    }
    private void handleUsernameNotFoundException(HttpServletResponse response) throws IOException {
        log.warn("Usuário não encontrado na validação do token");
        sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Usuário não encontrado");
    }

    private void handleGenericException(HttpServletResponse response, Exception ex) throws IOException {
        sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Erro interno ao processar autenticação" + ex.getMessage());
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }

    private void handleExpiredTokenException(HttpServletResponse response, ExpiredTokenException ex)
            throws IOException {
        log.warn("Token expirado: {}", ex.getMessage());
        sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    }


}
