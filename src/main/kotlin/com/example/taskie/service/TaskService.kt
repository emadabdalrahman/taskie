package com.example.taskie.service

import com.example.taskie.model.Task
import com.example.taskie.repository.TaskRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskService {
    @Autowired
    private lateinit var taskRepo: TaskRepo

    fun save(task: Task) {
        taskRepo.saveAndFlush(task)
    }

    fun update(task: Task) {
        taskRepo.saveAndFlush(task)
    }

    fun deleteAll() {
        taskRepo.deleteAll()
    }

    fun deleteById(id: String) {
        taskRepo.deleteById(id)
    }

    fun getAll(): Collection<Task> {
        return taskRepo.findAll()
    }

    fun getById(id: String): Task {
        return taskRepo.getReferenceById(id)
    }

}