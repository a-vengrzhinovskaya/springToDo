package com.example.springToDo.data

import com.example.springToDo.data.model.ToDoItem
import org.springframework.data.mongodb.repository.MongoRepository

interface ToDoRepository : MongoRepository<ToDoItem, Long>