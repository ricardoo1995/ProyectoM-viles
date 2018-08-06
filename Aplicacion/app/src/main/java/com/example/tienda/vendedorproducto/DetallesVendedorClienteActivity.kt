package com.example.tienda.vendedorproducto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_detalles_vendedor.*

class DetallesVendedorClienteActivity : AppCompatActivity() {

    var vendedor: Vendedor? = null
    lateinit var producto: ArrayList<Producto>
    lateinit var adaptador: ProductoClienteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_vendedor_cliente)

        vendedor = intent.getParcelableExtra("detallesVendedorCliente")

        txtShowVendedorId.text = vendedor?.id.toString()
        txtShowNombreVendedor.text = vendedor?.nombres
        txtShowApellidoVendedor.text = vendedor?.apellidos
        txtShowFechaNacVendedor.text = vendedor?.fechaNacimiento
        txtShowSemestreVendedor.text = vendedor?.semestreActual.toString()
        txtShowGraduadoVendedor.text = if(vendedor?.graduado == 1) "Si" else "No"



        producto = BaseDatosProducto.getProductoList(vendedor?.id!!)
        Log.d("resultado",producto.toString())

        val layoutManager = LinearLayoutManager(this)
        adaptador = ProductoClienteAdapter(producto)
        recycler_view_producto.layoutManager = layoutManager
        recycler_view_producto.itemAnimator = DefaultItemAnimator()
        recycler_view_producto.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_producto)
    }
}
