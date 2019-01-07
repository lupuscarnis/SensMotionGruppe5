package com.example.nicolai.sensmotiongruppe5.BLL;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private static final String TAG = DAO.class.getSimpleName();

    // Defaults
    /*private String project_key = "k5W2uX";
    private String patient_key = "6rT39u";
    private int dayCount = 7;*/

    // Singleton
    private static DAO instance;

    private DAO() {}

    public static DAO getInstance() {

        if (instance == null) {
            instance = new DAO();
        }

        return instance;

    }

    public List<List<String>> getData(String project_key, String patient_key, int dayCount){

        List<List<String>> finalValuesArray = new ArrayList<List<String>>();

            // Process JSON data (String)
            try {

                JSONConnection jsonConnection = new JSONConnection();
                // Making a request to url and getting response
                String url = "https://beta.sens.dk/exapi/1.0/patients/data/external/overview?project_key="+project_key+"&patient_key="+patient_key+"&day_count="+dayCount+"";
                String jsonStr = jsonConnection.getJSON(url);

                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray jsonArray = jsonObject.getJSONObject("value").getJSONArray("data");

                // Loop to get all json objects from data json array
                for (int i = 0; i < jsonArray.length(); i++) {

                    // Adds new row to List
                    finalValuesArray.add(new ArrayList<String>());

                    JSONObject e = jsonArray.getJSONObject(i);

                    String start_time = e.getString("start_time");
                    String end_time = e.getString("end_time");

                    // Add start/end time to beginning of the new array
                    finalValuesArray.get(i).add(start_time);
                    finalValuesArray.get(i).add(end_time);

                    String values = e.getString("values");

                    //Split values from string by comma
                    String[] valuesArray = values.split(",");

                    for(int j=0;j<valuesArray.length;j++){

                        //remove all non numeric characters
                        String time = valuesArray[j].replaceAll("[^\\d.]", "");
                        /*

                        // Just an example of how to split the activities by type.

                        String activity;

                        switch (j) {

                            case 0: activity = "resting";
                                break;
                            case 1: activity = "standing";
                                break;
                            case 2: activity = "walking";
                                break;
                            case 3: activity = "cycling";
                                break;
                            case 4: activity = "exercise";
                                break;
                            case 5: activity = "other";
                                break;
                            case 6: activity = "nodata";
                                break;
                            case 7: activity = "steps";
                                break;

                            default: activity = "";
                                break;

                        }

                        Log.e(TAG,"Activity: "+activity+" Time: "+time);
                        */

                        finalValuesArray.get(i).add(time);
                    }
                }

            } catch (JSONException e) {

                Log.e(TAG, "Json parsing error: " + e.getMessage());

            }

            Log.e("finalValuesArray Output",""+finalValuesArray);

            // Just an example of accessing the values from the arrays
            Log.e("finalValuesArray Output","Get index 0, start time (0): "+finalValuesArray.get(0).get(0));


        return finalValuesArray;

    }


}