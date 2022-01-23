package com.example.demo.Controllers;

import com.example.demo.ConexionBBDD.UsuarioDAO;
import com.example.demo.Models.Usuario;
import com.example.demo.Utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDAO usuarioDao;
    @Autowired
    private JWTUtil jtwutil;
    @RequestMapping(value="api/usuarios/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario= new Usuario();
        usuario.setId(id);
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucas@prueba.com");
        usuario.setPassword("1234567892");
        usuario.setTelefono("3112548596");
        return usuario;
    }

    @RequestMapping(value="api/usuarios",method = RequestMethod.GET)
    public List<Usuario> getListUsuarios(@RequestHeader(value="Authorization")String token){
        if (validarToken(token)) {return null;}
        return usuarioDao.getUsuario();
    }

    private boolean validarToken(String token){
        String usuarioId=jtwutil.getKey(token);
        return usuarioId!=null;
    }

    @RequestMapping(value="api/usuarios/{id}",method = RequestMethod.DELETE)
    public void eliminarUsuario(@PathVariable Long id,@RequestHeader(value="Authorization")String token){
        if (validarToken(token)) {return;}
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value="api/usuarios",method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario){
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash=argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }


}
