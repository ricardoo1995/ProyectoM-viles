/**
 * AplicacionController
 *
 * @description :: Server-side actions for handling incoming requests.
 * @help        :: See https://sailsjs.com/docs/concepts/actions
 */

module.exports = {

  create: async function (req, res){
    const aplicacion = req.body;
    console.log("RECIBIDO ", aplicacion);

    const appCreada = await Aplicacion.create(aplicacion).fetch();
    console.log("CREADO", appCreada);

    appCreada.detalleOrden = undefined;
    appCreada.foto = undefined;

    res.json(appCreada);

  }

};

