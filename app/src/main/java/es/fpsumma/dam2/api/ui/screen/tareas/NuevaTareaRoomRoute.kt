package es.fpsumma.dam2.api.ui.screen.tareas

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import es.fpsumma.dam2.api.ui.navegation.Routes
import es.fpsumma.dam2.api.viewmodel.TareasViewModel

//El Route seería el "cerebro", lo que el usuario no ve, aquí guardamos y puede reconocer el Controller y el VM.
@Composable
fun NuevaTareaRoomRoute(
    navController: NavController,
    vm: TareasViewModel
) {
    NuevaTareaContent(
        onBack = {
            navController.popBackStack()
        },
        onSave = { titulo, descripcion ->
            vm.addTarea(titulo, descripcion)
            navController.navigate(Routes.TAREA_LISTADO)
        }
    )
}
