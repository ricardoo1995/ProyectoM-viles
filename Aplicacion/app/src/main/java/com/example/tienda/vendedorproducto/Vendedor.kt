package com.example.tienda.vendedorproducto

import android.os.Parcel
import android.os.Parcelable

class Vendedor(var id: Int, var nombres: String, var apellidos: String, var fechaNacimiento: String, var cedula: String, var telefono: String, var createdAt: Long,
                 var updatedAt: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombres)
        parcel.writeString(apellidos)
        parcel.writeString(fechaNacimiento)
        parcel.writeString(cedula)
        parcel.writeString(telefono)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vendedor> {
        override fun createFromParcel(parcel: Parcel): Vendedor {
            return Vendedor(parcel)
        }

        override fun newArray(size: Int): Array<Vendedor?> {
            return arrayOfNulls(size)
        }
    }
}