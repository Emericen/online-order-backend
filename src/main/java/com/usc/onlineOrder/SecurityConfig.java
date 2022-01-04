package com.usc.onlineOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // "?" is the placeholder
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, enabled FROM customers WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email, authorities FROM authorities WHERE email = ?");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .failureForwardUrl("/login?error=true");
        http.authorizeRequests()
                .antMatchers("/order/*", "/cart", "/checkout")
                .hasAuthority("ROLE_USER");

        // ROLE_USER is defined in customer dao
        // meaning when someone accesses /order, /cart or /checkout, I need to check if
        // he has the authority that I granted.
        // If he is a freud pretending to be my user, he does not have auth. and is not allowed in.

    }

    @SuppressWarnings("deprecation") // otherwise, it bitches about this being deprecated.
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}