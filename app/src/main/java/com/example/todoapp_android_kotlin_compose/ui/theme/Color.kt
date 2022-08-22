package com.example.todoapp_android_kotlin_compose.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryColor =  Color(0xFF4FF46B)
val primaryLightColor =  Color(0xFF8CFF9C)
val primaryDarkColor =  Color(0xFF006D21)
val primaryTextColor =  Color(0xFF000000)
val secondaryColor =  Color(0xFF8E24AA)
val secondaryLightColor =  Color(0xFFC158DC)
val secondaryDarkColor =  Color(0xFF5C007A)
val secondaryTextColor =  Color(0xFFFFFFFF)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = MediumGray

val Colors.fabBackgroundColor: Color
    @Composable
    get() = if (isLight) secondaryLightColor else secondaryDarkColor

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) primaryColor else primaryDarkColor

val Colors.taskItemBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else DarkGray

val Colors.taskItemTextColor: Color
    @Composable
    get() = if (isLight) DarkGray else LightGray