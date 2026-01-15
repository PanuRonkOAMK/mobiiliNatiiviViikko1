package com.example.viikkotehtava1.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikkotehtava1.domain.*

@Composable
fun HomeScreen() {

    var tasks by remember { mutableStateOf(mockTasks) }
    var visibleTasks by remember { mutableStateOf(mockTasks) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Tasks",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                visibleTasks = sortByDueDate(visibleTasks)
            }) {
                Text("Sort date")
            }

            Button(onClick = {
                visibleTasks = filterByDone(visibleTasks, true)
            }) {
                Text("Filter by Done")
            }

            Button(onClick = {
                visibleTasks = filterByDone(visibleTasks, false)
            }) {
                Text("Filter by Not Done")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                visibleTasks = tasks
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear filter")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val newTask = Task(
                id = tasks.size + 1,
                title = "New task",
                description = "Added with button",
                priority = 1,
                dueDate = 2028,
                done = false
            )
            tasks = addTask(tasks, newTask)
            visibleTasks = tasks
        }) {
            Text("Add task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            visibleTasks.forEach { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "${task.title} | due: ${task.dueDate} | done: ${task.done}",
                        modifier = Modifier.padding(4.dp)
                    )

                    Button(onClick = {
                        tasks = toggleDone(tasks, task.id)
                        visibleTasks = toggleDone(visibleTasks, task.id)
                    }) {
                        Text("Toggle")
                    }
                }
            }
        }
    }
}
