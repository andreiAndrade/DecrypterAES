/**
 * Created by Andrei Andrade on 22/11/2016.
 */
public class KeyGeneratorUtil {
    private final Character[] SYMBOLS_DICTIONARY = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
    private static KeyGeneratorUtil instance;

    private int countChar1 = 0;
    private int countChar2 = 0;
    private int countChar3 = 0;
    private int countChar4 = 0;
    private int countChar5 = 0;

    private int attempts = 0;

    public Boolean finded = false;

    public String ganhador;

    private Boolean dictionaryReaded = false;
   
    public static synchronized KeyGeneratorUtil instance() {
        if (instance == null) {
            instance = new KeyGeneratorUtil();
        }
        return instance;
    }

    /**
     * Generates a key from a key fragment. If all possibilities up to the size of 16bytes are returned, return null.
     * @param fragmentOfKey String
     * @return String
     */
    public String generateKey(String fragmentOfKey) {
        if (hasNext()) {
            return fragmentOfKey + SYMBOLS_DICTIONARY[countChar1] + SYMBOLS_DICTIONARY[countChar2] + SYMBOLS_DICTIONARY[countChar3] + SYMBOLS_DICTIONARY[countChar4] + SYMBOLS_DICTIONARY[countChar5++];
        }

        return null;
    }

    private Boolean hasNext() {
        attempts++;
        if (!dictionaryReaded) {
            if (countChar5 < SYMBOLS_DICTIONARY.length) {
                return true;
            }
            countChar5 = 0;
            countChar4++;
            if (countChar4 < SYMBOLS_DICTIONARY.length) {
                return true;
            }
            countChar4 = 0;
            countChar3++;
            if (countChar3 < SYMBOLS_DICTIONARY.length) {
                return true;
            }
            countChar3 = 0;
            countChar2++;
            if (countChar2 < SYMBOLS_DICTIONARY.length) {
                return true;
            }
            countChar2 = 0;
            countChar1++;
            if (countChar1 < SYMBOLS_DICTIONARY.length) {
                return true;
            }
            countChar1 = 0;
            dictionaryReaded = true;
        }
        return false;
    }

    public int getAttempts() {
        return attempts;
    }
}
