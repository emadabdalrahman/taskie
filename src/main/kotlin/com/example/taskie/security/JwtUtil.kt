package com.example.taskie.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.GrantedAuthority
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.SecretKey

object JwtUtil {

    private const val key: String = "ss@d%dsv!sd#sd&sdv!sdv"
    private val parser = Jwts.parserBuilder().setSigningKey(getSecretKey()).build()

    private fun getSecretKey(): SecretKey? {
        return Keys.hmacShaKeyFor(key.toByteArray(Charsets.UTF_32))
    }

    fun getAccessToken(email: String, authorities: Collection<GrantedAuthority>): String? {
        return buildToken(
            Date.from(Instant.now().plus(3, ChronoUnit.DAYS)),
            mutableMapOf<String?, Any?>().apply {
                this["email"] = email
                this["authority"] = authorities.map { it.authority }
            }
        )
    }

    fun getRefreshToken(email: String, authorities: Collection<GrantedAuthority>): String? {
        return buildToken(
            Date.from(Instant.now().plus(7, ChronoUnit.DAYS)),
            mutableMapOf<String?, Any?>().apply {
                this["email"] = email
                this["authority"] = authorities.map { it.authority }
            }
        )
    }

    fun getClaims(jwt: String): Claims? {
        return parser.parseClaimsJws(jwt).body
    }

    fun isExpired(claims: Claims?): Boolean {
        val expiration = claims?.expiration
        return Instant.now().isAfter(expiration?.toInstant())
    }

    private fun buildToken(exp: Date, claims: Map<String?, Any?>): String? {
        return Jwts.builder()
            .addClaims(claims)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(exp)
            .signWith(getSecretKey())
            .compact()
    }

}