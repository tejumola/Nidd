package pinnacle.org.nidd.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @class Utils
 *
 * @brief Helper methods shared by various Activities.
 */
public class Utils {
    /**
     * Debugging tag.
     */
    private static final String TAG =
        Utils.class.getCanonicalName();

    /**
     * Return an uppercase version of the input or null if user gave
     * no input.  If user gave no input and @a showToast is true a
     * toast is displayed to this effect.
     */
    public static String uppercaseInput(Context context, 
                                        String input,
                                        boolean showToast) {
        if (input.isEmpty()) {
            if (showToast)
                Utils.showToast(context,
                                "no input provided");
            return null;
        } else
            // Convert the input entered by the user so it's in
            // uppercase.
            return input.toUpperCase(Locale.ENGLISH);
    }

    /**
     * Show a toast message.
     */
    public static void showToast(Context context,
                                 String message) {
        Toast.makeText(context,
                       message,
                       Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is used to hide a keyboard after a user has
     * finished typing the url.
     */
    public static void hideKeyboard(Activity activity,
                                    IBinder windowToken) {
        InputMethodManager mgr =
            (InputMethodManager) activity.getSystemService
            (Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }
        
    /**
     * Set the result of the Activity to indicate whether the
     * operation on the content succeeded or not.
     * 
     * @param activity
     *          The Activity whose result is being set.
     * @param pathToContent
     *          The pathname to the content file.
     * @param failureReason
     *          String to add to add as an extra to the Intent passed
     *          back to the originating Activity if the @a
     *          pathToContent is null.
     */
    public static void setActivityResult(Activity activity,
                                         Uri pathToContent,
                                         String failureReason) {
        if (pathToContent == null)
            // Indicate why the operation on the content was
            // unsuccessful or was cancelled.
            activity.setResult
                (Activity.RESULT_CANCELED,
                 new Intent("").putExtra("reason",
                                         failureReason));
        else
            // Set the result of the Activity to designate the path to
            // the content file resulting from a successful operation.
            activity.setResult(Activity.RESULT_OK,
                               new Intent("",
                                          pathToContent));
    }

    /**
     * Set the result of the Activity to indicate whether the
     * operation on the content succeeded or not.
     * 
     * @param activity
     *          The Activity whose result is being set.
     * @param resultCode
     *          The result of the Activity, i.e., RESULT_CANCELED or
     *          RESULT_OK. 
     * @param failureReason
     *          String to add to add as an extra to the Intent passed
     *          back to the originating Activity if the result of the
     *          Activity is RESULT_CANCELED. 
     */
    public static void setActivityResult(Activity activity,
                                         int resultCode,
                                         String failureReason) {
        if (resultCode == Activity.RESULT_CANCELED)
            // Indicate why the operation on the content was
            // unsuccessful or was cancelled.
            activity.setResult(Activity.RESULT_CANCELED,
                 new Intent("").putExtra("reason",
                                         failureReason));
        else 
            // Everything is ok.
            activity.setResult(Activity.RESULT_OK);
    }


    /**
     * generic method for converting an array to a modifiable list
     * @param args
     * @param <T>
     * @return
     */
    public static <T> List<T> asList(T... args){
        return new ArrayList<T>(Arrays.asList(args)) ;
    }




    public static  String shuffle(String input){
        List<Character> characters = new ArrayList< >();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }




    /**
     * close any closable stream quitely
     * @param closeable
     */
    public static void closeStream(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * @throws AssertionError when trying to create an instance of this class
     */
    private Utils() {
        throw new AssertionError("cannot instantiate this class");
    }

}
