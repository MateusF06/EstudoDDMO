package com.sambinelli.taskflow.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

/**
 * Diálogo de confirmação para prevenir a remoção acidental de tarefas.
 *
 * @param taskTitle Título da tarefa que será excluída (para exibição na mensagem).
 * @param onConfirm Action a ser executada ao confirmar a exclusão.
 * @param onDismiss Action a ser executada ao cancelar ou fechar o diálogo.
 */
@Composable
fun DeleteConfirmDialog(
    taskTitle: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = { Icon(imageVector = Icons.Default.Warning, contentDescription = null) },
        title = { Text(text = "Excluir tarefa?") },
        text = { Text(text = "A tarefa \"$taskTitle\" será removida permanentemente. Deseja continuar?") },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text(text = "Excluir")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        }
    )
}