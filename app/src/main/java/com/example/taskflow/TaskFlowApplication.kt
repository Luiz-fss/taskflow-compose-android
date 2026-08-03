package com.example.taskflow

import android.app.Application
import com.example.taskflow.di.AppContainer


class TaskFlowApplication : Application() {

    // Guarda nossas dependências enquanto o app estiver aberto
    lateinit var appContainer: AppContainer


    override fun onCreate() {
        super.onCreate()

        // Inicializa o container uma única vez
        appContainer = AppContainer(this)
    }
}