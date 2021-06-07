package com.mercadolivre.desafio_sring.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {
    public static String convertString(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
