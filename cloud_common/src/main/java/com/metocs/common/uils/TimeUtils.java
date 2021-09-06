package com.metocs.common.uils;

import java.text.SimpleDateFormat;


public class TimeUtils {

    public static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMATTER = "yyyy-MM-dd";

    public static final String TIME_FORMATTER = "HH:mm:ss";

    public static final String YEAR_MON = "yyyy-MM";


    private static final SimpleDateFormat dateTimeFormat  =  new SimpleDateFormat(DATETIME_FORMATTER);

    private static final SimpleDateFormat dateFormat  =  new SimpleDateFormat(DATE_FORMATTER);

    private static final SimpleDateFormat timeFormat  =  new SimpleDateFormat(TIME_FORMATTER);

    private static final SimpleDateFormat yearMonFormat  =  new SimpleDateFormat(YEAR_MON);


}
