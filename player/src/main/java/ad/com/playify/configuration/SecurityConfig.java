package ad.com.playify.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import ad.com.playify.adapter.in.utils.KeycloakLogoutHandler;
import ad.com.playify.adapter.in.utils.KeycloakRealmRoleConverter;

@Configuration
@EnableWebSecurity
class SecurityConfig {

    private final KeycloakLogoutHandler keycloakLogoutHandler;

    String jwkSetUri = "http://localhost:8081/realms/playify/protocol/openid-connect/certs";

    SecurityConfig(KeycloakLogoutHandler keycloakLogoutHandler) {
        this.keycloakLogoutHandler = keycloakLogoutHandler;
    }

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.POST, "*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "*").permitAll()
                .anyRequest()
                .authenticated()).oauth2ResourceServer(
                        oauth2ResourceServer -> oauth2ResourceServer.jwt(
                                jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
        http.oauth2Login()
                .and()
                .logout()
                .addLogoutHandler(keycloakLogoutHandler)
                .logoutSuccessUrl("/");
        return http.build();
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtConverter;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }
}

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

// public static final String[] ENDPOINTS_WHITELIST = {
// "/css/**",
// "/artist",
// "/auth/**",
// };
// public static final String LOGIN_URL = "/login";
// public static final String LOGOUT_URL = "/logout";
// public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
// public static final String DEFAULT_SUCCESS_URL = "/home";
// public static final String USERNAME = "username";
// public static final String PASSWORD = "password";

// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http.authorizeHttpRequests(request ->
// request.requestMatchers(ENDPOINTS_WHITELIST).permitAll()
// .anyRequest().authenticated())
// .csrf();
// return http.build();
// }
// }