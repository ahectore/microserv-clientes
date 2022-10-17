package com.micliente.cliente.mapper;

import java.util.List;

public interface EntityMapper<D, E> {
    D toDto(E entity);
    List<D> toDto(List<E> entityList);
}
