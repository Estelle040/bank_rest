package com.example.bankcards.util;

public class MaskUtil {
    public static String mask16(String num) {
        if (num == null) return null;
        String digits = num.replaceAll("\\D", "");
        if (digits.length() != 16) return "**** **** **** " + digits.substring(digits.length()-4);
        return "**** **** **** " + digits.substring(12);
    }
}
