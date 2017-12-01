package com.xiaoer.cloud.config;

import com.xiaoer.cloud.user.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationEntryPoint LoginUrlAuthenticationEntryPoint(){
        return new LoginUrlEntryPoint();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 处理没有登录参数丢失的问题
        http.exceptionHandling().authenticationEntryPoint(LoginUrlAuthenticationEntryPoint());

                // 禁用基本认证
                http.httpBasic().disable()

//                .requestMatchers()
//                        .antMatchers("/oauth/authorize","/login", "/oauth/confirm_access")
//                .and()
                .authorizeRequests()
                .antMatchers("/api/logput").permitAll()
                .antMatchers("/doLogin").permitAll()
                .antMatchers("/oauth/authorize").hasAnyRole("USER")
                //.antMatchers("/oauth/token").hasAnyRole("USER")
                .antMatchers("/user").hasAnyRole("USER")
                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .formLogin().loginPage("/login").permitAll()
//                .loginProcessingUrl("/login").usernameParameter("email")
//                .passwordParameter("password").permitAll().defaultSuccessUrl("/")
                .and().csrf().disable();
    }
}
