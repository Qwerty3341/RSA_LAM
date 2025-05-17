package RSA;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class RSA {
    private static final int KEY_SIZE = 2048;
    private PrivateKey llavePrivada;
    private PublicKey llavePublica;

    public RSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(KEY_SIZE);
        KeyPair pair = keyGen.generateKeyPair();
        this.llavePrivada = pair.getPrivate();
        this.llavePublica = pair.getPublic();
    }

    public String encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, llavePublica);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, llavePrivada);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(decryptedBytes);
    }

    public static int getKeySize() {
        return KEY_SIZE;
    }

    public PrivateKey getLlavePrivada() {
        return llavePrivada;
    }

    public PublicKey getLlavePublica() {
        return llavePublica;
    }
    
}