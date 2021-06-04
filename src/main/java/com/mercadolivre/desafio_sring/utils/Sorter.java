package com.mercadolivre.desafio_sring.utils;

import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.Optional;

public abstract class Sorter {
    public static Sort getSort(Map<String, String> mapFieldSort, Optional<String> stringSort) {
        Sort defaultSort = Sort.by(Sort.Direction.ASC, mapFieldSort.get("default"));

        if (stringSort.isEmpty()) return defaultSort;

        String[] stringSplited = stringSort.get().split("_");

        if (stringSplited.length != 2) return defaultSort;

        String filedNameSort = mapFieldSort.get(stringSplited[0]);

        if (filedNameSort == null) return defaultSort;

        Sort.Direction sortDirection = stringSplited[1].equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        return Sort.by(sortDirection, filedNameSort);
    }
}
