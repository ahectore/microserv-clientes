package com.micliente.cliente.mapper;

import com.micliente.cliente.dto.ClientDTO;
import com.micliente.cliente.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ClienteMapper extends EntityMapper<ClientDTO, Cliente> {

    ClientDTO toDto(Cliente cliente);

}
