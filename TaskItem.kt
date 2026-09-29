package com.sambinelli.taskflow.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.sambinelli.taskflow.model.Task

/**
 * Componente que exibe os detalhes de uma tarefa individual dentro da lista.
 *
 * @param task Instância da tarefa a ser mostrada.
 * @param onToggleDone Callback chamado para alterar o estado de conclusão.
 * @param onDeleteRequest Callback chamado para solicitar a exclusão da tarefa.
 */
@Composable
fun TaskItem(
    task: Task,
    onToggleDone: () -> Unit,
    onDeleteRequest: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox para marcar/desmarcar a tarefa como concluída
            Checkbox(
                checked = task.estaConcluido,
                onCheckedChange = { onToggleDone() }
            )

            // Coluna central com Título e Descrição
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.titulo,
                    style = MaterialTheme.typography.bodyLarge,
                    // Aplica estilo riscado (LineThrough) se a tarefa estiver concluída
                    textDecoration = if (task.estaConcluido) TextDecoration.LineThrough else null,
                    color = if (task.estaConcluido) MaterialTheme.colorScheme.outline
                            else MaterialTheme.colorScheme.onSurface
                )
                
                // Exibe a descrição apenas se não estiver em branco
                if (task.descricao.isNotBlank()) {
                    Text(
                        text = task.descricao,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }

            // Botão com ícone para solicitar a exclusão da tarefa
            IconButton(onClick = onDeleteRequest) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Excluir tarefa",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}