package com.suicidesquad.ChessSystem.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.hash.Hashing;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Utils {
    public static final int ONE_DAY = 24*60*60;

    public Utils() {
    }

    public static String generateJWT(String payload) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        Builder tokenBuilder = JWT.create()
                .withIssuer("https://keycloak.quadmeup.com/auth/realms/Realm")
                .withClaim("jti", UUID.randomUUID().toString())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(300)))
                .withIssuedAt(Date.from(Instant.now()));

        tokenBuilder.withClaim(payload, payload);
        return  tokenBuilder.sign(Algorithm.RSA256(((RSAPublicKey) keyPair.getPublic()), ((RSAPrivateKey) keyPair.getPrivate())));
    }

     public static String encodeString(String payload){
        return Hashing.sha256().hashString(payload, StandardCharsets.UTF_8).toString();
    }
    public static byte[] compressImage(byte[] data) {
        byte[] encoded = Base64.getEncoder().encode(data);
        return encoded;
    }

    public static byte[] decompressImage(byte[] data) {
        byte[] decoded = Base64.getDecoder().decode(data);
        return decoded;
    }
}
