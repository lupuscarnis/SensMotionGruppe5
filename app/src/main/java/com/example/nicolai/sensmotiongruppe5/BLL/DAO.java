package com.example.nicolai.sensmotiongruppe5.BLL;

import android.util.Log;

public class DAO  {

    private static final String TAG = DAO.class.getSimpleName();

    // For future use
    public String patient_key;
    public String project_key;

    // Singleton
    private static DAO instance ;

    private DAO() {}

    public static DAO getInstance() {

        if (instance == null) {
            instance = new DAO();
        }

        return instance;

    }

    public void getData(){

        JSONConnection jsonConnection = new JSONConnection();
        // Making a request to url and getting response
        String url = "https://beta.sens.dk/exapi/1.0/patients/data/external/overview?project_key=k5W2uX&patient_key=6rT39u&day_count=7";
        String jsonStr = jsonConnection.getJSON(url);
        Log.e(TAG, "Response from url: " + jsonStr);

    }

}