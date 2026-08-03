package com.example.taskflow.data.mapper

import com.example.taskflow.data.local.entity.TaskEntity
import com.example.taskflow.model.Task


fun TaskEntity.toTask(): Task {

    return Task(
        id = id,
        title = title,
        completed = completed
    )
}


fun Task.toTaskEntity(): TaskEntity {

    return TaskEntity(
        id = id,
        title = title,
        completed = completed
    )
}