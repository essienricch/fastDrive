package project.colon.fastdrive.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project.colon.fastdrive.security.filters.FastDriveAuthenticationFilter;
import project.colon.fastdrive.security.filters.FastDriveAuthorizationFilter;
import project.colon.fastdrive.security.util.JwtUtil;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final String[] AUTHENTICATION_WHITE_LIST = {"/api/v1/driver/register", "/api/v1/passenger/register"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        UsernamePasswordAuthenticationFilter authenticationFilter = new FastDriveAuthenticationFilter(authenticationManager, jwtUtil);
        authenticationFilter.setFilterProcessesUrl("api/v1/login");
        return httpSecurity.csrf().disable().cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new FastDriveAuthorizationFilter(jwtUtil), FastDriveAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, AUTHENTICATION_WHITE_LIST)
                .permitAll()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and().build();

    }
}
