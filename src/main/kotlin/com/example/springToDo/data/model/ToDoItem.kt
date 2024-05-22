package com.example.springToDo.data.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Id
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ToDo")
data class ToDoItem(
    @Id
    val id: ObjectId = ObjectId(),
    val description: String = "",
    @ElementCollection
    val subtasksIds: List<ObjectId> = emptyList(),
    val status: String = "NOT STARTED"
)