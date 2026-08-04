package com.example.taskflow

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Classe Application da aplicação.
 *
 * Ela é criada apenas uma vez,
 * quando o aplicativo é iniciado.
 *
 * O Hilt utiliza essa classe para
 * inicializar todo o grafo de dependências.
 */
@HiltAndroidApp
class TaskFlowApplication : Application()