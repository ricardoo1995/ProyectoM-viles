package com.example.tienda.vendedorproducto

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView

class ProductoAdapter(private val productoList: List<Producto>) :  RecyclerView.Adapter<ProductoAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var codigo: TextView
        var descripcion : TextView
        var marca: TextView
        var detalles: Button

        lateinit var productoM1: Producto

        init {
            codigo = view.findViewById(R.id.txtNombreVendedor) as TextView
            descripcion = view.findViewById(R.id.txtApellidoVendedor) as TextView
            marca = view.findViewById(R.id.txtFechaNacimientoVendedor) as TextView
            detalles = view.findViewById(R.id.btnDetallesVendedo) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
             menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Editar")
             menu?.add(Menu.NONE, R.id.item_menu_eliminar, Menu.NONE, "Eliminar")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_vendedor_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productoM = productoList[position]
        holder.codigo.text = productoM.codigo
        holder.descripcion.text = productoM.descripcion
        holder.marca.text = productoM.marca
        holder.productoM1 = productoM
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesProductoActivity::class.java)
            intent.putExtra("detallesProducto", productoM)
            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return productoList.size
    }


}