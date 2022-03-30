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
        String message = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond()+" 56d429cb628df6c80a8e7f0e9a7487ab 3c75973b ZBGW2-01-2-001";
        Security.addProvider(new BouncyCastleProvider());
        String rsaPublicKeyHeader = "-----BEGIN RSA PUBLIC KEY-----\n";
        String rsaPublicKeyFooter = "\n-----END RSA PUBLIC KEY-----";
        String rsaPublicKeyString =
                "MIIBCgKCAQEAh55b7EADrOyDwfrp9FaEXgs++D16ESl0atAZhiv8ViOzZ4tqYHMX/NxTqWxYc2hS" +
                        "uku2lE4t7SZ2dFSGv8UdqlzyKF7H1P9bT0PCRQXDvwADjOedoAJ4zLsp9uJ16ZXLFesr5SB3Lmf3" +
                        "s77hW23B4PqVyIyeb9D02ug7FZ5rDST/Fqj4jlu9GABMlkRJEBlVk3nyqdjsG+Axjr+/FssxPeJ2" +
                        "e+j4cHFQQA4yE57JM9MePVvJA2q5oqdaaVuOephj7yVB26GmUkPJgwUlhqho2g0EFSNUqsJl5tSM" +
                        "+hSV8koFwTImAX48vMJ8r8heQGDj4oqg3gZVBOufWTPqEXkzfwIDAQAB";
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
