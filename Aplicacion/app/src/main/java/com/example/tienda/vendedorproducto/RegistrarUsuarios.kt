package com.example.tienda.vendedorproducto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_registrar_usuarios.*


class RegistrarUsuarios : AppCompatActivity() {

    lateinit var rol:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuarios)


        rol = intent.getStringExtra("valorRol")


        if (rol.equals("ADMINISTRADOR",true)){
            btnBuscarVendedores.visibility = View.INVISIBLE

        }else if (rol.equals("FACTURA",true)){
            btnBuscarVendedores.visibility = View.INVISIBLE
            btnCrearVendedor.visibility = View.INVISIBLE
            btnListarVendedor.visibility = View.INVISIBLE
            irActividadDelivery()

        }else{
            btnCrearVendedor.visibility = View.INVISIBLE
            btnListarVendedor.visibility = View.INVISIBLE
        }


      btnCrearVendedor.setOnClickListener { v: View? ->
          irVendedorActivity()
      }

        btnListarVendedor.setOnClickListener { v: View? ->
            irListarVendedorActivity()
        }

        btnBuscarVendedores.setOnClickListener { v: View? ->
            irBuscarVendedorActivity()
        }

        btn_salirsistema.setOnClickListener { v: View? ->
            irMainActivity()
        }


    }

    fun irVendedorActivity(){
        val intent = Intent(this, VendedorActivity::class.java)
        intent.putExtra("tipo", "Create")
        startActivity(intent)
    }

    fun irListarVendedorActivity(){
        val intent = Intent(this, ListarVendedoresActivity::class.java)
        startActivity(intent)
    }

    fun irActividadDelivery(){
        val intent = Intent(this,DeliveryActivity::class.java)
        startActivity(intent)

    }

    fun irBuscarVendedorActivity(){
        val intent = Intent(this,BuscarVendedorActivity::class.java)
        startActivity(intent)

    }

    fun irMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }


}
