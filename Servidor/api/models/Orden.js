/**
 * Orden.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    fecha:{
      type: 'string',
      required: true
    },
    total: {
      type: 'number',
      required: true
    },
    estado: {
      type: 'string',
      required: true
    },
    lat: {
      type: 'number',
      required: true
    },
    lng: {
      type: 'number',
      required: true
    },
    fechaEntrega: {
      type: 'string',
    },
    costoEntrega: {
      type: 'number'
    },

    usuario: {
      model: 'usuario'
    },

    detallesOrden:{
      collection: 'DetalleOrden',
      via: 'orden'
    }
  },

};

