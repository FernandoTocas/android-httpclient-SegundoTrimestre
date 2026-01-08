package es.fpsumma.dam2.api.ui.screen.tareas

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import es.fpsumma.dam2.api.ui.navegation.Routes
import es.fpsumma.dam2.api.viewmodel.TareasViewModel
import java.lang.reflect.Modifier

@Composable
fun ListadoTareasRoomRoute(
  navController: NavController,
  vm: TareasViewModel
) {
  val ui by vm.state.collectAsState()

  ListadoTareasContent(
    ui = ui,
    onBack = { navController.popBackStack() },
    onAdd = { navController.navigate(Routes.TAREA_ADD) },
    onOpenDetalle = { id -> navController.navigate(Routes.tareaView(id)) },
    onDelete = { id -> vm.deleteTareaById(id) }
  )
}