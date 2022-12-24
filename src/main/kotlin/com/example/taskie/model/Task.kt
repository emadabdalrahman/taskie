package com.example.taskie.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "task", schema = "public")
class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    lateinit var id: String

    @NotBlank
    @Column(name = "description", nullable = false)
    lateinit var description: String

}