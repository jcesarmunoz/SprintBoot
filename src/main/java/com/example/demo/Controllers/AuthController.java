package com.example.demo.Controllers;

import com.example.demo.ConexionBBDD.UsuarioDAO;
import com.example.demo.Models.Usuario;
import com.example.demo.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value="api/login",method = RequestMethod.POST)
    public String loginUser(@RequestBody Usuario usuario){
        Usuario usuarioVerificado=usuarioDao.obtenerUsuarioCredenciales(usuario);

        if(usuarioVerificado!=null){
            String tokenJwt=jwtUtil.create(String.valueOf(usuarioVerificado.getId()),usuarioVerificado.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }


}
