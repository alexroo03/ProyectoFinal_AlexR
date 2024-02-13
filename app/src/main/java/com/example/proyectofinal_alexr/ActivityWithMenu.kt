package com.example.proyectofinal_alexr

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class ActivityWithMenu: AppCompatActivity() {
    companion object {
        var actividadActual = 0 // Variable estática para almacenar la actividad actual
    }

    // Método llamado para inflar el menú en la barra de acción
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú desde el archivo de recursos 'menu.xml'
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        // Deshabilitar la opción del menú que representa la actividad actual
        for (i in 0 until menu.size()) {
            if (i == actividadActual) menu.getItem(i).isEnabled = false
            else menu.getItem(i).isEnabled = true
        }
        return true
    }

    // Método llamado cuando se selecciona un elemento del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home ->  {
                // Navegar a la actividad HomeActivity
                actividadActual = 0
                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }
            R.id.personal ->  {
                // Navegar a la actividad PersonalActivity
                actividadActual = 1
                val intent = Intent(this, PersonalActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }
            R.id.visitas ->  {
                // Navegar a la actividad VisitasActivity
                actividadActual = 2
                val intent = Intent(this, VisitasActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }
            R.id.cerrar ->  {
                // Mostrar un diálogo de confirmación para cerrar sesión
                AlertDialog.Builder(this).apply{
                    setTitle("Confirmar cierre")
                    setMessage("¿Está seguro de Cerrar Sesión?")
                    setPositiveButton("Sí") { dialog, _ ->
                        actividadActual = 3
                        val intent = Intent(this@ActivityWithMenu, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                        startActivity(intent)
                    }
                    setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    show()
                }
                true
            }
            R.id.salir -> {
                // Mostrar un diálogo de confirmación para salir de la aplicación
                AlertDialog.Builder(this).apply {
                    setTitle("Confirmar salida")
                    setMessage("¿Está seguro de querer salir?")
                    setPositiveButton("Sí") { dialog, _ ->
                        actividadActual = 4
                        finishAffinity() // Finalizar todas las actividades y salir de la aplicación
                    }
                    setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    show()
                }
                true
            }
            R.id.camara ->  {
                // Navegar a la actividad ActivityCamara
                actividadActual = 5
                val intent = Intent(this, ActivityCamara::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
