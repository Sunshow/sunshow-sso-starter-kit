package jws;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class JWSTest {

    Algorithm algorithm;

    RSAPublicKey publicKey;

    RSAPrivateKey privateKey;

    @BeforeEach
    public void init() throws Exception {
        //initHS256();
        initRS256();
    }

    public void initHS256() {
        //HMAC
        algorithm = Algorithm.HMAC256("secret");
    }

    public void initRS256() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //RSA
        publicKey = (RSAPublicKey) keyPair.getPublic();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();//Get the key instance
        algorithm = Algorithm.RSA256(publicKey, privateKey);
    }

    public String signToken() throws Exception {
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("test", "aaaa")
                .sign(algorithm);
        return token;
    }

    @Test
    public void signAndVerifyToken() throws Exception {
        JWTVerifier verifier = JWT.require(algorithm)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(signToken());
        System.out.println(jwt.getPayload());
        System.out.println(jwt.getHeader());
    }

    @Test
    public void rsaSignAndVerifyToken() throws Exception {
        String token;
        {
            // 私钥签名
            Algorithm algorithm = Algorithm.RSA256(null, privateKey);

            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("test", "aaaa")
                    .sign(algorithm);
        }

        System.out.println(token);
        {
            // 公钥验签
            Algorithm algorithm = Algorithm.RSA256(publicKey, null);

            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt.getPayload());
            System.out.println(jwt.getHeader());
        }
    }
}
