package com.example.springToDo

import com.example.springToDo.data.ToDoItem
import com.example.springToDo.data.ToDoRepository
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringToDoApplication {
    @Autowired
    lateinit var toDoRepository: ToDoRepository

    @PostConstruct
    fun initRepository() {
        repeat(10) {
            toDoRepository.save(ToDoItem(description = "task $it", subtasksIds = emptyList()))
        }
        val todos = toDoRepository.findAll()
        toDoRepository.save(
            ToDoItem(
                description = "with subtask",
                subtasksIds = listOf(todos.toList().random().id.toString())
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<SpringToDoApplication>(*args)
}