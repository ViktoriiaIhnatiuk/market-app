package com.example.marketapp.mapper;

public interface ResponseMapper <D, M>{
    D mapToDto(M model);
}
