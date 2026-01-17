package es.fpsumma.dam2.api.ui.screen.tareas

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import es.fpsumma.dam2.api.viewmodel.TareasViewModel

@Composable
fun DetalleTareasRoute (
    id: Int,
    navController: NavController,
    vm: TareasViewModel,
    modifier: Modifier = Modifier,
){


    DetalleTareaContent(
        id = id,
        navController = navController,
        vm = vm,
        modifier = modifier,
        onSave = {id, titulo, descripcion ->
            vm.updateTarea(id, titulo, descripcion)
            navController.popBackStack()
        }
    )
}
