package com.cristianomoraes.libri.helpers;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DataFormata {

    private String dateFormat = "dd-MM-yyyy HH:mm:ss";
    private String locale = "pt-BR";
    SimpleDateFormat sdf= null;

    public DataFormata() {

        this.sdf = new SimpleDateFormat(this.dateFormat, new Locale(this.locale));

    }

    public String getDateFormat(){

        return sdf.format(new Date());

    }
}
