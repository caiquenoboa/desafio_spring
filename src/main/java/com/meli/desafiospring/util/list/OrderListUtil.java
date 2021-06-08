package com.meli.desafiospring.util.list;

import java.util.Collections;
import java.util.List;

public class OrderListUtil {

    public static <T extends Comparable<? super T>> void order(String order, List<T> responseList, String fieldName) {
        if(!order.contains(fieldName)){
            if(order.contains("date")){
                sortDesc(responseList);
            }else{
                sortAsc(responseList);
            }
            return;
        }

        if(order.contains("asc")){
            sortAsc(responseList);
        }else{
            sortDesc(responseList);
        }
    }

    private static <T extends Comparable<? super T>> void sortDesc(List<T> responseList) {
        responseList.sort(Collections.reverseOrder());
    }

    private static <T extends Comparable<? super T>> void sortAsc(List<T> responseList) {
        Collections.sort(responseList);
    }
}
