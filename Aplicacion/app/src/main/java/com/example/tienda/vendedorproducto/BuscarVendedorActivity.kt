package com.example.tienda.vendedorproducto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buscar_vendedor.*

class BuscarVendedorActivity : AppCompatActivity() {

    lateinit var adaptador: VendedorClienteAdapter
    lateinit var vendedores: ArrayList<Vendedor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_vendedor)


        btnBuscarVendedorServer.setOnClickListener { v: View? ->
            consultarDatos()
        }


    }

    fun consultarDatos(){
        if (txtBuscarVendedor.equals("")){
            Toast.makeText(this,"Ingrese parametro de busqueda",Toast.LENGTH_SHORT).show()
        }else{
            var datoBusqueda:String = txtBuscarVendedor.text.toString()
            

            vendedores = BaseDatosVendedor.buscarVendedor(datoBusqueda)

            //Toast.makeText(this,datoBusqueda,Toast.LENGTH_SHORT).show()

            val layoutManager = LinearLayoutManager(this)
            adaptador = VendedorClienteAdapter(vendedores)
            recyclerView_Resultados_Vendedor.layoutManager = layoutManager
            recyclerView_Resultados_Vendedor.itemAnimator = DefaultItemAnimator()
            recyclerView_Resultados_Vendedor.adapter = adaptador
            adaptador.notifyDataSetChanged()

            registerForContextMenu(recyclerView_Resultados_Vendedor)

        }
    }
}
