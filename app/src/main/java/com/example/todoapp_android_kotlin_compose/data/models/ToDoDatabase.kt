package com.example.todoapp_android_kotlin_compose.data.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp_android_kotlin_compose.data.ToDoDao

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}