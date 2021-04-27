package org.petdians.user.common.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatter {

    public String dateToString(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");

        if (null != date) {
            return format.format(date);
        }

        return null;

    }

}
