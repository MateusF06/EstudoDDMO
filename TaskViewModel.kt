package com.sambinelli.taskflow.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sambinelli.taskflow.model.Task

/**
 * Enumeração que define os tipos de filtros de exibição disponíveis na interface.
 */
enum class Filter { ALL, PENDING, DONE }

/**
 * ViewModel responsável por gerenciar a lógica de negócio e os estados reativos da tela de tarefas.
 */
class TaskViewModel : ViewModel() {

    // Lista mutável interna observável pelo Jetpack Compose.
    // O uso de `mutableStateListOf` permite que alterações na lista notifiquem a UI para se recompor.
    private val _tasks = mutableStateListOf<Task>()
    
    // Exposição pública da lista de tarefas apenas para leitura.
    val tasks: List<Task> get() = _tasks

    // Estado do filtro ativo selecionado pelo usuário.
    // `private set` garante que apenas o ViewModel possa alterar o valor diretamente.
    var activeFilter by mutableStateOf(Filter.ALL)
        private set

    // Armazena a tarefa que o usuário solicitou excluir.
    // Se for diferente de null, aciona a exibição do diálogo de confirmação.
    var taskToDelete by mutableStateOf<Task?>(null)
        private set

    // Contador sequencial privado para geração de IDs únicos para cada nova tarefa.
    private var nextId = 1

    /**
     * Propriedade calculada (get) que retorna a lista de tarefas filtrada com base no `activeFilter`.
     */
    val filteredTasks: List<Task>
        get() = when (activeFilter) {
            Filter.ALL -> tasks
            Filter.PENDING -> tasks.filter { !it.estaConcluido }
            Filter.DONE -> tasks.filter { it.estaConcluido }
        }

    /**
     * Adiciona uma nova tarefa à lista.
     * @param titulo Título da tarefa.
     * @param descricao Descrição opcional da tarefa.
     */
    fun addTask(titulo: String, descricao: String) {
        // Impede a adição de tarefas sem título.
        if (titulo.isBlank()) return
        
        _tasks.add(
            Task(
                id = nextId++,
                titulo = titulo,
                descricao = descricao
            )
        )
    }

    /**
     * Alterna o estado de conclusão de uma tarefa (concluída <-> pendente).
     * @param task A tarefa que terá o status alternado.
     */
    fun toggleDone(task: Task) {
        val index = _tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            // Utiliza o método .copy() do data class para criar uma nova instância modificada
            _tasks[index] = _tasks[index].copy(estaConcluido = !_tasks[index].estaConcluido)
        }
    }

    /**
     * Prepara uma tarefa para ser excluída abrindo o diálogo de confirmação.
     */
    fun requestDelete(task: Task) {
        taskToDelete = task
    }

    /**
     * Confirma e remove permanentemente a tarefa armazenada em `taskToDelete`.
     */
    fun confirmDelete() {
        taskToDelete?.let { task ->
            _tasks.removeIf { it.id == task.id }
        }
        taskToDelete = null // Fecha o diálogo
    }

    /**
     * Cancela o processo de exclusão e fecha o diálogo.
     */
    fun cancelDelete() {
        taskToDelete = null
    }

    /**
     * Atualiza o filtro ativo na interface.
     */
    fun setFilter(filter: Filter) {
        activeFilter = filter
    }
}