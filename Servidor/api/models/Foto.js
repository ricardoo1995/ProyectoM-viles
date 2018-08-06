/**
 * Foto.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    datos: {
      type: 'string',
    required: true
    },
    extension: {
      type: 'string',
      required: true
    },
    aplicacion: {
      model: 'aplicacion',
    }


  },

};

