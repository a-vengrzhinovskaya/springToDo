package com.example.springToDo.data

import jakarta.persistence.*

@Entity
data class ToDoItem(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val description: String = "",
    @ElementCollection
    val subtasksIds: List<String> = emptyList(),
    val status: ToDoStatus = ToDoStatus.NOT_STARTED
)