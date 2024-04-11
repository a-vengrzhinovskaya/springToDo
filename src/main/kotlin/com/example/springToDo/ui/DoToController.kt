package com.example.springToDo.ui

import com.example.springToDo.data.ToDoItem
import com.example.springToDo.data.ToDoRepository
import com.example.springToDo.data.ToDoStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class DoToController(private val toDoRepository: ToDoRepository) {
    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("todos", toDoRepository.findAll())
        return "index"
    }

    @GetMapping("/changeStatus/{todoId}")
    fun changeStatus(model: Model, @PathVariable todoId: Long): String {
        val todoItem = toDoRepository.findById(todoId).orElseThrow { NoSuchElementException("Todo not found") }
        val newTodoItem = todoItem.copy(status = ToDoStatus.DONE)
        toDoRepository.save(newTodoItem)
        return "redirect:/"
    }

    @GetMapping("/delete/{todoId}")
    fun delete(model: Model, @PathVariable todoId: Long): String {
        toDoRepository.deleteById(todoId)
        return "redirect:/"
    }

    @PostMapping("/add")
    fun add(model: Model, @ModelAttribute todoItem: ToDoItem): String {
        if (toDoRepository.findAll().map { it.id }.containsAll(todoItem.subtasksIds.map { it.toLong() })) {
            toDoRepository.save(todoItem)
        }
        return "redirect:/"
    }
}