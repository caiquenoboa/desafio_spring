package com.meli.desafiospring.util.user;

import com.meli.desafiospring.gateway.response.UserResponse;

import java.util.Collections;
import java.util.List;

public class OrderUserResponseUtil {

    public static void order(List<UserResponse> userResponseList, String order){
        if(order != null && order.equalsIgnoreCase("name_desc")){
            userResponseList.sort(Collections.reverseOrder());
        }else{
            Collections.sort(userResponseList);
        }
    }
}
