package org.hainu.onlinedrugstore.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class Md5Util {
    //配置文件中增加加密复杂度的随机串
    @Value("${uuid}")
    String uuid;
    //配置文件中token的过期时间
    @Value("${tokenDeprecatedMinutes}")
    int tokenDeprecatedMinutes;


    /**
     * @param header  base64加密好的header
     * @param payload base64加密好的payload
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public String generateToken(String header, String payload) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //使用MD5算法对该串加密
        String current = header + payload + uuid;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] md5 = messageDigest.digest(current.getBytes(StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        for (byte i : md5) {
            stringBuilder.append(String.format("%02x", i));
        }
        return header + "." + payload + "." + stringBuilder.toString();
    }

    public  Map checkToken(String token) throws UnsupportedEncodingException, NoSuchAlgorithmException, ParseException {
        //将token分为三部分
        String[] current = token.split("\\.");

        //先检查token有没有过期
        byte[] payloads = Base64.getDecoder().decode(current[1]);
        Map payload = (Map) JSON.parse(new String(payloads, StandardCharsets.UTF_8));
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String loginTime = (String) payload.get("firstLoginTime");
        Date date = simpleFormat.parse(loginTime);
        long begin = date.getTime();
        long end = new Date().getTime();

        int gap = (int) (end - begin) / (1000 * 60);
        if (gap > tokenDeprecatedMinutes) {
            return null;
        }
        //没有过期检验是否是合法的
        if (generateToken(current[0], current[1]).equals(token)) {
            byte[] md5 = Base64.getDecoder().decode(current[1]);
            String jsonPayload = new String(md5, StandardCharsets.UTF_8);
            Map map = (Map) JSON.parse(jsonPayload);
            return map;
        }
        return null;

    }
}
