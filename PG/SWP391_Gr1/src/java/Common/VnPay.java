/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import jdk.internal.foreign.Utils;

/**
 *
 * @author DoanManhTai
 */
public class VnPay {

    public static final String VERSION = "2.1.0";
    private TreeMap<String, String> _requestData = new TreeMap<>();
    private TreeMap<String, String> _responseData = new TreeMap<>();

    public void addRequestData(String key, String value) {
        if (value != null && !value.isEmpty()) {
            _requestData.put(key, value);
        }
    }

    public void addResponseData(String key, String value) {
        if (value != null && !value.isEmpty()) {
            _responseData.put(key, value);
        }
    }

    public String getResponseData(String key) {
        String retValue = _responseData.get(key);
        return retValue != null ? retValue : "";
    }

    public String createRequestUrl(String baseUrl, String vnp_HashSecret) {
        StringBuilder data = new StringBuilder();
        for (Entry<String, String> entry : _requestData.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                data.append(URLEncoder.encode(entry.getKey()) + "=" + URLEncoder.encode(entry.getValue()) + "&");
            }
        }
        String queryString = data.toString();

        baseUrl += "?" + queryString;
        String signData = queryString;
        if (signData.length() > 0) {
            signData = signData.substring(0, data.length() - 1);
        }
        String vnp_SecureHash = hmacSHA512(vnp_HashSecret, signData);
        baseUrl += "vnp_SecureHash=" + vnp_SecureHash;
        return baseUrl;
    }

    public String getIpAddress(HttpServletRequest request) {
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
        }
        return ipAdress;
    }

    public static String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
    
    
}
