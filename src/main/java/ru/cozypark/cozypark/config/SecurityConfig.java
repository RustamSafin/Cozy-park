package ru.cozypark.cozypark.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import ru.cozypark.cozypark.security.CustomUserDetailsService;
import ru.cozypark.cozypark.security.JwtAuthenticationEntryPoint;
import ru.cozypark.cozypark.security.JwtAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Configuration
    @Order(1)
    public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter{
        @Autowired
        CustomUserDetailsService customUserDetailsService;

        @Autowired
        private JwtAuthenticationEntryPoint unauthorizedHandler;

        @Autowired
        PasswordEncoder passwordEncoder;
        @Bean
        public JwtAuthenticationFilter jwtAuthenticationFilter() {
            return new JwtAuthenticationFilter();
        }

        @Override
        public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder
                    .userDetailsService(customUserDetailsService)
                    .passwordEncoder(passwordEncoder);
        }

        @Bean(BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**").authorizeRequests()
                    .antMatchers("/api/auth/**").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                    .and()
                    .cors().and().csrf().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            // Add our custom JWT security filter
            http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter{
        @Autowired
        CustomUserDetailsService customUserDetailsService;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);

        }

        @Bean
        public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
            TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices("govnojopa", customUserDetailsService);
            tokenBasedRememberMeServices.setCookieName("kim-reborn");
            tokenBasedRememberMeServices.setAlwaysRemember(true);
            tokenBasedRememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 30);
            return tokenBasedRememberMeServices;
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/reg").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/swagger-ui.html/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .cors().and().csrf().disable()
                    .rememberMe().key("kim").alwaysRemember(true)
                    .and()
                    .formLogin()
                    .loginPage("/login").successForwardUrl("/issue").defaultSuccessUrl("/issue")
                    .loginProcessingUrl("/login");

        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/fonts/**", "/icons/**");
        }
    }

}
