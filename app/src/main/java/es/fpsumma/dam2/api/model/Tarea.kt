package es.fpsumma.dam2.api.model


//Aquí presentaremos los datos de la lista, y se mostrará en la pantalla.
//Aquí cree el "Tarea.kt", que automaticamente se transforma en una clase.
data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String
)