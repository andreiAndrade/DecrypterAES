import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by Andrei Andrade on 22/11/2016.
 */
public class CipherAES {
    private static final String ALGORITHM = "AES";
    private Cipher cipher;

    CipherAES() throws NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance(ALGORITHM);
    }

    public String encrypt(String value, String keyValue) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyValue.getBytes(), ALGORITHM));
        byte[] encryptedValue = cipher.doFinal(value.getBytes());

        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public String decrypt(String value, String keyValue) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyValue.getBytes(), ALGORITHM));
        byte[] base64AsByte = Base64.getDecoder().decode(value);
        byte[] decryptedValue = cipher.doFinal(base64AsByte);

        return new String(decryptedValue);
    }
}
