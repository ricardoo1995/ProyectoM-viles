package com.example.tienda.vendedorproducto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_listar_vendedores_cliente.*

class ListarVendedorClienteActivity : AppCompatActivity() {

    lateinit var adaptador: VendedorClienteAdapter
    lateinit var vendedores: ArrayList<Vendedor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_vendedores_cliente)

        vendedores = BaseDatosVendedor.getList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = VendedorClienteAdapter(vendedores)
        recyclerView_listarVendedor_Clientes.layoutManager = layoutManager
        recyclerView_listarVendedor_Clientes.itemAnimator = DefaultItemAnimator()
        recyclerView_listarVendedor_Clientes.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerView_listarVendedor_Clientes)
    }
}
