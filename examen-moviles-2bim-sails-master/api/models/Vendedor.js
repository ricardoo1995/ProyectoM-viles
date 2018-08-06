module.exports = {

  attributes: {

    nombre: {
      type: 'string',
      required: true
    },
    apellido: {
      type: 'string',
      required: true
    },
    cedula: {
      type: 'string',
      required: true
    },
    telefono: {
      type: 'string',
      required: true
    },
    direccion: {
      type: 'string',
      required: true
    },
    fechaNacimiento: {
      type: 'string',
      required: true
    },
    orden:{
      collection: 'DetalleOrden',
      via: 'idOrden'
    }

  },

};
