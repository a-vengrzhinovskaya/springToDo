package com.example.springToDo.data

import org.springframework.data.jpa.repository.JpaRepository

interface ToDoRepository : JpaRepository<ToDoItem, Long>