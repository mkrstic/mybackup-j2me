/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pim.util;

import java.util.Date;

/**
 *
 * @author mladen
 */
public class AppUtils {

    public static boolean isPIMSupported() {
        String version = "";
        version = System.getProperty("microedition.pim.version");
        if (version == null || !version.startsWith("1.")) {
            return false;
        } else {
            return true;
        }
    }

    public static String formatPhone(String phone) {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (Character.isDigit(c)) {
                str.append(c);
            }
        }
        return str.toString();
    }
    public static boolean isValidEmail(String email) {
        if (email != null && email.length() >= 5 && email.length() <= 50 && email.indexOf(" ") == -1 && email.indexOf("@") > 0 && email.indexOf(".") > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidEmail(String email, boolean allowEmpty) {
        if (email == null || email.equals("")) {
            if (allowEmpty) {
                return true;
            }
        }
        if (email != null && email.length() >= 5 && email.length() <= 50 && email.indexOf(" ") == -1 && email.indexOf("@") > 0 && email.indexOf(".") > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 5 && pass.length() <= 20 && pass.indexOf(" ") == -1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidPhone(String phone) {
        StringBuffer str = new StringBuffer(phone.trim());
        if (str.length() < 5) {
            return false;
        }
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (!(Character.isDigit(c) || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidActivationKey(String key) {
        try {
            Long.parseLong(key);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static long getTimeSpanInMilliSeconds(Date d1, Date d2) {
        return Math.abs(d1.getTime() - d2.getTime());
    }

    public static double getTimeSpanInMinutes(Date d1, Date d2) {
        return getTimeSpanInMilliSeconds(d1, d2) / 60000;
    }
}
