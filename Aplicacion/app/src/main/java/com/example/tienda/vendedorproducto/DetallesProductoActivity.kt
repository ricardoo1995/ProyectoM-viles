package com.example.tienda.vendedorproducto

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_detalles_producto.*


class DetallesProductoActivity : AppCompatActivity() {

    var producto: Producto? = null
    lateinit var myBitmapAgain:Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_producto)

        producto = intent.getParcelableExtra("detallesProducto")


        txtShowNombreProducto.text = producto?.nombre.toString()
        txtShowCodigoProducto.text = producto?.codigo
        txtShowDescripcion.text = producto?.descripcion
        txtShowActivo.text = producto?.activo
        txtShowFechaCreacion.text = producto?.fechaCreacion
        txtShowHoras.text = producto?.numeroHorasPorSemana.toString()

        myBitmapAgain = decodeBase64(producto?.imagenProducto.toString()!!)
        showImageViewProducto.setImageBitmap(myBitmapAgain)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }


}
