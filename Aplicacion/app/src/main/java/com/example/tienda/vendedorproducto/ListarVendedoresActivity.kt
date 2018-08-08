package com.example.tienda.vendedorproducto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_listar_vendedores.*

class ListarVendedoresActivity : AppCompatActivity() {

    lateinit var adaptador: VendedorAdapter
    lateinit var vendedores: ArrayList<Vendedor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_vendedores)

        vendedores = BaseDatosVendedor.getList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = VendedorAdapter(vendedores)
        recyclerViewVendedor.layoutManager = layoutManager
        recyclerViewVendedor.itemAnimator = DefaultItemAnimator()
        recyclerViewVendedor.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerViewVendedor)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var vendedor = vendedores[position]

        when (item.itemId) {

            R.id.item_menu_editar -> {
                val intent = Intent(this, VendedorActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("vendedor", vendedor)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            BaseDatosVendedor.eliminarVendedor(vendedor.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton("No", null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            R.id.item_menu_compartir -> {
                val addressees = arrayOf("danny.alvarez@epn.edu.ec")
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_EMAIL, addressees)
                intent.putExtra(Intent.EXTRA_SUBJECT, "Vendedor Recomendado")
                intent.putExtra(Intent.EXTRA_TEXT, "Te recomiendo  este vendedor")
                startActivity(intent)
                finish()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }

    }

}
