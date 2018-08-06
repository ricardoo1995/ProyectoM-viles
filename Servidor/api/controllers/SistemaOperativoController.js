/**
 * SistemaOperativoController
 *
 * @description :: Server-side actions for handling incoming requests.
 * @help        :: See https://sailsjs.com/docs/concepts/actions
 */

module.exports = {

  devolver_apps: async function(req, res){
    const os_id = req.params.id;
    const aplicaciones = await Aplicacion.find({ sistemaOperativo: os_id });

    const aplicacionesFoto = await Promise.all(aplicaciones.map(async (aplicacion)=> {
      aplicacion.foto = await Foto.findOne({aplicacion: aplicacion.id});
      return aplicacion;
    }));


    console.log(aplicacionesFoto);
    res.ok(aplicacionesFoto);

  }

};

