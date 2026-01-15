package com.example.viikkotehtava1.domain

data class Task(val id: Int,
                val title: String,
                val description: String,
                val priority: Int,
                val dueDate: Int,
                val done: Boolean)
