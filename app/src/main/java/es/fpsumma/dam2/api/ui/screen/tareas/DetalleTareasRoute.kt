package es.fpsumma.dam2.api.ui.screen.tareas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import es.fpsumma.dam2.api.ui.navegation.Routes
import es.fpsumma.dam2.api.ui.navegation.Routes.tareaAPIView
import es.fpsumma.dam2.api.viewmodel.TareasViewModel

@Composable
fun DetalleTareasRoute (
    navController: NavController,
    vm: TareasViewModel,
    modifier: Modifier = Modifier,
){

    val state by vm.state.collectAsState()

    val Tarea = backStage.arguments?.getInt("id") ?: 0,

    DetalleTareaContent(
        id = Tarea,
        navController = navController,
        vm = vm,
        modifier = modifier,
        onSave = {id, titulo, descripcion ->
            vm.updateTarea(id, titulo, descripcion)
            navController.popBackStack()
        }
    )
}
