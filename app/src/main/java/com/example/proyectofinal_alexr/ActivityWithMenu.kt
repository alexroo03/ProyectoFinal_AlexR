package com.example.proyectofinal_alexr

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class ActivityWithMenu: AppCompatActivity() {
    companion object {
        var actividadActual = 0;
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Relacionamos la clase con el layout del menú que hemos creado:
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)


        for (i in 0 until   menu.size()) {
            if (i== actividadActual) menu.getItem(i).isEnabled =false
            else menu.getItem(i).isEnabled= true
        }
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home ->  {
                actividadActual = 0
                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)

                true
            }

            R.id.personal ->  {
                actividadActual = 1
                val intent = Intent(this, PersonalActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)

                true
            }

            R.id.visitas ->  {
                actividadActual = 2
                val intent = Intent(this, VisitasActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)

                true
            }

            R.id.cerrar ->  {
                AlertDialog.Builder(this).apply{
                    setTitle("Confirmar cierre")
                    setMessage("¿Está seguro de Cerrar Sesion?")
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
                AlertDialog.Builder(this).apply {
                    setTitle("Confirmar salida")
                    setMessage("¿Está seguro de querer Salir?")
                    setPositiveButton("Sí") { dialog, _ ->
                        actividadActual = 4
                        finishAffinity()
                    }
                    setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    show()
                }
                true
            }
            R.id.camara ->  {
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