package com.example.tienda.vendedorproducto

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut

class BaseDatosProducto{
    companion object {

        fun insertarProducto(producto:Producto){
            "http://172.29.22.4:1337/Producto".httpPost(listOf("nombre" to producto.nombre, "codigo" to producto.codigo, "descripcion" to producto.descripcion, "marca" to producto.marca, "modelo" to producto.modelo,"valorUnitario" to producto.valorUnitario,"imagenProducto" to producto.imagenProducto,"vendedorId" to producto.vendedorId ))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarProducto(id: Int) {
            "http://172.29.22.4:1337/Producto/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizarProducto(producto: Producto) {
            "http://172.29.22.4:1337/Producto/${producto.id}".httpPut(listOf("nombre" to producto.nombre, "codigo" to producto.codigo, "descripcion" to producto.descripcion, "marca" to producto.marca, "modelo" to producto.modelo, "valorUnitario" to producto.valorUnitario))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getProductoList(vendedorId: Int): ArrayList<Producto> {
            val producto: ArrayList<Producto> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://172.29.22.4:1337/Producto?vendedorId=$vendedorId".httpGet().responseString()
            val jsonStringProducto = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringProducto)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val codigo = it["codigo"] as String
                val descripcion = it["descripcion"] as String
                val marca = it["marca"] as String
                val modelo = it["modelo"] as String
                val valorUnitario = it["valorUnitario"] as Int
                val imagenProducto = it["imagenProducto"] as String
                val productos = Producto(id,nombre,codigo,descripcion,marca,modelo,valorUnitario,imagenProducto,vendedorId,0,0)
                producto.add(productos)
            }
            return producto
        }




    }
}