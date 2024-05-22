package com.example.springToDo

import com.example.springToDo.data.ToDoRepository
import com.example.springToDo.data.model.ToDoItem
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
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
                subtasksIds = listOf(todos.toList().random().id)
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<SpringToDoApplication>(*args)
}