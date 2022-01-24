package com.example.demo.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter  @Setter @Column(name="id")
    private int id;
    @Getter  @Setter @Column(name="nombre")
    private String nombre;
    @Getter  @Setter @Column(name="apellido")
    private String apellido;
    @Getter  @Setter @Column(name="tipo_documento")
    private String tipoDocumento;
    @Getter  @Setter @Column(name="numero_documento")
    private String numeroDocumento;
    @Getter  @Setter @Column(name="fecha_nacimiento")
    private String fechaNacimiento;
    @Getter  @Setter @Column(name="fecha_vinculacion")
    private String fechaVinculacion;
    @Getter  @Setter @Column(name="cargo")
    private String cargo;
    @Getter  @Setter @Column(name="salario")
    private String salario;
}
