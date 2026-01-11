package es.fpsumma.dam2.api.ui.screen.tareas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.fpsumma.dam2.api.ui.navegation.Routes
import es.fpsumma.dam2.api.viewmodel.TareasViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListadoTareasScreen(
    navController: NavController,
    vm: TareasViewModel,
    modifier: Modifier = Modifier
) {

    //Arreglamos el error, debido a que dentro del "vm" teemos la variable llamada state.
    val state by vm.state.collectAsState()


    //Ambos botones que por defecto deberían ir el de "Volver": popBack y "Añadir": TAREA_ADD_API
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de tareas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    Row {
                        IconButton(onClick = { navController.navigate(Routes.TAREA_ADD_API) }) {
                            Icon(Icons.AutoMirrored.Filled.NoteAdd, contentDescription = "Añadir")
                        }

                    }
                }
            )
        }
    ) { innerPadding ->

        //Agregué un "When para las dos diferentes condiciones.
        //Es más potente que usar un Switch.
        when {
            state.loading -> { //Primero el estado de carga, que se verá al momento que se intente ingresar a la lista con el siguiente mensaje..
                Text(
                    text = "Cargando...",
                    modifier = modifier.padding(innerPadding).padding(16.dp)
                )
            }

            state.error != null -> { //Segundo el mensaje de "error", que le informará al usuario una vez se detecte el error.
                Text(
                    text = state.error!!, //Para que detecte el error es necesario usar el "!!"
                    modifier = modifier.padding(innerPadding).padding(16.dp)
                )
            }

            state.tareas.isEmpty() -> { //Tercero, llamamos a la lista de tareas una cez termina de cargar, y en caso no se de un error "state.error"
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
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
                    items(
                        items = state.tareas,
                        key = { it.id }
                    ) { tarea ->
                        Card(
                            onClick = {
                                navController.navigate(Routes.tareaAPIView(tarea.id))
                            },
                            shape = RoundedCornerShape(16.dp),
                            modifier = modifier.padding(8.dp)
                        ) {
                            ListItem(
                                headlineContent = { Text(tarea.titulo) },
                                supportingContent = { Text(tarea.descripcion) },
                                trailingContent = {
                                    IconButton(onClick = {
                                        vm.deleteTareaById(tarea.id)
                                    }) {
                                        Icon(Icons.Outlined.Delete, contentDescription = "Borrar")
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