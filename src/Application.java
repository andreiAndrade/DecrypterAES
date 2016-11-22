import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by Andrei Andrade on 22/11/2016.
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
//        testEncryptDecrypt();
        executeApplication();
    }

    public static void executeApplication() throws InterruptedException {
        Decryptor billGates = new Decryptor();
        Decryptor steveJobs = new Decryptor();
        Decryptor linusTorvald = new Decryptor();

        LocalTime init = LocalTime.now();
        System.out.println("Que os jogos comecem: " + init);
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

        LocalTime finish = LocalTime.now();
        Duration duration = Duration.between(init, finish);

        System.out.println(KeyGeneratorUtil.instance().ganhador);
        System.out.println("Duração: " + duration);
        System.out.println("Tentativas: " + KeyGeneratorUtil.instance().getAttempts());
    }

    public static void testEncryptDecrypt() {
        try {
            CipherAES cipherAES = new CipherAES();
//            String texto = "Dentro do texto claro (decriptado) possui a palavra teste";
//            System.out.println(texto);
            String criptado = "6j7W7b51dLus8COS0tVzf8t4Qf7Ch2sO5wBP7eeyEmkW+mIT4eEdk+CejhqT7/t/Dbo2lQZlias7AMqw0OD9NNkkaZSRd2qbrxjlzClLdVClBf877N90JutLjxw7d9q9+MYbRzYJGkCEvUWFHF7gvg==";
            System.out.println(criptado);
            String decriptado = cipherAES.decrypt(criptado, "12pingpongQ1w2e3");
            System.out.println(decriptado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
