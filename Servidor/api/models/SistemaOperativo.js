/**
 * SistemaOperativo.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombre: { type: 'string', required: true },
    version: { type: 'number', required: true },
    fechaLanzamiento: { type: 'string', required: true },
    pesoEnGigas: { type: 'number', required: true },
    instalado: { type: 'boolean', required: true},

    aplicaciones: {
      collection: 'aplicacion',
      via: 'sistemaOperativo'
    }

  },

};

