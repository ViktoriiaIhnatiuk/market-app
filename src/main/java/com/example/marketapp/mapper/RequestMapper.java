package com.example.marketapp.mapper;

public interface RequestMapper<D, M>{
    M mapToModel(D dto);
}
