import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by Andrei Andrade on 22/11/2016.
 */
public class CipherAES {
    private static final String ALGORITHM = "AES";

//    public String encrypt(String value, String keyValue) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
//        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyValue.getBytes("UTF-8"), ALGORITHM));
//        byte[] encryptedValue = cipher.doFinal(value.getBytes());
//
//        return Base64.getEncoder().encodeToString(encryptedValue);
//    }
//
//    public String decrypt(String value, String keyValue) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
//        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyValue.getBytes("UTF-8"), ALGORITHM));
////        byte[] base64AsByte = Base64.getDecoder().decode(value);
//        String string = new String(value.getBytes());
//        byte[] decryptedValue = cipher.doFinal(value.getBytes());
//
//        return new String(decryptedValue);
//    }

    public static String encrypt(String texto, String chave) throws Exception {
        Key key = new SecretKeySpec(chave.getBytes("UTF-8"), ALGORITHM); //gera a "key" a partir da chave passada pelo usuário
        Cipher c = Cipher.getInstance(ALGORITHM); //cria o algortimo de criptografia no modo escolhido, ex:AES
        c.init(Cipher.ENCRYPT_MODE, key);//inicia o algoritmo no modo encriptação com a chave criada
        //System.out.println(c.getAlgorithm());
        byte[] resultadoEncriptado = c.doFinal(texto.getBytes()); //encripta o texto passado
        String teste = new String(resultadoEncriptado);
        System.out.println("isso em binario " + teste);
        String encriptadoEmBase64 = new BASE64Encoder().encode(resultadoEncriptado); //transforma para Base 64 para poder exibir na tela

        return encriptadoEmBase64;
    }

    // metodo para decriptar  utilizando texto encriptado (em base 64) e chave passada
    public static String decrypt(String textoEncriptado, String chave) throws Exception {
        Key key = new SecretKeySpec(chave.getBytes("UTF-8"), ALGORITHM); //gera a "key" a partir da chave passada pelo usuário
        Cipher c = Cipher.getInstance(ALGORITHM); //cria o algortimo de criptografia no modo escolhido, ex:AES
        c.init(Cipher.DECRYPT_MODE, key); //inicia o algoritmo no modo decriptação com a chave criada
        byte[] decodificadoBase64paraByte = new BASE64Decoder().decodeBuffer(textoEncriptado); //transforma de Base 64 para bytes

        byte[] resultadoDecriptado = c.doFinal(decodificadoBase64paraByte); //decripta valor em byte
        String decriptadoEmString = new String(resultadoDecriptado); //passa vetor de bytes decriptados para string
        return decriptadoEmString;
    }
}
