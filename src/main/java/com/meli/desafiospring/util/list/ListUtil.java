package com.meli.desafiospring.util.list;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListUtil<REQ, RESP> {

    public List<RESP> map(Function<REQ, RESP> mapper, List<REQ> listToIterable){
        return listToIterable.stream()
                                .map(mapper)
                                .collect(Collectors.toList());
    }
}
