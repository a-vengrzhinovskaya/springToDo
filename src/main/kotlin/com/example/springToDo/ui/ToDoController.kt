package com.example.springToDo.ui

import com.example.springToDo.data.ToDoRepository
import com.example.springToDo.data.model.ToDoItem
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@Api(description = "ToDo List Controller")
class ToDoController(private val toDoRepository: ToDoRepository) {
    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("todos", toDoRepository.findAll())
        return "index"
    }

    @GetMapping("/changeStatus/{todoId}")
    @ApiOperation("Change ToDo Status by id")
    fun changeStatus(model: Model, @PathVariable todoId: Long): String {
        val todoItem = toDoRepository.findById(todoId).orElseThrow { NoSuchElementException("Todo not found") }
        val newTodoItem = todoItem.copy(status = "DONE")
        toDoRepository.save(newTodoItem)
        return "redirect:/"
    }

    @DeleteMapping("/delete/{todoId}")
    @ApiOperation("Delete ToDo by id")
    fun delete(model: Model, @PathVariable todoId: Long): String {
        toDoRepository.deleteById(todoId)
        return "redirect:/"
    }

    @PostMapping("/add")
    @ApiOperation("Add new ToDo")
    fun add(model: Model, @ModelAttribute todoItem: ToDoItem): String {
        if (toDoRepository.findAll().map { it.id }.containsAll(todoItem.subtasksIds.map { it })) {
            toDoRepository.save(todoItem)
        }
        return "redirect:/"
    }
}