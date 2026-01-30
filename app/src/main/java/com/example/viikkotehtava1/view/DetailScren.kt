package com.example.viikkotehtava1.view

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.viikkotehtava1.model.Task

@Composable
fun DetailDialog(
    task: Task,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: (Task) -> Unit
) {
    var title by remember { mutableStateOf(task.title) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit task") },
        text = {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") }
            )
        },
        confirmButton = {
            Button(onClick = {
                onSave(task.copy(title = title))
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = { onDelete(task) }) {
                Text("Delete")
            }
        }
    )
}
