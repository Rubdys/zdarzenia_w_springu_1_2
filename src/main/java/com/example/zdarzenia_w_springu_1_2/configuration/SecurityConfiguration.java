package com.example.zdarzenia_w_springu_1_2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET)
                .hasAnyRole("BASIC", "ADVANCED", "ADMIN")
                .antMatchers(HttpMethod.POST)
                .hasAnyRole("ADVANCED", "ADMIN")
                .antMatchers(HttpMethod.DELETE)
                .hasAnyRole( "ADMIN")
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails basic = User.builder()
                .username("basic")
                .password("basic")
                .roles("BASIC")
                .build();

        UserDetails advanced = User.builder()
                .username("advanced")
                .password("advanced")
                .roles("ADVANCED")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(basic, advanced, admin);
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
