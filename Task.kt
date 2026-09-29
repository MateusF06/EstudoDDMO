package com.sambinelli.taskflow.model

/**
 * Data Class que define a estrutura de dados de uma Tarefa (Task).
 *
 * @property id Identificador único da tarefa.
 * @property titulo Título ou nome da tarefa (obrigatório).
 * @property descricao Detalhes adicionais ou notas sobre a tarefa (opcional).
 * @property estaConcluido Status da tarefa. Define se já foi finalizada (padrão: false).
 */
data class Task(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val estaConcluido: Boolean = false
)

// --- Objetos Fictícios (Fake Objects) para Testes e Preview na IDE ---
val todo1 = Task(
    id = 1,
    titulo = "Todo 1",
    descricao = "Descrição para Tarefa 1",
    estaConcluido = false
)

val todo2 = Task(
    id = 2,
    titulo = "Todo 2",
    descricao = "Descrição para Tarefa 2",
    estaConcluido = true
)

val todo3 = Task(
    id = 3,
    titulo = "Todo 3",
    descricao = "Descrição para Tarefa 3",
    estaConcluido = false
)