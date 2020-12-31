package com.siwoo.algo.learn;

import java.util.Date;
import java.util.TimeZone;

public class _TimeZone {

    public static void main(String[] args) {
        TimeZone timeZone = TimeZone.getDefault();      //system timezone
        String id = timeZone.getID();
        System.out.println(id);
        TimeZone parsed = TimeZone.getTimeZone(id);
        System.out.println(parsed);
        for (String ids: TimeZone.getAvailableIDs())
            if (id.equals(ids))
                System.out.println("ok");
        long epochTime = 1600263201000L;    //utc
        Date date = new Date(epochTime);    //utc to system timezone
        System.out.println(date);
    }
}
