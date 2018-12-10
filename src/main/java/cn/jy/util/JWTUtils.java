package cn.jy.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

public class JWTUtils {

    private static final String profiles = "XMJJ666";

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    private static SecretKey generalKey() {
        try {
            String stringKey = profiles;
            byte[] encodedKey = Base64.decodeBase64(stringKey);
            SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
            return key;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 创建jwt
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public static String createJWT(String id, String subject, Map<String, Object> claims, long ttlMillis) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            SecretKey key = generalKey();
            JwtBuilder builder = Jwts.builder()
                    .setClaims(claims)
                    .setId(id)
                    .setIssuedAt(now)
                    .setSubject(subject)
                    .signWith(signatureAlgorithm, key);
            if (ttlMillis >= 0) {
                long expMillis = nowMillis + ttlMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp);
            }
            return builder.compact();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        try {
            SecretKey key = generalKey();
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }


    public static void main(String[] args) {
       /* long time = 10000L;
        String jwt = JWTUtils.createJWT("id", "subjec", time);
        System.out.println(jwt);
        Claims claims = parseJWT(jwt);
        System.out.println(claims.getSubject());*/
       /* long time = 10000L;
        String jwt = JWTUtils.createJWT("userId", "70", -1);
        System.out.println(jwt);
        Claims claims = parseJWT(jwt);
        System.out.println(claims.getSubject());
        Claims claims1 = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ1c2VySWQiLCJpYXQiOjE1MjU3NDcxNzcsInN1YiI6IjcwIn0._jfVaFdx4T5t8qb3UeQQkzTf2FCsV-v0M9jHK5wKMNk");
        System.out.println(claims1.getSubject());*/
    }


}
