package nrabello.back.core.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nrabello.back.core.domain.entity.User;
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
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    private final LoginService userDetailsService;

    private final AuthenticationEntryPoint authenticationEntryPoint;


    public JwtAuthFilter(JwtService jwtService, LoginService userDetailsService, AuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String authHeader = request.getHeader(AUTHORIZATION_HEADER);

            if (!isValidAuthHeader(authHeader)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = extractToken(authHeader);
            processToken(token, request);

            filterChain.doFilter(request, response);
        }
//        catch (ExpiredTokenException ex) {
//            handleExpiredTokenException(response, ex);
//        }
        catch (UsernameNotFoundException ex) {
            authenticationEntryPoint.commence(request, response, new AuthenticationException(ex.getMessage()) {});
        } catch (Exception ex) {
            authenticationEntryPoint.commence(request, response, new AuthenticationException(ex.getMessage()) {});
        }
    }

    /**
     * Verifica se o cabeçalho de autorização é válido
     * @param authHeader Cabeçalho de autorização
     * @return true se o cabeçalho for válido, false caso contrário
     */
    private boolean isValidAuthHeader(String authHeader) {
        return authHeader != null && authHeader.startsWith(BEARER_PREFIX);
    }

    /**
     * Extrai o token do cabeçalho de autorização
     * @param authHeader Cabeçalho de autorização
     * @return Token JWT
     */
    private String extractToken(String authHeader) {
        return authHeader.substring(BEARER_PREFIX.length());
    }

    /**
     * Processa o token JWT
     * @param token Token JWT
     * @param request Requisição HTTP
     */
    private void processToken(String token, HttpServletRequest request) {
        String username = jwtService.extractUsername(token);
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        if (isAuthenticationRequired(currentAuth)) {
            authenticateUser(username, token, request);
        }
    }

    /**
     * Verifica se a autenticação é necessária
     * @param currentAuth Autenticação atual
     * @return true se a autenticação for necessária, false caso contrário
     */
    private boolean isAuthenticationRequired(Authentication currentAuth) {
        return currentAuth == null || currentAuth instanceof AnonymousAuthenticationToken;
    }

    /**
     * Autentica o usuário com base no token
     * @param username Nome de usuário
     * @param token Token JWT
     * @param request Requisição HTTP
     */
    private void authenticateUser(String username, String token, HttpServletRequest request) {
        User userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = createAuthenticationToken(userDetails, request);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    /**
     * Cria um token de autenticação
     * @param userDetails Detalhes do usuário
     * @param request Requisição HTTP
     * @return Token de autenticação
     */
    private UsernamePasswordAuthenticationToken createAuthenticationToken(User userDetails,
                                                                          HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authToken;
    }

    /**
     * Trata exceção de token expirado
     * @param response Resposta HTTP
     * @param ex Exceção
     * @throws IOException
     */
//    private void handleExpiredTokenException(HttpServletResponse response, ExpiredTokenException ex)
//            throws IOException {
//        sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
//    }

    /**
     * Trata exceção de usuário não encontrado
     * @param response Resposta HTTP
     * @throws IOException
     */
    private void handleUsernameNotFoundException(HttpServletResponse response) throws IOException {
        sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Usuário não encontrado");
    }

    /**
     * Trata exceção genérica
     * @param response Resposta HTTP
     * @param ex Exceção
     * @throws IOException
     */
    private void handleGenericException(HttpServletResponse response, Exception ex) throws IOException {
        sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Erro interno ao processar autenticação");
    }

    /**
     * Envia resposta de erro
     * @param response Resposta HTTP
     * @param status Status HTTP
     * @param message Mensagem de erro
     * @throws IOException
     */
    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }

}
