package com.example.viikkotehtava1.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.viikkotehtava1.domain.*

class TaskViewModel : ViewModel() {

    private val allTasks = mutableStateListOf<Task>()
    var visibleTasks by mutableStateOf(listOf<Task>())
        private set

    init {
        allTasks.addAll(mockTasks)
        visibleTasks = allTasks
    }

    fun addTask(task: Task) {
        allTasks.add(task)
        visibleTasks = allTasks
    }

    fun toggleDone(id: Int) {
        val index = allTasks.indexOfFirst { it.id == id }
        if (index != -1) {
            allTasks[index] = allTasks[index].copy(done = !allTasks[index].done)
            visibleTasks = allTasks
        }
    }

    fun removeTask(id: Int) {
        allTasks.removeAll { it.id == id }
        visibleTasks = allTasks
    }

    fun filterByDone(done: Boolean) {
        visibleTasks = allTasks.filter { it.done == done }
    }

    fun sortByDueDate() {
        visibleTasks = visibleTasks.sortedBy { it.dueDate }
    }

    fun resetFilter() {
        visibleTasks = allTasks
    }

    fun nextId(): Int =
        (allTasks.maxOfOrNull { it.id } ?: 0) + 1
}
