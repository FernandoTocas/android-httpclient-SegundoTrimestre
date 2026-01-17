package es.fpsumma.dam2.api.ui.screen.tareas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.NoteAdd
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoTareasContent(
    state: TareasUIState,
    onBack: () -> Unit,
    onAdd: () -> Unit,
    onOpenDetalle: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de tareas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                },
                actions = {
                    IconButton(onClick = onAdd) {
                        Icon(Icons.AutoMirrored.Filled.NoteAdd, null)
                    }
                }
            )
        }
    ) { innerPadding ->

        when {
            state.loading -> {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                )
            }

            state.error != null -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(state.error)
                }
            }

            state.tareas.isEmpty() -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("No hay tareas aún")
                }
            }

            else -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(state.tareas, key = { it.id }) { tarea ->
                        Card(
                            onClick = { onOpenDetalle(tarea.id) },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            ListItem(
                                headlineContent = { Text(tarea.titulo) },
                                supportingContent = { Text(tarea.descripcion) },
                                trailingContent = {
                                    IconButton(onClick = { onDelete(tarea.id) }) {
                                        Icon(Icons.Outlined.Delete, null)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
