package com.example.tienda.vendedorproducto

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_detalles_producto_cliente.*

class DetallesProductoClienteActivity : AppCompatActivity() {

    var producto: Producto? = null
    lateinit var myBitmapAgain: Bitmap
    lateinit var idProducto:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_producto_cliente)

        producto = intent.getParcelableExtra("detallesProductoCliente")



        txtShowNombreProductoM.text = producto?.nombre.toString()
        txtShowCodigoProductoM.text = producto?.codigo
        txtShowDescripcionM.text = producto?.descripcion
        txtShowMarcaM.text = producto?.marca
        txtShowModeloM.text = producto?.modelo
        txtShowValorM.text = producto?.valorUnitario.toString()



        myBitmapAgain = decodeBase64(producto?.imagenProducto!!)
        showImageViewProductoM.setImageBitmap(myBitmapAgain)

        btnAdquirirProducto.setOnClickListener { v ->
            irActividadDatosComprador()
        }


    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    fun irActividadDatosComprador(){
        val intent = Intent(this, CompradorActivity::class.java)
        idProducto = producto?.id.toString()
        intent.putExtra("idProducto",idProducto)
        startActivity(intent)
    }


}
