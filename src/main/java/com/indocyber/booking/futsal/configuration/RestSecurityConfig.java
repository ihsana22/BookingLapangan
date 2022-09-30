package com.indocyber.booking.futsal.configuration;

import com.indocyber.booking.futsal.utility.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    @Bean
    @Primary
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**").csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/authenticate","/api/user/register").permitAll()
                .antMatchers("/api/lapangan/insert","/api/hari/insert","/api/jadwal/insert","/api/reservasi/insert"
                                        ,"/api/jadwal/list","/api/jadwal/update","/api/lapangan/list","/api/lapangan/{idLapangan}").hasAuthority("Admin")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
