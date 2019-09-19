package np.com.tourguide.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtils {

    private static SharedPreferences preferences;

    private static void init(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences("PUBG", Context.MODE_PRIVATE);
        }
    }

    public static void setString(Context context, String key, String value) {
        init(context);
        preferences.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        init(context);
        return preferences.getString(key, "");
    }

    public static void setInt(Context context, String key, int value) {
        init(context);
        preferences.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key) {
        init(context);
        return preferences.getInt(key, 0);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        init(context);
        preferences.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key) {
        init(context);
        return preferences.getBoolean(key, false);
    }
}