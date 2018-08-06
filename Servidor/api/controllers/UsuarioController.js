/**
 * UsuarioController
 *
 * @description :: Server-side actions for handling incoming requests.
 * @help        :: See https://sailsjs.com/docs/concepts/actions
 */

module.exports = {

  login: async function(req, res){
    const usuarioRecv = req.body;
    const usuarioQuery = await Usuario.findOne({ username: usuarioRecv.username, password: usuarioRecv.password });

    if(!usuarioQuery){
      res.notFound();
    }else{
      res.ok(usuarioQuery);
    }

  }

};

