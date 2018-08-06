/**
 * Aplicacion.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombre: { type: 'string', required: true },
    version: { type: 'number', required: true },
    pesoEnGigas: { type: 'number', required: true},
    urlDescarga: { type: 'string', required: true },
    fechaLanzamiento: { type: 'string', required: true },
    costo: { type: 'number', required: true },

    sistemaOperativo: {
      model: 'SistemaOperativo',
    },
    detalleOrden: {
      model: 'detalleOrden'
    },
    foto: {
      model: 'foto'
    }

  },

};

