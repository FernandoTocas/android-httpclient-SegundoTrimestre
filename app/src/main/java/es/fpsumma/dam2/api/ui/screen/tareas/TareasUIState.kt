package es.fpsumma.dam2.api.ui.screen.tareas

data class TareasUIState(
    val tareas: List<es.fpsumma.dam2.api.viewmodel.Tarea> = emptyList(), // listado de tareas
    val loading: Boolean = false, // representa si las tareas se están cargando o no
    val error: String? = null // Indica si ha ocurrido algún error que deba informarse al usuario
)
