package com.example.demo.ConexionBBDD;

import com.example.demo.Models.Usuario;

import java.util.List;

public interface UsuarioDAO {
    List<Usuario> getUsuario();

    void eliminar(int id);

    String registrar(Usuario usuario);

    Usuario obtenerUsuarioCredenciales(Usuario usuario);

    String consultarUsuario(int id);
}
