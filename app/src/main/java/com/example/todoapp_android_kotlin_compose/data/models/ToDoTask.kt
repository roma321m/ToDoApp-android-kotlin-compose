package com.example.todoapp_android_kotlin_compose.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoapp_android_kotlin_compose.data.source.ToDoDao.Companion.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)
