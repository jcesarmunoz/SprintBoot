package com.example.demo.ConexionBBDD;

import com.example.demo.Models.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDAO {

    private final DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Usuario> getUsuario() {
        String query="FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(int id) {
        Usuario usuario=entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public String registrar(Usuario usuario) {
        Date fechaNacimiento;
        Date fechaVinculacion;
        try{
            String nombre=verificarCampo(usuario.getNombre());;
            String apellido=verificarCampo(usuario.getApellido());
            String tipoDocumento=verificarCampo(usuario.getTipoDocumento());
            String numeroDocumento=verificarCampo(usuario.getNumeroDocumento());
            fechaNacimiento=verificarFecha(usuario.getFechaNacimiento());
            fechaVinculacion=verificarFecha(usuario.getFechaVinculacion());
            String cargo=verificarCampo(usuario.getCargo());
            String Salario=verificarCampo(usuario.getCargo());
        }catch (NullPointerException e){
            return "FAIL";
        }catch (ParseException e){
            return "errorDate";
        }catch (Exception e){
            return"FAIL";
        }

        try{
            if (verificarMayorEdad(usuario.getFechaNacimiento())){
                if(fechaVinculacion.after(fechaNacimiento)){
                    entityManager.merge(usuario);
                    return "OK";
                }else{
                    return "errorVinculacion";
                }
            }else{
                return "errorNacimiento";
            }
        }catch (ParseException e){
            return "errorDate";
        }
    }

    private String verificarCampo(String campo)throws NullPointerException{
        if(campo==null || campo.equals("")){
           throw new NullPointerException();
        }else{
            return campo;
        }
    }

    private Date verificarFecha(String fecha)throws ParseException,NullPointerException{
        if(fecha==null || fecha.equals("")){
            throw new NullPointerException();
        }
        return dateFormat.parse(fecha);

    }

    private String difFecha(String fecha) throws ParseException {
        LocalDate fechaActual= LocalDate.now();
        Date fechaComparar= dateFormat.parse(fecha);
        LocalDate localFechaComparar=fechaComparar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period=Period.between(localFechaComparar,fechaActual);
        int anios=period.getYears();
        int meses=period.getMonths();
        int dias=period.getDays();
        return anios + " años, " + meses + " meses, " + dias + " dias";
    }



    private boolean verificarMayorEdad(String fecha)throws ParseException{
        LocalDate fechaActual= LocalDate.now();
        Date fechaComparar= dateFormat.parse(fecha);
        LocalDate localFechaComparar=fechaComparar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period=Period.between(localFechaComparar,fechaActual);
        int anios=period.getYears();
        if(anios>=18){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuarioCredenciales(Usuario usuario) {
        /*String query="from Usuario Where email=:email";
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
        }*/
        return usuario;
    }

    @Override
    public String consultarUsuario(int id) {
        String query="FROM Usuario Where id='" + id + "'";
        List<Usuario> list=entityManager.createQuery(query).getResultList();
        String fechaNacimiento=list.get(0).getFechaNacimiento();
        String fechaVinculacion=list.get(0).getFechaVinculacion();
        String difNacimiento="";
        String difVinculacion="";
        try {
            difNacimiento=difFecha(fechaNacimiento);
            difVinculacion=difFecha(fechaVinculacion);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String cadena="diferencia nacimiento: " + difNacimiento + "\ndiferencia vinculacion: " + difVinculacion;

        return cadena;
    }
}
