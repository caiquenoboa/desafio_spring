package com.meli.desafiospring.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.meli.desafiospring.exception.date.DateInvalidException;
import com.meli.desafiospring.exception.date.DateNullException;
import org.apache.commons.validator.GenericValidator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private final static String DATE_FORMAT = "dd-MM-yyyy";

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getValueAsString();

        if(date.isBlank()){
            throw new DateNullException("Date cannot be null or empty");
        }

        boolean dateIsValid = GenericValidator.isDate(date, DATE_FORMAT, true);

        if(!dateIsValid){
            throw new DateInvalidException("Date format is invalid");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, formatter);
    }
}
