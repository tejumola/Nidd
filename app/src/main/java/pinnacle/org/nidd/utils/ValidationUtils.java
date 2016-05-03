package pinnacle.org.nidd.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 4/15/16.
 */
public enum ValidationUtils {
    INSTANCE;
    private static final String USERNAME_PATTERN = "^[A-Za-z_0-9]{3,15}$";

    // validating email id
    public boolean isValidEmail(String email) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }
        return true;
    }

    // validating password with retype password
    public boolean isValidPassword(String pass) {
         if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }

    /**
     * Validate username with regular expression
     * @param userName username for validation
     * @return true valid username, false invalid username
     */
    public boolean isValidName(String userName) {
        if(userName==null)
            return false;

        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(userName);

         if (matcher.matches () && userName.length() > 6) {
            return true;
        }
        return false;
    }

    public boolean containsWhiteSpace(String string){
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
