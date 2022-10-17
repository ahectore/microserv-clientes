package com.micliente.cliente.service;

import com.micliente.cliente.dto.ClientDTO;
import com.micliente.cliente.dto.KpiDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {

    ResponseEntity<ClientDTO> addClient(ClientDTO cliente);
    ResponseEntity<KpiDTO> kpiClients();
    ResponseEntity<List<ClientDTO>> findAllClients();
}
