package com.example.todoapp_android_kotlin_compose.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

@Composable
fun DisplayDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    fullText: String,
    hyperLinks: Map<String, String>,
    textStyle: TextStyle = TextStyle.Default,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Normal,
    linkTextDecoration: TextDecoration = TextDecoration.None,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = { closeDialog() },
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                HyperlinkText(
                    modifier,
                    fullText,
                    hyperLinks,
                    textStyle,
                    linkTextColor,
                    linkTextFontWeight,
                    linkTextDecoration,
                    fontSize
                )
            },
            buttons = {}
        )
    }
}