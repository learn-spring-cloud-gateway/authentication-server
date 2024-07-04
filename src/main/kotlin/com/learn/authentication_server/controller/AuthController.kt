package com.learn.authentication_server.controller

import com.learn.authentication_server.model.Credential
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*


@RestController
@RequestMapping("/login")
class AuthController {
    @Value("\${jwt.secret}")
    private val secret: String? = null

    @GetMapping
    fun getToken(): ResponseEntity<Token> {
        println("inside auth login")
        return ResponseEntity.ok(Token(Jwts.builder()
            .claim("id", "ankitha")
            .claim("role", "admin")
            .setSubject("Test Token")
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)))
            .signWith(SignatureAlgorithm.HS256, secret).compact()))
    }

    @PostMapping
    fun getTokenProvidingUsernameAndPassword(@RequestBody credential: Credential): String {
        /*println("inside getTokenProvidingUsernameAndPassword")
        return Jwts.builder()
            .claim("id", credential.userName)
            .claim("role", credential.role)
            .setSubject("Test Token")
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)))
            .signWith(SignatureAlgorithm.HS256, secret).compact()*/

        return "LOGIN - POST"
    }
}

data class Token(val value: String)