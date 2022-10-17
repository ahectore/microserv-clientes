package com.micliente.cliente.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("KpiDTO")
public class KpiDTO {

    @ApiModelProperty(value = "Promedio de edad de los clientes.", example = "56,7")
    private Double promedioEdad;

    @ApiModelProperty(value = "Desviacion estandar de las edades de los clientes.", example = "2,4")
    private Double desviacionEstandar;
}
