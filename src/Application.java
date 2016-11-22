import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

/**
 * Created by Andrei Andrade on 22/11/2016.
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        Decryptor billGates = new Decryptor();
        Decryptor steveJobs = new Decryptor();
        Decryptor linusTorvald = new Decryptor();

        long initTime = System.currentTimeMillis();
        System.out.println("Que os jogos comecem: " + new SimpleDateFormat("hh:mm:ss").format(initTime));
        System.out.println("Tio Bill, Steve e Linus entraram no jogo.");

        Thread tioBill = new Thread(billGates, "Tio Bill");
        Thread tioSteve = new Thread(steveJobs, "Tio Steve");
        Thread tioLinus = new Thread(linusTorvald, "Tio Linus");

        tioBill.start();
//        tioSteve.start();
//        tioLinus.start();
        tioBill.join();
//        tioSteve.join();
//        tioLinus.join();

        System.out.println("Game Over: " + new SimpleDateFormat("hh:mm:ss").format(System.currentTimeMillis() - initTime));
        System.out.println("Tentativas: " + KeyGeneratorUtil.instance().getAttempts());
    }

    public static void testEncryptDecrypt() {
        try {
            CipherAES cipherAES = new CipherAES();
            String texto = "Dois macacos pregos";
            System.out.println(texto);
            String criptado = cipherAES.encrypt(texto, "chaveMegaSecreta");
            System.out.println(criptado);
            String decriptado = cipherAES.decrypt(criptado, "chaveMegaSecreta");
            System.out.println(decriptado);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
