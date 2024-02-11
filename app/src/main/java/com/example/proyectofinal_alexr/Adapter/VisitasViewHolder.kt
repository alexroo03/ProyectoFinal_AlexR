import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_alexr.Visitas
import com.example.proyectofinal_alexr.databinding.ItemVisitasBinding
import java.text.SimpleDateFormat

class VisitasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemVisitasBinding.bind(view)

    fun render(visitasModel: Visitas) {

        binding.tvfecha.text = visitasModel.Fecha
        binding.tvnombreempresa.text = visitasModel.Nombreempresa
        binding.tvobservaciones.text = visitasModel.Observaciones
        binding.tvtipovisita.text = visitasModel.Tipovisita
        binding.tvtrabajadoresexternos.text = visitasModel.Trabajadoresexternos
        binding.tvtrabajadorempresa.text = visitasModel.Trabajadorempresa
    }
}
