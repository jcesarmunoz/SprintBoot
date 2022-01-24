package com.example.demo.Controllers;

import com.example.demo.ConexionBBDD.UsuarioDAO;
import com.example.demo.Models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDAO usuarioDao;


    @RequestMapping(value="api/usuarios",method = RequestMethod.GET)
    public List<Usuario> getListUsuarios(){
        return usuarioDao.getUsuario();
    }

    @RequestMapping(value="api/usuarios/{id}",method = RequestMethod.DELETE)
    public void eliminarUsuario(@PathVariable int id){
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value="api/registro",method = RequestMethod.POST)
    public String registrarUsuarios(@RequestBody Usuario usuario){
        return usuarioDao.registrar(usuario);
    }

    @RequestMapping(value="api/consulta/{id}",method = RequestMethod.GET)
    public String consultarUsuario(@PathVariable int id){
        return usuarioDao.consultarUsuario(id);
    }

}
