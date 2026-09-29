package com.sambinelli.taskflow.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sambinelli.taskflow.viewmodel.Filter

/**
 * Componente que renderiza uma linha horizontal de botões de filtro (Chips).
 *
 * @param activeFilter O filtro selecionado no momento.
 * @param onFilterSelected Callback acionado quando o usuário seleciona um novo filtro.
 */
@Composable
fun FilterBar(
    activeFilter: Filter,
    onFilterSelected: (Filter) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        // Percorre todas as opções do enum Filter
        Filter.entries.forEach { filter ->
            FilterChip(
                selected = activeFilter == filter,
                onClick = { onFilterSelected(filter) },
                label = {
                    Text(
                        text = when (filter) {
                            Filter.ALL -> "Todas"
                            Filter.PENDING -> "Pendentes"
                            Filter.DONE -> "Concluídas"
                        }
                    )
                }
            )
        }
    }
}