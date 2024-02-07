package com.example.proyectofinal_alexr

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class ActivityWithMenu: AppCompatActivity() {
    companion object {
        var actividadActual = 0;
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Relacionamos la clase con el layout del menú que hemos creado:
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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
                actividadActual = 3
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)

                true
            }
            R.id.salir ->  {
                actividadActual = 4
                System.exit(0)
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }
}