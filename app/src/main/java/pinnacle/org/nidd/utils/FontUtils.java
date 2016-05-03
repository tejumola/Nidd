package pinnacle.org.nidd.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by DOTECH on 10/03/2016.
 */
public class FontUtils {

    public static Typeface getFont(Context context, String font){
        Typeface roboto=Typeface.createFromAsset(context.getAssets(),font);
        return roboto;
    }
}
