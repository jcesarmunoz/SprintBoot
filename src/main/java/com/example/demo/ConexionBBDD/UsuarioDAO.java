package com.example.demo.ConexionBBDD;

import com.example.demo.Models.Usuario;

import java.util.List;

public interface UsuarioDAO {
    List<Usuario> getUsuario();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioCredenciales(Usuario usuario);
}
