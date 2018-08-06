module.exports = {

  attributes: {

    cantidadProducto: {
      type: 'number',
      required: true
    },
    precio: {
      type: 'number',
      required: true
    },

    fecha:{
      type: 'string',
      required: true
    },
    totalPagar: {
      type: 'number',
      required: true
    },


    factura:{
      collection: 'Factura',
      via: 'idFactura'
    },
    producto: {
      collection: 'Producto',
      via: 'idProducto'
    }

  },

};
