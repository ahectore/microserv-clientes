package com.micliente.cliente.service;

import com.micliente.cliente.dto.ClientDTO;
import com.micliente.cliente.dto.KpiDTO;
import com.micliente.cliente.entities.Cliente;
import com.micliente.cliente.mapper.ClienteMapper;
import com.micliente.cliente.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public ResponseEntity<ClientDTO> addClient(ClientDTO cliente) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            clientRepository.save(Cliente.builder()
                    .dni(cliente.getDni())
                    .nombre(cliente.getNombre())
                    .apellido(cliente.getApellido())
                    .fechaNacimiento(dateFormatter.parse(cliente.getFechaNacimiento()))
                    .build());
        } catch (Exception e) {
            log.error("Error guardando el cliente. '{}'", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<KpiDTO> kpiClients() {
        AtomicReference<Long> totalAge= new AtomicReference<>(0L);
        AtomicReference<Integer> totalClients= new AtomicReference<>(0);
        AtomicReference<Double> varianza = new AtomicReference<>(0.0);
        Double mediaAritmetica;
        Double desviacionStandard;
        List<ClientDTO> clientDTOList;
        try {
            clientDTOList = findClients();
            clientDTOList.forEach(
                    client-> {
                        totalAge.updateAndGet(v -> v + client.getEdad());
                        totalClients.getAndSet(totalClients.get() + 1);
                    }
            );
        } catch(Exception e) {
            log.error("Error en la busqueda de cliente. '{}'", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        //Calcular la media aritmetica
        mediaAritmetica = totalAge.get() / Double.valueOf(totalClients.get());

        //Calcular la varianza
        clientDTOList.forEach(
                clienteDTO -> {
                    double rango;
                    rango = Math.pow(clienteDTO.getEdad() - mediaAritmetica, 2f);
                    varianza.set(varianza.get() + rango);
                }
        );
        varianza.set(varianza.get() / Double.valueOf(totalClients.get()));
        //Calcular la desviación standarr
        desviacionStandard = Math.sqrt(varianza.get());

        return ResponseEntity.ok(KpiDTO.builder().promedioEdad(mediaAritmetica).desviacionEstandar(desviacionStandard).build());
    }

    @Override
    public ResponseEntity<List<ClientDTO>> findAllClients() {
        List<ClientDTO> clientDTOList;
        try {
            clientDTOList = findClients();
        } catch(Exception e) {
            log.error("Error en la busqueda de cliente. '{}'", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (clientDTOList.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.ok(clientDTOList);
    }

    private List<ClientDTO> findClients() {
        return clientRepository.findAll()
                .stream()
                .map(clienteMapper::toDto)
                .toList();
    }
}
