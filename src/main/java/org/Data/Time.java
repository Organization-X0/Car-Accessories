package org.Data;

import java.util.ArrayList;

public enum Time {
    T8_9,
    T9_10,
    T10_11,
    T11_12,
    T12_13,
    T13_14,
    T14_15,
    T15_16;
    public static Time fromInteger(int x) {
        Time time=null;
        switch(x) {
            case 0 -> time=T8_9;
            case 1 -> time=T9_10;
            case 2 -> time=T10_11;
            case 3 -> time=T11_12;
            case 4 -> time=T12_13;
            case 5 -> time=T13_14;
            case 6 -> time=T14_15;
            case 7 -> time=T15_16;
        }
        return time;
    }
    public static String timeToPrint(Time time){
        String str="";
        switch (time){
            case T8_9 -> str="8:00-9:00";
            case T9_10 -> str="9:00-10:00";
            case T10_11 -> str="10:00-11:00";
            case T11_12 -> str="11:00-12:00";
            case T12_13 -> str="12:00-13:00";
            case T13_14 -> str="13:00-14:00";
            case T14_15 -> str="14:00-15:00";
            case T15_16 -> str="15:00-16:00";
        }
        return str;
    }
}
