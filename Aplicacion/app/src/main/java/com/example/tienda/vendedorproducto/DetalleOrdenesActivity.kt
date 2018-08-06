package com.example.tienda.vendedorproducto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_detalles_ordenes.*


class DetalleOrdenesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var ordenes: Ordenes? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_ordenes)
        ordenes = intent.getParcelableExtra("detallesOrden")

        txtShowCedula.text = ordenes?.cedulaComprador.toString()
        txtShowSector.text = ordenes?.sector
        txtShowIdProducto.text = ordenes?.idProducto.toString()

        btnGuardarDatosOrden.setOnClickListener { v: View? ->
            guardarDatosOrdenDetalles()

        }

        btn_mapa.setOnClickListener{ v: View? ->
            irAlMapa()
        }

    }

    fun guardarDatosOrdenDetalles(){
        val fechaEnvio = txtFechaEnvio.text.toString()
        val costoProducto = txtPrecioProducto.text.toString().toInt()
        val idProducto = txtShowIdProducto.text.toString().toInt()
        val ordenDetalles = DetallesOrden(0,fechaEnvio,costoProducto,idProducto)
        BaseDatosOrdenes.insertarOrdenDetalles(ordenDetalles)
        Alerter.create(this)
                .setTitle("Orden enviada a CLIENTE")
                .setText("La solicitud fue enviada exitosamente")
                .setDuration(10000)
                .enableSwipeToDismiss()
                .setOnClickListener(View.OnClickListener {
                    irAPaginaPrincipal()
                })
                .show()


    }

    fun irAlMapa(){
        val intent = Intent(this, GoogleMapsActivity::class.java)
        startActivity(intent)
    }

    fun irAPaginaPrincipal(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
