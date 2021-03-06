package com.example.tienda.vendedorproducto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_factura.*

class FacturaActivity : AppCompatActivity() {

    lateinit var adaptador: OrdenesPendientesAdaptador
    lateinit var ordenes: ArrayList<Ordenes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factura)

        ordenes = BaseDatosOrdenes.getOrdenesList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = OrdenesPendientesAdaptador(ordenes)
        recyclerView_ordenes.layoutManager = layoutManager
        recyclerView_ordenes.itemAnimator = DefaultItemAnimator()
        recyclerView_ordenes.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerView_ordenes)
    }
}
