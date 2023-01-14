package com.example.todoapp_android_kotlin_compose.ui.screens.task.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.todoapp_android_kotlin_compose.R
import com.example.todoapp_android_kotlin_compose.components.PriorityDropDown
import com.example.todoapp_android_kotlin_compose.data.models.Priority
import com.example.todoapp_android_kotlin_compose.ui.theme.DevicePreviews
import com.example.todoapp_android_kotlin_compose.ui.theme.spacing
import com.example.todoapp_android_kotlin_compose.util.Constants.MAX_TITLE_LENGTH

@Composable
fun TaskContent(
    modifier: Modifier,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(all = MaterialTheme.spacing.large)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = title,
            onValueChange = {
                onTitleChange(it)
            },
            label = {
                Text(text = stringResource(R.string.title))
            },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = MaterialTheme.spacing.medium),
            text = "${title.length} / $MAX_TITLE_LENGTH",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.caption,
            color = if (title.length >= MAX_TITLE_LENGTH) Color.Red else Color.LightGray
        )
        Divider(
            modifier = Modifier.height(MaterialTheme.spacing.medium),
            color = MaterialTheme.colors.background
        )
        PriorityDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = description,
            onValueChange = {
                onDescriptionChange(it)
            },
            label = {
                Text(text = stringResource(R.string.description))
            },
            textStyle = MaterialTheme.typography.body1
        )
    }
}

@DevicePreviews
@Composable
fun TaskContentPreview() {
    TaskContent(
        modifier = Modifier,
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.HIGH,
        onPrioritySelected = {}
    )
}
