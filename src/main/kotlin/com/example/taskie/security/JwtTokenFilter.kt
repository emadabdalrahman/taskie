package com.example.taskie.security

import com.example.taskie.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter(private val userService: UserService) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION) ?: ""
        if (authorization.isEmpty() || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }
        kotlin.runCatching {
            val claims = JwtUtil.getClaims(authorization.replaceFirst("Bearer ", ""))
            if (!JwtUtil.isExpired(claims)) {
                val user = userService.loadUserByUsername(claims?.get("email") as String)
                val authentication = UsernamePasswordAuthenticationToken(user.username, user.password, user.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }
}