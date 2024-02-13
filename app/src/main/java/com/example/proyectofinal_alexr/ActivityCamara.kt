package com.example.proyectofinal_alexr

import android.content.ContentValues
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal_alexr.databinding.ActivityCamaraBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ActivityCamara : ActivityWithMenu() {

    lateinit var imagen: ImageButton
    lateinit var binding: ActivityCamaraBinding
    private fun guardarImagenEnGaleria(image: Bitmap) {
        val imageUri = insertarImagenEnGaleria(image, "Imagen desde la Cámara",
            "Descripción")
        // Verificar que el URI no sea nulo
        if (imageUri != null) {
            // Notificar a la galería que se ha añadido una nueva imagen y mostrar en un mensaje de Log la ruta de la imagen.
            Log.d("Ruta de archivo", imageUri.toString())
            MediaScannerConnection.scanFile(
                this,
                arrayOf(imageUri.toString()), //convierte la Uri en String y la almacena en un array.
                null
            ) { _, uri ->
                // 'uri' contiene la URI del archivo escaneado
            }
        } else {
            Log.e("Error", "El URI de la imagen es nulo.")
        }

    }
    private fun insertarImagenEnGaleria(image: Bitmap, s: String, s1: String): Uri? {
        // imagesDir contiene la ubicación del almacenamiento externo
        val imagesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        // Crea un archivo para la imagen
        val imagen = File(imagesDir, "$title.jpg")
        // Guarda la imagen en el archivo usando compresión JPEG
        try {
            FileOutputStream(imagen).use { fos ->
                image.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            }
        } catch (e: IOException) {
            e.printStackTrace() //Imprime la traza de la pila en caso de error
        }
        // Inserta la imagen en la galería
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, s) //Título de la imagen en la galería
            put(MediaStore.Images.Media.DESCRIPTION, s1) //Descripción de la imagen
            put(MediaStore.Images.Media.DATA, imagen.absolutePath) //Ruta del archivo de la imagen
        }
        // Utiliza el ContentResolver para insertar la imagen en la galería
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCamaraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imagen=binding.imageButton
        val pickFoto =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                //obtenemos un objeto bitmap de los extras del resultado y lo almacenamos en la variable image
                val image = it.data?.extras?.get("data") as Bitmap
                //hacemos que la imagen se muestre en nuestro imageButton
                binding.imageButton.setImageBitmap(image)
                guardarImagenEnGaleria(image)
        }
    }





}
