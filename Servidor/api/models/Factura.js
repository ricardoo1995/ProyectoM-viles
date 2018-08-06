module.exports = {

  attributes: {


    fecha: {
      type: 'string',
      required: true
    },
    cliente:{
      collection: 'Cliente',
      via: 'idCliente'
    }

  },

};
