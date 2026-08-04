package com.example.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.taskflow.ui.TaskFlowApp
import com.example.taskflow.ui.theme.TaskFlowTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity principal da aplicação.
 *
 * @AndroidEntryPoint informa ao Hilt que
 * esta Activity faz parte do grafo de
 * dependências e poderá utilizar objetos
 * gerenciados por ele.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TaskFlowTheme {
                TaskFlowApp()
            }
        }
    }
}