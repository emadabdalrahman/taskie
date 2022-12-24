package com.example.taskie.config

import com.example.taskie.security.JwtTokenFilter
import com.example.taskie.security.LoginFilter
import com.example.taskie.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import java.util.*

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userService: UserService

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.csrf().disable()
            it.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            it.cors().configurationSource {
                CorsConfiguration().apply {
                    allowedMethods = Collections.singletonList("*")
                    allowedHeaders = Collections.singletonList("*")
                    allowedOrigins = Collections.singletonList("*")
                    allowCredentials = true
                    maxAge = 2500L
                }
            }
            it.authorizeHttpRequests()
                .antMatchers("/taskie-api/v1/auth/login").permitAll()
                .antMatchers("/taskie-api/v1/auth/register").permitAll()
                .antMatchers("/taskie-api/v1/task/*").authenticated()
                .antMatchers("/taskie-api/v1/user/*").authenticated()
            it.addFilter(LoginFilter(authenticationManager()))
            it.addFilterBefore(JwtTokenFilter(userService), LoginFilter::class.java)
            it.httpBasic()
        }
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}