package com.sambinelli.taskflow.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Componente que renderiza a seção/card de entrada para adicionar uma nova tarefa.
 *
 * @param titulo Texto atual do campo de título.
 * @param descricao Texto atual do campo de descrição.
 * @param onTitleChange Callback disparado ao digitar no campo título.
 * @param onDescriptionChange Callback disparado ao digitar no campo descrição.
 * @param onAddClick Callback disparado ao clicar no botão de adicionar.
 */
@Composable
fun AddTaskSection(
    titulo: String,
    descricao: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onAddClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Nova Tarefa",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo para o Título (obrigatório)
            OutlinedTextField(
                value = titulo,
                onValueChange = onTitleChange,
                label = { Text("Título *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo para a Descrição (opcional)
            OutlinedTextField(
                value = descricao,
                onValueChange = onDescriptionChange,
                label = { Text("Descrição (opcional)") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Botão de submissão (desabilitado se o título estiver em branco)
            Button(
                onClick = onAddClick,
                modifier = Modifier.align(Alignment.End),
                enabled = titulo.isNotBlank()
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Adicionar")
            }
        }
    }
}