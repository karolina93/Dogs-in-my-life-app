package pl.dogsinmylife.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtils {
    private static final String KEY_FIRST_RUN = "KEY_FIRST_RUN";

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("Breed", Context.MODE_PRIVATE);
    }

    public static boolean isFirstRun(SharedPreferences prefs) {

        if (!prefs.getBoolean(KEY_FIRST_RUN, false)) {
            prefs.edit()
                    .putBoolean(KEY_FIRST_RUN, true)
                    .apply();
            return true;
        }
        return false;
    }

}
