package com.example.taskie.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class LoginFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    init {
        super.setAuthenticationManager(authenticationManager)
        setFilterProcessesUrl("/taskie-api/v1/auth/login")
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val email = request?.getParameter("email")
        val password = request?.getParameter("password")
        val authentication = UsernamePasswordAuthenticationToken(email, password)
        return authenticationManager.authenticate(authentication)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val email = authResult?.principal as String
        val authorities = authResult.authorities
        val accessToken = JwtUtil.getAccessToken(email, authorities)
        val refreshToken = JwtUtil.getRefreshToken(email, authorities)
        response?.setHeader("access_token", accessToken)
        response?.setHeader("refresh_token", refreshToken)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        super.unsuccessfulAuthentication(request, response, failed)
    }

}