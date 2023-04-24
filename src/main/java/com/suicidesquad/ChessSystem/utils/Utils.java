package com.suicidesquad.ChessSystem.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Utils {
    public Utils() {
    }

    public String generateJWT(String payload) throws NoSuchAlgorithmException {
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
}
