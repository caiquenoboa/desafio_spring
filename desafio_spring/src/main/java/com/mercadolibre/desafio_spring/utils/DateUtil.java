package com.mercadolibre.desafio_spring.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static boolean isTheDateInTheLast14Days(String sDate) {
        Calendar dateOfProduct = new GregorianCalendar();
        Date date = convertStringToDate(sDate);

        dateOfProduct.setTime(date);

        Calendar twoWeeksAgo = Calendar.getInstance();
        twoWeeksAgo.add(Calendar.DATE, -14);

        return twoWeeksAgo.before(dateOfProduct);
    }

    public static Date convertStringToDate(String sDate){
        Date date = null;
        try {
            date = Date.from(LocalDate.parse(sDate, DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT)).atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            throw new RuntimeException("Formato de data não permitida");
        }
        return date;
    }

}
