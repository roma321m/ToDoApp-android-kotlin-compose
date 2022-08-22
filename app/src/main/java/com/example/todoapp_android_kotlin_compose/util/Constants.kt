package com.example.todoapp_android_kotlin_compose.util

object Constants {

    const val DATABASE_TABLE = "todo_table"
    const val DATABASE_NAME = "todo_database"

    const val LIST_SCREEN = "list/{action}"
    const val TASK_SCREEN = "task/{taskId}"

    const val LIST_ARGUMENT_KEY = "action"
    const val TASK_ARGUMENT_KEY = "taskId"

    const val PREFERENCE_NAME = "todo_preferences"
    const val PREFERENCE_KEY = "sort_state"

    const val MAX_TITLE_LENGTH = 20

    const val ITEM_ANIMATION_TIME_MILLIS = 300
}
