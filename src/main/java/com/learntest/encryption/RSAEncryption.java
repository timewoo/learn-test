package com.learntest.encryption;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

/**
 * @author yanglin
 * @date 2022/3/29 15:39
 */
public class RSAEncryption {


    public static void main(String[] args) throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException {
//        String message = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond()+" GET /dsns/AC000W027097195.json";
        String message = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond()+" ed61bba015d6449aaf9b7a73853019f2 73d461bf ledevb-test-gw";
        Security.addProvider(new BouncyCastleProvider());
        String rsaPublicKeyHeader = "-----BEGIN RSA PUBLIC KEY-----\n";
        String rsaPublicKeyFooter = "\n-----END RSA PUBLIC KEY-----";
        String rsaPublicKeyString =
                "MIIBCgKCAQEA6OoR3wlR6bXKFC55VzLrNjYIGxJ9g/vviVX10EADVfVjam34UJjwC8fbkHiu1ndv\n" +
                        "Bz8PsSqEH7nzs0gJ77FKdHVqwJlzxud8VPibDWNGZHu8A5uFb8A6iVr2foFXnAk2RU+Y0GB5GzYV\n" +
                        "Adpq9uNnkRQ/nJyxTohASm+qRR2WjkilxU6qEnfRFNpIKZUpfvnS7EwBHTKuCi2RGbFDos2TaO+P\n" +
                        "gDcRSuHpcLg4fnKE4y0wvzd9PjDmh6tw8lc6WuOYYDleJdXDq4ITjcRMWBg9A9B0S0380V7Fal7r\n" +
                        "USwBt1dmX5MALWsGH/rvzthHRSXeWizxuQsGjjIfVryU9KNd8wIDAQAB";
//        String message = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond()+" GET /dsns/AC000W027139906.json";
//        String rsaPublicKeyString = "MIIBCgKCAQEAywC2gSsjLw0kEpKUyDZHeLSHP9Sm7q0K7SRxdKVndCdgMWR2wAyFI1YWHbva4epe4Uf3s/ZAe5LlpyoYdWrf1Wr59/UI9YT2j9Zm2hqBKRr8rAYNxYiRecOPgndloi4Tj/4o/z4bLI2UZm5mWaVUjtNvZZagfpTHWd1zAMqVE7pY1pwZJDfAKX7LVmqN4i0OAWo57/EkrYPz0o8k3B2nbk1VIx6h0kJgZLV6MHY4nVvMeoe7SGeZq/m10BSNX0ZImbKqR6WBM7jXNwd/34Qc6ZY1+t5zxWb8Ga+L8dUkIDv9wOjlP0pjlxKasSNfv/0mP+lLg7RkTIDg5C3upog83wIDAQAB";
        PEMParser pemParser = new PEMParser(new StringReader(rsaPublicKeyHeader +
                rsaPublicKeyString + rsaPublicKeyFooter));
        SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo) pemParser.readObject();
        byte[] publicKey = subjectPublicKeyInfo.getEncoded();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKey);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        Cipher rsa = Cipher.getInstance("RSA");
        rsa.init(Cipher.ENCRYPT_MODE,rsaPublicKey);
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] bytes1 = rsa.doFinal(bytes);
        String encode = Base64.getEncoder().encodeToString(bytes1);
        System.out.println(encode);
    }

}
