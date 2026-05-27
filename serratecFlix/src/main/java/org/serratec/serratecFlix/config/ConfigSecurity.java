package org.serratec.serratecFlix.config;

import java.util.Arrays;

import org.serratec.serratecFlix.security.JwtAuthenticationFilter;
import org.serratec.serratecFlix.security.JwtAuthorizationFilter;
import org.serratec.serratecFlix.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors((cors) -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(
                        requests -> {
                            requests.requestMatchers(HttpMethod.POST,"/usuarios/cadastrar").permitAll()
                                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/filmes/externo").permitAll()
                                    .requestMatchers(HttpMethod.DELETE, "/historicos/*").permitAll()
                                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                                    .requestMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMINISTRADOR")
                                    .requestMatchers(HttpMethod.POST, "/filmes").hasAuthority("ADMINISTRADOR")
                                    .requestMatchers(HttpMethod.PUT, "/filmes/**").hasAuthority("ADMINISTRADOR")
                                    .requestMatchers(HttpMethod.POST, "/series").hasAuthority("ADMINISTRADOR")
                                    .requestMatchers(HttpMethod.PUT, "/series/**").hasAuthority("ADMINISTRADOR")
                                    .requestMatchers(HttpMethod.POST, "/categorias").hasAuthority("ADMINISTRADOR")
                                    .requestMatchers(HttpMethod.PUT, "/categorias/**").hasAuthority("ADMINISTRADOR")

                                    .anyRequest().hasAnyAuthority("USUARIO", "ADMINISTRADOR");
                        }
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilter(new JwtAuthenticationFilter(
                authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil));
        http.addFilter(new JwtAuthorizationFilter(
                authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil, userDetailsService));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
