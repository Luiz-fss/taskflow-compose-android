package com.example.taskflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskflow.data.repository.TaskRepository


// Essa classe funciona como uma fábrica de ViewModels.
// Ela existe porque nossa TaskViewModel agora precisa receber um Repository.
//
// Antes:
// TaskViewModel()
//
// Agora:
// TaskViewModel(repository)
//
// O Android sozinho não sabe de onde pegar esse repository,
// então nós ensinamos aqui.
class TaskViewModelFactory(
    private val repository: TaskRepository
) : ViewModelProvider.Factory {


    // Esse método é chamado internamente pelo Android
    // quando ele precisa criar uma ViewModel.
    //
    // Ele recebe:
    // - modelClass -> qual ViewModel ele quer criar
    //
    // Retorna:
    // - uma instância dessa ViewModel
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {


        // Aqui estamos perguntando:
        //
        // "A ViewModel que o Android pediu
        // é a nossa TaskViewModel?"
        //
        // Exemplo:
        //
        // pediu TaskViewModel?
        // Sim -> continua
        // Não -> lança erro
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {


            // Aqui finalmente criamos nossa ViewModel.
            //
            // Perceba que agora conseguimos passar
            // o Repository pelo construtor.
            //
            // Isso seria impossível usando apenas:
            //
            // viewModel()
            //
            // porque ele não sabe criar o Repository.
            return TaskViewModel(
                repository
            ) as T

        }


        // Caso alguém tente usar essa Factory
        // para criar outra ViewModel que ela não conhece,
        // avisamos que não existe criação para ela.
        throw IllegalArgumentException(
            "ViewModel desconhecida"
        )
    }
}