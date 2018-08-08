package com.example.tienda.vendedorproducto

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buscar_vendedor.*
import java.util.*

class BuscarVendedorActivity : AppCompatActivity() {

    lateinit var adaptador: VendedorClienteAdapter
    lateinit var vendedores: ArrayList<Vendedor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_vendedor)


       // btnBuscarVendedorServer.setOnClickListener { v: View? ->
         //   consultarDatos()
        //}
        btnBuscarVendedorServer.setOnClickListener { v: View? ->
            consultarDatos()
        }
        //btn_escucharVoz.setOnClickListener {
          //  v: View? -> getSpeechInput(v)
        //}


    }

    fun getSpeechInput (view: View?){
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(intent,10)
        }
        else{
            Toast.makeText(this,"\n" +
                    "Su dispositivo no es compatible con la entrada de voz", Toast.LENGTH_SHORT ).show()
        }
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent){
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            10 -> if (resultCode == Activity.RESULT_OK){
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                txtBuscarVendedor.setText(result.get(0))
                consultarDatos()

            }
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
