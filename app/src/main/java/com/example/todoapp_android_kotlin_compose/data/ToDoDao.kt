package com.example.todoapp_android_kotlin_compose.data

import androidx.room.*
import com.example.todoapp_android_kotlin_compose.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao // Data Access Object
interface ToDoDao {
    // All the functions need to be asynchronous.
    // When using Flow - the function is automatically using kotlin coroutine and the function is asynchronous.
    // When we don't use Flow, need to make the function suspended.

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<ToDoTask>>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(toDoTask: ToDoTask)

    @Update
    suspend fun updateTask(toDoTask: ToDoTask)

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTask)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>

    @Query(
        "SELECT * FROM todo_table ORDER BY CASE " +
                "WHEN priority LIKE 'L%' THEN 1" +
                "WHEN priority LIKE 'M%' THEN 2" +
                "WHEN priority LIKE 'H%' THEN 3 END"
    )
    fun sortByLowPriority(): Flow<List<ToDoTask>>

    @Query(
        "SELECT * FROM todo_table ORDER BY CASE " +
                "WHEN priority LIKE 'H%' THEN 1" +
                "WHEN priority LIKE 'M%' THEN 2" +
                "WHEN priority LIKE 'L%' THEN 3 END"
    )
    fun sortByHighPriority(): Flow<List<ToDoTask>>
}