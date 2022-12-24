package com.example.taskie.repository

import com.example.taskie.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepo : JpaRepository<Task, String> {
}