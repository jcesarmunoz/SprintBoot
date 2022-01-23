package com.example.demo.ConexionBBDD;

import com.example.demo.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Usuario> getUsuario() {
        String query="from Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario=entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioCredenciales(Usuario usuario) {
        String query="from Usuario Where email=:email";
        List<Usuario> lista=entityManager.createQuery(query)
                .setParameter("email",usuario.getEmail())
                .getResultList();
        if(lista.isEmpty()){
            return null;
        }
        String passwordHashed= lista.get(0).getPassword();
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed,usuario.getPassword())){
            return lista.get(0);
        }else{
            return null;
        }
    }
}
