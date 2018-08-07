package com.example.tienda.vendedorproducto

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.*

class BaseDatosVendedor{

    companion object {

        fun insertarVendedor(vendedor:Vendedor){
            "http://192.168.100.134:1337/Vendedor".httpPost(listOf("nombres" to vendedor.nombres, "apellidos" to vendedor.apellidos, "fechaNacimiento" to vendedor.fechaNacimiento, "cedula" to vendedor.cedula, "telefono" to vendedor.telefono))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarVendedor(id: Int) {
            "http://192.168.100.134:1337/Vendedor/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizarVendedor(vendedor: Vendedor) {
            "http://192.168.100.134:1337/Vendedor/${vendedor.id}".httpPut(listOf("nombres" to vendedor.nombres, "apellidos" to vendedor.apellidos, "fechaNacimiento" to vendedor.fechaNacimiento, "cedula" to vendedor.cedula, "telefono" to vendedor.telefono))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Vendedor> {
            val vendedores: ArrayList<Vendedor> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.134:1337/Vendedor".httpGet().responseString()
            val jsonStringVendedor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringVendedor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombres = it["nombres"] as String
                val apellidos = it["apellidos"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val cedula = it["cedula"] as String
                val telefono = it["telefono"] as String
                val vendedor = Vendedor(id, nombres, apellidos, fechaNacimiento, cedula, telefono, 0, 0)
                vendedores.add(vendedor)
            }
            return vendedores
        }

        fun buscarVendedor(nombre:String): ArrayList<Vendedor> {
            val vendedores: ArrayList<Vendedor> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.134:1337/Vendedor?nombres=${nombre}".httpGet().responseString()
            val jsonStringVendedor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringVendedor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombres = it["nombres"] as String
                val apellidos = it["apellidos"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val cedula = it["cedula"] as String
                val telefono = it["telefono"] as String
                val vendedor = Vendedor(id, nombres, apellidos, fechaNacimiento, cedula, telefono, 0, 0)
                vendedores.add(vendedor)
            }
            return vendedores
        }

    }
}