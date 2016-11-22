import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Andrei Andrade on 22/11/2016.
 */
public class Decryptor implements Runnable {

    private static final String FRAGMENT_OF_KEY = "12pingpongQ";
    private static final String ENCRYPTED_VALUE = "6j7W7b51dLus8COS0tVzf8t4Qf7Ch2sO5wBP7eeyEmkW+mIT4eEdk+CejhqT7/t/Dbo2lQZlias7AMqw0OD9NNkkaZSRd2qbrxjlzClLdVClBf877N90JutLjxw7d9q9+MYbRzYJGkCEvUWFHF7gvg==";
    private static final String FRAGMENT_OF_TEXT = "teste";

    @Override
    public void run() {
        try {
            CipherAES cipherAES = new CipherAES();
            Boolean findedResult = false;
            String result = null;
            String key = null;
            do {
                key = getCompleteKey(FRAGMENT_OF_KEY);
                if (key == null) {
                    System.out.println("**Thread: " + Thread.currentThread().getName() + "**");
                    System.out.println("Nenhum resultado encontrado ao fim de todas possibilidades de chave na Thread: ");
                    Thread.currentThread().interrupt();
                    break;
                }

                result = cipherAES.decrypt(ENCRYPTED_VALUE, key);
                findedResult = result.contains(FRAGMENT_OF_TEXT);

                if (!findedResult) {
                    System.out.println(Thread.currentThread().getName() + " bateu na trave com: " + key);
                }
            } while (!findedResult);

            System.out.println("**Thread: " + Thread.currentThread().getName() + "**");
            System.out.println("O resultado encontrado para a chave: " + key + "... " + "foi: " + result);
            Thread.currentThread().interrupt();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private synchronized String getCompleteKey(String fragmentOfKey) {
        return KeyGeneratorUtil.instance().generateKey(fragmentOfKey);
    }
}
