package com.micliente.cliente.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@Data
@Builder
@Slf4j
@ApiModel("ClientDTO")
public class ClientDTO {

    public static final Long PROBABILIDAD_DE_VIDA = 80L;

    @ApiModelProperty(position = 1, required = true, value = "Numero unico identificador.", example = "1")
    @NotNull
    private Long dni;

    @NotBlank
    @ApiModelProperty(position = 2, required = true, value = "Nombre del cliente.", example = "Juan")
    private String nombre;

    @NotBlank
    @ApiModelProperty(position = 3, required = true, value = "Apellido del cliente.", example = "Perez")
    private String apellido;

    @NotNull
    @ApiModelProperty(position = 4, required = true, value = "Fecha de nacimiento.", example = "03/10/2000")
    private Date fechaNacimiento;

    @ApiModelProperty(position = 5, hidden=true, value = "Edad en años del cliente.", example = "32")
    @Transient
    private Long edad;

    @ApiModelProperty(position = 6, hidden=true, value = "Fecha probable de muerte.", example = "03/10/2040")
    @Transient
    private Date fechaProbableMuerte;

    public Long getEdad() {
        return ChronoUnit.YEARS.between(this.fechaNacimiento.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
    }

    public String getFechaNacimiento() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter.format(this.fechaNacimiento);
    }

    public String getFechaProbableMuerte() {
        Long anosRestantes = PROBABILIDAD_DE_VIDA - getEdad();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(Date.from(Instant.now()));
        } catch (Exception e) {
            log.error("Error calculando fecha de muerte del cliente. '{}'", e.getMessage());
        }
        calendar.add(Calendar.YEAR, anosRestantes.intValue());
        return dateFormatter.format(calendar.getTime());
    }
}
