package com.example.nicolai.sensmotiongruppe5.BLL;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;


public class SharedPrefs {

    private static SharedPrefs   sharedPreference;
    public static final String PREFS_NAME = "SENS_PREFS";
    public static final String PREFS_KEY = "SENS";

    public static SharedPrefs getInstance()
    {
        if (sharedPreference == null)
        {
            sharedPreference = new SharedPrefs();
        }
        return sharedPreference;
    }

    public SharedPrefs() {
        super();
    }

    /**
     * Stores a string in shared prefs. Optional base64 encode string, true|false boolean
     * @param context
     * @param text
     * @param Key
     * @param encode
     */
    public void saveString(Context context, String text , String Key, boolean encode) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        if (encode) {

            editor.putString(Key, encode(text));

        } else {

            editor.putString(Key, text);
        }

        editor.apply();
    }

    /**
     * Retrieves a string from shared prefs. Optional decode a base64, true|false boolean
     * @param context
     * @param Key
     * @param decode
     * @return
     */
    public String getString(Context context , String Key, boolean decode) {
        SharedPreferences settings;
        String text = "";
        //  settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (decode) {

            text = decode(settings.getString(Key, ""));

        } else {

            text = settings.getString(Key, "");

        }
        return text;
    }

    /**
     * Clears all shared prefs.
     * @param context
     */
    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.apply();
    }

    /**
     * Removes a value from shared prefs.
     * @param context
     * @param value
     */
    public void removeValue(Context context , String value) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(value);
        editor.apply();
    }

    /**
     * Encodes base64
     * @param input
     * @return
     */
    public static String encode(String input) {
        // base64 encoding
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    /**
     * Decodes base64
     * @param input
     * @return
     */
    public static String decode(String input) {
        // base64 decoding
        return new String(Base64.decode(input, Base64.DEFAULT));
    }

}