package com.example.tienda.vendedorproducto

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.view.View
import kotlinx.android.synthetic.main.activity_producto.*
import java.io.ByteArrayOutputStream


class ProductoActivity : AppCompatActivity() {

    var producto: Producto? = null
    var vendedorId: Int = 0
    private lateinit var imageBitmap: Bitmap
    var tipo = false
    lateinit var myBase64Image:String
    lateinit var myBitmapAgain:Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        vendedorId = intent.getIntExtra("vendedorId", 0)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewProducto.text = "Editar Producto"
            producto = intent.getParcelableExtra("producto")
            fillFields()
            tipo = true
        }

        btnGuardarProducto.setOnClickListener { v: View? ->
            crearProducto()
        }

        btnTomarFoto.setOnClickListener{v: View? ->
            tomarFoto()

        }
    }

    fun fillFields() {
        txtNombreProducto.setText(producto?.nombre.toString())
        txtCodigoProducto.setText(producto?.codigo)
        txtDescripcionProducto.setText(producto?.descripcion)
        txtMarcaProducto.setText(producto?.marca)
        txtModeloProducto.setText(producto?.modelo)
        txtValorProducto.setText(producto?.valorUnitario.toString())
    }

    fun crearProducto(){
        var nombre = txtNombreProducto.text.toString()
        var codigo = txtCodigoProducto.text.toString()
        var descripcion = txtDescripcionProducto.text.toString()
        var marca = txtMarcaProducto.text.toString()
        var modelo = txtModeloProducto.text.toString()
        var valorUnitario = txtValorProducto.text.toString().toInt()
        var imagenProducto = myBase64Image

        if (!tipo){
            var producto = Producto(0,nombre,codigo,descripcion,marca,modelo,valorUnitario,imagenProducto,vendedorId,0,0)
            BaseDatosProducto.insertarProducto(producto)

        }else{
            var producto2 = Producto(producto?.id!!,nombre,codigo,descripcion,marca,modelo,valorUnitario,imagenProducto,vendedorId,0,0)
            BaseDatosProducto.actualizarProducto(producto2)
        }

        finish()

    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun tomarFoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
       super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data.extras
            imageBitmap = extras!!.get("data") as Bitmap

            myBase64Image = encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG, 100)
            myBitmapAgain = decodeBase64(myBase64Image)

            imageViewProducto.setImageBitmap(myBitmapAgain)


        }

    }

    fun encodeToBase64(image: Bitmap, compressFormat: Bitmap.CompressFormat, quality: Int): String {
        val byteArrayOS = ByteArrayOutputStream()
        image.compress(compressFormat, quality, byteArrayOS)
        return android.util.Base64.encodeToString(byteArrayOS.toByteArray(), android.util.Base64.DEFAULT)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }



}

