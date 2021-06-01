package com.mercadolibre.desafio_spring.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Json {
    private static ObjectMapper mapper = new ObjectMapper();

    public static List read(Class classe, String fileName) {
        File file = null;
        file = new File("src/main/resources/jsons/" + fileName);

        try {
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, classe));
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> void write(List<T> lista, String fileName) {
        String jsonString;

        try {
            jsonString = mapper.writeValueAsString(lista);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        try (FileWriter file = new FileWriter("src/main/resources/jsons/" + fileName)) {
            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
