package com.leonteqsecurity.cysec.Security;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyGeneratorUtility {
    public static KeyPair generateRSAKey() {
        KeyPair keyPair;
        try {
            // Check if the files containing keys exist
            boolean keysExist = areKeysExist("../public_key.txt", "../private_key.txt");

            if (keysExist) {
                // Read keys from files
                keyPair = readKeysFromFile("../public_key.txt", "../private_key.txt");
            } else {
                // Generate new keys
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(2048);
                keyPair = keyPairGenerator.generateKeyPair();

                RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
                saveKeyToFile("../public_key.txt", publicKey.getEncoded());

                RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
                saveKeyToFile("../private_key.txt", privateKey.getEncoded());
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate or load RSA key pair", e);
        }
        return keyPair;
    }

    private static boolean areKeysExist(String publicKeyFile, String privateKeyFile) {
        return new File(publicKeyFile).exists() && new File(privateKeyFile).exists();
    }

    private static KeyPair readKeysFromFile(String publicKeyFile, String privateKeyFile) throws Exception {
        // Read public key
        byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyFile));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);

        // Read private key
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyFile));
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);
    }

    private static void saveKeyToFile(String fileName, byte[] keyBytes) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(keyBytes);
        fos.close();
    }
}
