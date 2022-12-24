package com.example.taskie.controller

import com.example.taskie.model.Task
import com.example.taskie.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/taskie-api/v1/task")
class TaskController {
    @Autowired
    private lateinit var taskService: TaskService

    @GetMapping("/get-all")
    fun getAll(): ResponseEntity<Collection<Task>> {
        return ResponseEntity.ok(taskService.getAll())
    }

    @GetMapping("/get-by-id")
    fun getById(@RequestParam id: String): ResponseEntity<Task> {
        return ResponseEntity.ok(taskService.getById(id))
    }

    @PostMapping("/save")
    fun save(@Valid @RequestBody task: Task): ResponseEntity<String> {
        taskService.save(task)
        return ResponseEntity.ok("saved successfully")
    }

    @PutMapping("/update")
    fun update(@Valid @RequestBody task: Task): ResponseEntity<String> {
        taskService.update(task)
        return ResponseEntity.ok("updated successfully")
    }

    @PutMapping("/delete-by-id")
    fun deleteById(@RequestParam id: String): ResponseEntity<String> {
        taskService.deleteById(id)
        return ResponseEntity.ok("task deleted successfully")
    }

    @PutMapping("/delete-all")
    fun deleteAll(): ResponseEntity<String> {
        taskService.deleteAll()
        return ResponseEntity.ok("all tasks deleted successfully")
    }
}