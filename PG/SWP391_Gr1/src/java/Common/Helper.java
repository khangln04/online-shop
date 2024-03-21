/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author toden
 */
public class Helper {

    private static final String PHONE_REGEX = "^\\d{10}$";

    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static String convertToBase64(InputStream inputStream) throws IOException {
        byte[] imageBytes = new byte[inputStream.available()];
        inputStream.read(imageBytes);
        inputStream.close();

        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static StringBuilder convertToStringArray(ArrayList a) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < a.size(); i++) {
            json.append("\"").append(a.get(i)).append("\"");
            if (i < a.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        if (json != null) {
            return json;
        }
        return null;
    }

    public static StringBuilder convertToStringArrayByChart(ArrayList a) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < a.size(); i++) {
            json.append(a.get(i));
            if (i < a.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        if (json != null) {
            return json;
        }
        return null;
    }
}
