package com.shop.sales.mapper;

/**
 * EntityMapper, interface for mapper entity
 * @param <D> DTO class
 * @param <E> Entity class
 */
public interface EntityMapper<D, E> {

  E toEntity(D dto);

  D toDto(E entity);
}
