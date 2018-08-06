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
            "http://192.168.100.189:1337/Producto".httpPost(listOf("nombre" to producto.nombre, "codigo" to producto.codigo, "descripcion" to producto.descripcion, "activo" to producto.activo, "fechaCreacion" to producto.fechaCreacion,"numeroHorasPorSemana" to producto.numeroHorasPorSemana,"imagenProducto" to producto.imagenProducto,"vendedorId" to producto.vendedorId ))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarProducto(id: Int) {
            "http://192.168.100.189:1337/Producto/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizarProducto(producto: Producto) {
            "http://192.168.100.189:1337/Producto/${producto.id}".httpPut(listOf("nombre" to producto.nombre, "codigo" to producto.codigo, "descripcion" to producto.descripcion, "activo" to producto.activo, "fechaCreacion" to producto.fechaCreacion, "numeroHorasPorSemana" to producto.numeroHorasPorSemana))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getProductoList(vendedorId: Int): ArrayList<Producto> {
            val producto: ArrayList<Producto> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.189:1337/Producto?vendedorId=$vendedorId".httpGet().responseString()
            val jsonStringProducto = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringProducto)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as Int
                val codigo = it["codigo"] as String
                val descripcion = it["descripcion"] as String
                val activo = it["activo"] as String
                val fechaCreacion = it["fechaCreacion"] as String
                val numeroHorasPorSemana = it["numeroHorasPorSemana"] as Int
                val imagenProducto = it["imagenProducto"] as String
                val productos = Producto(id,nombre,codigo,descripcion,activo,fechaCreacion,numeroHorasPorSemana,imagenProducto,vendedorId,0,0)
                producto.add(productos)
            }
            return producto
        }




    }
}