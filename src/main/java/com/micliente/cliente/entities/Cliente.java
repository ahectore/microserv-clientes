package com.micliente.cliente.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="CLIENTE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	@Id
    @Column(name = "DNI")
	private Long dni;
	
	@Column(name = "NOMBRE")
	@NotBlank
	private String nombre;
	
	@Column(name = "APELLIDO")
	@NotBlank
	private String apellido;
	
	@Column(name = "FECHA_NACIMIENTO")
	@NotNull
	private Date fechaNacimiento;
}
