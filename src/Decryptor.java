import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Andrei Andrade on 22/11/2016.
 */
public class Decryptor implements Runnable {

    private static final String FRAGMENT_OF_KEY = "12pingpongQ";
    private static final String ENCRYPTED_VALUE = "6j7W7b51dLus8COS0tVzf8t4Qf7Ch2sO5wBP7eeyEmkW+mIT4eEdk+CejhqT7/t/Dbo2lQZlias7AMqw0OD9NNkkaZSRd2qbrxjlzClLdVClBf877N90JutLjxw7d9q9+MYbRzYJGkCEvUWFHF7gvg==";
    private static final String FRAGMENT_OF_TEXT = "texto";

    @Override
    public void run() {

        CipherAES cipherAES = new CipherAES();
        String result = null;
        String key = null;

        do {
            try {
                key = getCompleteKey(FRAGMENT_OF_KEY);
                if (key == null) {
                    System.out.println("**Thread: " + Thread.currentThread().getName() + "**");
                    System.out.println("Nenhum resultado encontrado ao fim de todas possibilidades de chave na Thread: ");
                    Thread.currentThread().interrupt();
                    break;
                }

                result = cipherAES.decrypt(ENCRYPTED_VALUE, key);
                KeyGeneratorUtil.instance().finded = result.contains(FRAGMENT_OF_TEXT);

                if (result.contains(FRAGMENT_OF_TEXT)) {
                    KeyGeneratorUtil.instance().ganhador = "**Thread: " + Thread.currentThread().getName() + "**" + "\n"
                            + "O resultado encontrado para a chave: " + key + "... " + "foi: " + result;
                    KeyGeneratorUtil.instance().finded = true;
                } else {
                    System.out.println(Thread.currentThread().getName() + " bateu na trave com: " + key);
                }
            } catch (Exception e) {
            }
        } while (!KeyGeneratorUtil.instance().finded);

        Thread.currentThread().interrupt();
    }

    private synchronized String getCompleteKey(String fragmentOfKey) {
        return KeyGeneratorUtil.instance().generateKey(fragmentOfKey);
    }
}
