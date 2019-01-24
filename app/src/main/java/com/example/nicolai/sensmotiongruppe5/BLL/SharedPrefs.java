package com.example.nicolai.sensmotiongruppe5.BLL;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.example.nicolai.sensmotiongruppe5.Achievements;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class SharedPrefs {

    private static SharedPrefs   sharedPreference;
    public static final String PREFS_NAME = "SENS_PREFS";
    public static final String PREFS_KEY = "SENS";

    private static SharedPreferences settings;
    private static SharedPreferences.Editor editor;

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

        String text;
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

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.apply();
    }

    public static int getInt(String key){
        int n;
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<Integer>() {}.getType();


        if (gson.fromJson(json, type) == null){
            n=0;
        }
   else {

       n = gson.fromJson(json, type);
        }
        return n;
    }

    public static void saveInt(int n, String key){
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(n);
        editor.putString(key, json);
        editor.apply();
    }















    /**
     * Removes a value from shared prefs.
     * @param context
     * @param value
     */
    public void removeValue(Context context, String value) {

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