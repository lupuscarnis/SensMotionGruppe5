package com.example.nicolai.sensmotiongruppe5.BLL;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DAO {

    // Start and end dates
    private String currentStartDate;
    private String currentEndDate;

    private static final String TAG = DAO.class.getSimpleName();

    // Singleton
    private static DAO instance;

    private DAO() {

        setDefaults();
    }

    public static DAO getInstance() {

        if (instance == null) {
            instance = new DAO();
        }

        return instance;

    }

    public void setDefaults() {

        String date = String.valueOf(android.text.format.DateFormat.format("dd-MM-yyyy", new java.util.Date(new java.util.Date().getTime() - (24 * 60 * 60 * 1000))));
        currentStartDate = date;
        currentEndDate = "01-01-2019";

    }

    public void setCurrentStartDate(String date) {
        currentStartDate = date;
    }

    public void setCurrentEndDate(String date) {
        currentEndDate = date;
    }

    public String getCurrentStartDate() {
        return currentStartDate;
    }

    public String getCurrentEndDate() {
        return currentEndDate;
    }

    public static ArrayList<JSONData> getData(String project_key, String patient_key, int dayCount) {

        Log.d("Hello from getData", "getData was called");

        ArrayList<JSONData> jsonData = new ArrayList<JSONData>();

        JSONConnection jsonConnection = new JSONConnection();
        // Making a request to url and getting response
        String url = "https://beta.sens.dk/exapi/1.0/patients/data/external/overview?project_key=" + project_key + "&patient_key=" + patient_key + "&day_count=" + dayCount + "";

        // Try to process JSON data (String)
        try {

            //Check for status code here. 0 = "OK", 13 = "Analysis in progress"
            String jsonStr = jsonConnection.getJSON(url);
            JSONObject jsonObject = new JSONObject(jsonStr);
            String jsonStatusCode = jsonObject.getString("status_code");

            Log.d("status_code was: ", jsonStatusCode + "");

            JSONArray jsonArray = jsonObject.getJSONObject("value").getJSONArray("data");

            try {

                // Loop to get all json objects from data json array
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject e = jsonArray.getJSONObject(i);

                    jsonData.add(new JSONData());

                    // start_time split into yyyy mm dd
                    String[] datesArr = e.getString("start_time").substring(0, 10).split("-");

                    // set start_time and reverse order
                    //jsonData.get(i).setStartDate(e.getString("start_time").substring(0, 10));
                    jsonData.get(i).setStartDate(datesArr[2] + "-" + datesArr[1] + "-" + datesArr[0]);

                    Log.d("JSON data updated!", "");

                    jsonData.get(i).setYear(Integer.parseInt(datesArr[0]));
                    jsonData.get(i).setMonth(Integer.parseInt(datesArr[1]));
                    jsonData.get(i).setDay(Integer.parseInt(datesArr[2]));

                    // values
                    String values = e.getString("values");

                    //Split values from string by comma
                    String[] valuesArray = values.split(",");

                    jsonData.get(i).setResting(Double.parseDouble(valuesArray[0].replaceAll("[^0-9.]", "")));
                    jsonData.get(i).setStanding(Double.parseDouble(valuesArray[1].replaceAll("[^0-9.]", "")));
                    jsonData.get(i).setWalking(Double.parseDouble(valuesArray[2].replaceAll("[^0-9.]", "")));
                    jsonData.get(i).setCycling(Double.parseDouble(valuesArray[3].replaceAll("[^0-9.]", "")));
                    jsonData.get(i).setExercise(Double.parseDouble(valuesArray[4].replaceAll("[^0-9.]", "")));
                    jsonData.get(i).setOtherd(Double.parseDouble(valuesArray[5].replaceAll("[^0-9.]", "")));
                    jsonData.get(i).setNodata(Double.parseDouble(valuesArray[6].replaceAll("[^0-9.]", "")));
                    jsonData.get(i).setSteps(Double.parseDouble(valuesArray[7].replaceAll("[^0-9.]", "")));

                    if (jsonData.isEmpty()) {

                        jsonData.add(new JSONData());
                    }

                }

            } catch (Exception e) {

                Log.e(TAG, "Json parsing error 1: " + e.getMessage());
            }

        } catch (Exception e) {

            Log.e(TAG, "Json parsing error 2: " + e.getMessage());
        }

        return jsonData;
    }

}