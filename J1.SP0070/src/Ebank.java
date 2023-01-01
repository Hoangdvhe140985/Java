
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hoang
 */
public class Ebank {

    private final String ACCOUNT_NUMBER_VALID = "^\\d{10}$";
    private final char[] chars = {'1', 'A', 'a', 'B', 'b', 'C',
        'c', '2', 'D', 'd', 'E', 'e', 'F', 'f', '3', 'G', 'g', 'H', 'h',
        'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', '4', 'M', 'm', 'N', 'n',
        'O', 'o', '5', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
        '6', '7', 'U', 'u', 'V', 'v', 'U', 'u', 'W', 'w', '8', 'X', 'x',
        'Y', 'y', 'Z', 'z', '9'};

    private ResourceBundle words;

    public void setLocale(Locale locate) {
        words = ResourceBundle.getBundle("Language/" + locate, locate);
    }

    public String getWord(String key) {
        String value = words.getString(key);
        return value;
    }

    //check account   
    public String checkInputAccount(String acc) {
        if (acc.matches(ACCOUNT_NUMBER_VALID)) {
            return "";
        }
        return getWord("errCheckInputAccount");
    }

    //check password
    public String checkInputPassword(String pass) {
        Pattern p2 = Pattern.compile("^[a-zA-Z0-9]{8,31}$");
        if (p2.matcher(pass).find()) {
            return "";
        }
        return getWord("errCheckLengthPassword");
    }

    //tạo random 1 capcha
    public String generateCaptcha() {
        final int LENGTH = 6;
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < LENGTH; i++) {
            index = (int) (Math.random() * (chars.length - 1));
            sb.append(chars[index]);
        }
        return sb.toString();
    }

    //
    public String checkInputCaptcha(String captchaGenerated, String captchaInput) {
        
        if (!captchaInput.trim().isEmpty() && captchaGenerated.contains(captchaInput)) {
            return "";
        }
        return getWord("errCaptchaIncorrect");
    }
}
