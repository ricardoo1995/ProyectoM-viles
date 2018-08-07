package com.example.tienda.vendedorproducto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_detalles_vendedor.*

class DetallesVendedorActivity : AppCompatActivity() {

    var vendedor: Vendedor? = null
    lateinit var producto: ArrayList<Producto>
    lateinit var adaptador: ProductoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_vendedor)



        vendedor = intent.getParcelableExtra("detallesVendedor")

        txtShowVendedorId.text = vendedor?.id.toString()
        txtShowNombreVendedor.text = vendedor?.nombres
        txtShowApellidoVendedor.text = vendedor?.apellidos
        txtShowFechaNacVendedor.text = vendedor?.fechaNacimiento
        txtShowCedulaVendedor.text = vendedor?.cedula.toString()
        txtShowTelefonoVendedor.text = vendedor?.telefono



        producto = BaseDatosProducto.getProductoList(vendedor?.id!!)
       Log.d("resultado",producto.toString())

       val layoutManager = LinearLayoutManager(this)
        adaptador = ProductoAdapter(producto)
        recycler_view_producto.layoutManager = layoutManager
        recycler_view_producto.itemAnimator = DefaultItemAnimator()
        recycler_view_producto.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_producto)

        btnNuevaProducto.setOnClickListener { v: View? ->
            irActividdadCrearProducto()
        }


    }

    fun irActividdadCrearProducto(){
        val intent = Intent(this, ProductoActivity::class.java)
        intent.putExtra("tipo", "Create")
        intent.putExtra("vendedorId", vendedor?.id!!)
        startActivity(intent)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var productos = producto[position]

        when (item.itemId) {

            R.id.item_menu_editar -> {
                val intent = Intent(this, ProductoActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("producto", productos)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            BaseDatosProducto.eliminarProducto(productos.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton("No", null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
