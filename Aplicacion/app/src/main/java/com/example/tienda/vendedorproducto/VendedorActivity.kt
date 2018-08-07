package com.example.tienda.vendedorproducto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_vendedores.*

class VendedorActivity : AppCompatActivity() {

    var vendedor: Vendedor? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendedores)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewEntreador.text = "Editar Vendedor"
            vendedor = intent.getParcelableExtra("vendedor")
            fillFields()
            tipo = true
        }

        btnGuardarVendedor.setOnClickListener { v: View? ->
            crearVendedor()
        }
    }

    fun fillFields() {
        txtNombreVendedor.setText(vendedor?.nombres)
        txtApellidoVendedor.setText(vendedor?.apellidos)
        txtFechaNacimientoVendedor.setText(vendedor?.fechaNacimiento)
        txtCedulaVendedor.setText(vendedor?.cedula.toString())
        txtTelefonoVendedor.setText(vendedor?.telefono.toString())

    }

    fun crearVendedor(){
        var nombres = txtNombreVendedor.text.toString()
        var apellidos = txtApellidoVendedor.text.toString()
        var fecha = txtFechaNacimientoVendedor.text.toString()
        var cedula = txtCedulaVendedor.text.toString()
        var telefono = txtTelefonoVendedor.text.toString()


        if (!tipo){

            var vendedor = Vendedor(0, nombres, apellidos, fecha, cedula, telefono,0,0)
            BaseDatosVendedor.insertarVendedor(vendedor)

        }else{
            var vendedor = Vendedor(vendedor?.id!!, nombres, apellidos, fecha, cedula, telefono,0,0)
            BaseDatosVendedor.actualizarVendedor(vendedor)
        }
        iraActividadVendedor()

    }

    fun iraActividadVendedor(){
        val intent = Intent(this, RegistrarUsuarios::class.java)
        intent.putExtra("valorRol","ADMINISTRADOR")
        startActivity(intent)
    }
}
