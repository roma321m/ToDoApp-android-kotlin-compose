package com.example.todoapp_android_kotlin_compose.data.models

import androidx.compose.ui.graphics.Color
import com.example.todoapp_android_kotlin_compose.ui.theme.HighPriorityColor
import com.example.todoapp_android_kotlin_compose.ui.theme.LowPriorityColor
import com.example.todoapp_android_kotlin_compose.ui.theme.MediumPriorityColor
import com.example.todoapp_android_kotlin_compose.ui.theme.NonePriorityColor

enum class Priority (val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
