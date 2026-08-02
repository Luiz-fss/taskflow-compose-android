package com.example.taskflow.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskflow.ui.components.EmptyTaskState
import com.example.taskflow.ui.screens.HomeScreen

@Composable
fun TaskFlowApp() {
    Scaffold { innerPadding ->

        HomeScreen(
            modifier = Modifier.padding(innerPadding)
        )
        EmptyTaskState()

    }
}