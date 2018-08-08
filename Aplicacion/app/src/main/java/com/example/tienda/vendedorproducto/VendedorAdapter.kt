package com.example.tienda.vendedorproducto

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView

class VendedorAdapter(private val vendedorList: List<Vendedor>) :  RecyclerView.Adapter<VendedorAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombre: TextView
        var apellido : TextView
        var fechaNacimiento: TextView
        var detalles: Button

        lateinit var vendedor: Vendedor

        init {
            nombre = view.findViewById(R.id.txtNombreVendedor) as TextView
            apellido = view.findViewById(R.id.txtApellidoVendedor) as TextView
            fechaNacimiento = view.findViewById(R.id.txtFechaNacimientoVendedor) as TextView
            detalles = view.findViewById(R.id.btnDetallesVendedo) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Editar")
            menu?.add(Menu.NONE, R.id.item_menu_eliminar, Menu.NONE, "Eliminar")
            menu?.add(Menu.NONE, R.id.item_menu_compartir, Menu.NONE, "Compartir")

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_vendedor_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val vendedor = vendedorList[position]
        holder.nombre.text = vendedor.nombres
        holder.apellido.text = vendedor.apellidos
        holder.fechaNacimiento.text = vendedor.fechaNacimiento
        holder.vendedor = vendedor
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesVendedorActivity::class.java)
            intent.putExtra("detallesVendedor", vendedor)

            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return vendedorList.size
    }


}