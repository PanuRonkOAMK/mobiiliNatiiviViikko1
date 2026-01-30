package com.example.viikkotehtava1.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtava1.model.Task
import com.example.viikkotehtava1.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    viewModel: TaskViewModel = viewModel()
) {
    val tasks by viewModel.visibleTasks.collectAsState()
    var newTaskTitle by remember { mutableStateOf("") }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Tasks", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { viewModel.sortByDueDate() }) {
                Text("Sort date")
            }
            Button(onClick = { viewModel.filterByDone(true) }) {
                Text("Done")
            }
            Button(onClick = { viewModel.filterByDone(false) }) {
                Text("Not done")
            }
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { viewModel.resetFilter() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear filter")
        }

        Spacer(Modifier.height(12.dp))

        Row {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                modifier = Modifier.weight(1f),
                label = { Text("New task") }
            )

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = {
                    if (newTaskTitle.isNotBlank()) {
                        viewModel.addTask(
                            Task(
                                id = viewModel.nextId(),
                                title = newTaskTitle,
                                description = "",
                                priority = 1,
                                dueDate = 2028,
                                done = false
                            )
                        )
                        newTaskTitle = ""
                    }
                }
            ) {
                Text("Add")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Checkbox(
                            checked = task.done,
                            onCheckedChange = {
                                viewModel.toggleDone(task.id)
                            }
                        )
                        Text(
                            "${task.title} | due: ${task.dueDate}",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Button(onClick = {
                        selectedTask = task
                    }) {
                        Text("Edit")
                    }
                }
                Divider()
            }
        }
    }

    selectedTask?.let { task ->
        DetailDialog(
            task = task,
            onDismiss = { selectedTask = null },
            onSave = {
                viewModel.updateTask(it)
                selectedTask = null
            },
            onDelete = {
                viewModel.removeTask(it.id)
                selectedTask = null
            }
        )
    }
}
