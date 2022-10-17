package com.micliente.cliente.controller;

import com.micliente.cliente.dto.ClientDTO;
import com.micliente.cliente.dto.KpiDTO;
import com.micliente.cliente.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@Api(value = "ClientesAPI")
@Slf4j
public class ClienteController {

    private ClientService clientService;

    public ClienteController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "Agrega un cliente.")
    @PostMapping(path="/creacliente", produces = "application/json")
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO cliente) {
        log.info("Metodo POST addCliente() Controller - API cliente.");
        return clientService.addClient(cliente);
    }

    @ApiOperation(value = "Kpi de clientes.")
    @GetMapping(path="/kpideclientes", produces = "application/json")
    public ResponseEntity<KpiDTO> kpiClients() {
        log.info("Metodo GET kpiClientes() Controller - API cliente.");
        return clientService.kpiClients();
    }

    @ApiOperation(value = "Lista de clientes.")
    @GetMapping(path="/listclientes", produces = "application/json")
    public ResponseEntity<List<ClientDTO>> findAllClients() {
        log.info("Metodo GET findAllClientes() Controller - API cliente.");
        return clientService.findAllClients();
    }
}
