package com.example.taskie.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@JsonPropertyOrder("id","first_name","last_name","email","password","role")
@Entity
@Table(name = "user", schema = "public")
class User() {

    @Id
    @Column(name = "id", nullable = false)
    lateinit var id: String

    @NotBlank
    @JsonProperty("first_name")
    @Column(name = "first_name", nullable = false)
    lateinit var firstName: String

    @NotBlank
    @JsonProperty("last_name")
    @Column(name = "last_name", nullable = false)
    lateinit var lastName: String

    @Email
    @Column(name = "email", nullable = false)
    lateinit var email: String

    @NotBlank
    @Column(name = "password", nullable = false)
    lateinit var password: String

    @NotBlank
    @Column(name = "role", nullable = false)
    lateinit var role: String

    constructor(id: String, firstName: String, lastName: String, email: String, password: String, role: String) : this() {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.role = role
    }
}