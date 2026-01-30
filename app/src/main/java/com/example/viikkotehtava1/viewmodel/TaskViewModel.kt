package com.example.viikkotehtava1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viikkotehtava1.model.Task
import com.example.viikkotehtava1.model.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(mockTasks)

    private val _visibleTasks = MutableStateFlow<List<Task>>(mockTasks)
    val visibleTasks: StateFlow<List<Task>> = _visibleTasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.update { it + task }
        resetFilter()
    }

    fun toggleDone(id: Int) {
        _tasks.update { list ->
            list.map {
                if (it.id == id) it.copy(done = !it.done) else it
            }
        }
        resetFilter()
    }

    fun removeTask(id: Int) {
        _tasks.update { it.filterNot { task -> task.id == id } }
        resetFilter()
    }

    fun updateTask(updated: Task) {
        _tasks.update { list ->
            list.map {
                if (it.id == updated.id) updated else it
            }
        }
        resetFilter()
    }

    fun sortByDueDate() {
        _visibleTasks.update { it.sortedBy { task -> task.dueDate } }
    }

    fun filterByDone(done: Boolean) {
        _visibleTasks.value = _tasks.value.filter { it.done == done }
    }

    fun resetFilter() {
        _visibleTasks.value = _tasks.value
    }

    fun nextId(): Int =
        (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1

/*    init {
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
        (allTasks.maxOfOrNull { it.id } ?: 0) + 1*/
}
