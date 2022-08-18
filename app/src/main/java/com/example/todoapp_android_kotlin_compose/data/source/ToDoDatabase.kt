package com.example.todoapp_android_kotlin_compose.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp_android_kotlin_compose.data.models.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}