package com.example.nicolai.sensmotiongruppe5.BLL;


import android.util.Log;

import com.example.nicolai.sensmotiongruppe5.Interface.IData;

import java.util.ArrayList;
import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;

//import static com.facebook.FacebookSdk.getApplicationContext;

public class DAOHandler implements IData {

    static DAO userDAO = DAO.getInstance();

    private static String project_key;
    private static String patient_key;

    //private ArrayList<DAO.jsonArray> valuesArray = userDAO.getData(project_key, patient_key, 7);

    public DAOHandler() {

        patient_key = SharedPrefs.getInstance().getString(getApplicationContext(), "patientKey", true);
        project_key = SharedPrefs.getInstance().getString(getApplicationContext(), "projectKey", true);

    }

    /**
     * @param startDate in format [DD-MM-YYYY]
     * @param endDate   in format [DD-MM-YYYY]
     */
    @Override
    public ArrayList<String> setCurrentData(String startDate, String endDate) {

        ArrayList<JSONData> valuesArray;
        valuesArray = DAO.getData(project_key, patient_key, 7);

        ArrayList<String> dataArray = new ArrayList<String>();

        int dateSumStart = Integer.parseInt(startDate.replaceAll("-", ""));
        int dateSumEnd = Integer.parseInt(endDate.replaceAll("-", ""));

        // Loop to get all json objects from data json array
        // Iterate over valuesArray
        for (int i = 0; i < valuesArray.size(); i++) {

            int dateSumV = Integer.parseInt(valuesArray.get(i).getStartDate().replaceAll("-", ""));

            if (dateSumV >= dateSumStart && dateSumV <= dateSumEnd) {

                dataArray.add(Double.toString(valuesArray.get(i).getResting()));

            }

        }

        return dataArray;

    }

    /**
     * Gets all dates. Converts DAO.jsonArray to string array.
     *
     * @return all available start_dates from json in format (DD-MM-YYYY)
     */
    @Override
    public String[] getAllDates() {

        ArrayList<JSONData> valuesArray;
        valuesArray = DAO.getData(project_key, patient_key, 7);
        String[] dataArray = new String[valuesArray.size()];

        // Iterate over valuesArray
        for (int i = 0; i < valuesArray.size(); i++) {
            dataArray[i] = valuesArray.get(i).getStartDate();
        }

        return dataArray;
    }


    /**
     * @return arraylist of all objects from json
     */
    @Override
    public ArrayList<JSONData> getAllInfoAsObjects() {

        ArrayList<JSONData> valuesArray;
        valuesArray = DAO.getData(project_key, patient_key, 7);

        return valuesArray;
    }

    /**
     * Note: Not the most elegant solution :-(
     *
     * @return All json data as object within a given date range
     */
    @Override
    public ArrayList<JSONData> getCurrentSelectDatesDataAsObject() {

        ArrayList<JSONData> valuesArray;
        valuesArray = DAO.getData(project_key, patient_key, 7);

        ArrayList<JSONData> dataArray = new ArrayList<JSONData>();

        String[] dates = getDAOCurrentDates();

        Log.d("getDAOCurrentDates", Arrays.toString(dates));

        // Split and reverse order of start date
        String[] startDate = dates[0].split("-");
        String sDate = startDate[2] + startDate[1] + startDate[0];
        //Converts date string to int ex. 2019-01-01 to 20190101
        int sYMD = Integer.parseInt(sDate);

        // Split and reverse order of end date
        String[] endDate = dates[1].split("-");
        String eDate = endDate[2] + endDate[1] + endDate[0];
        //Converts date string to int ex. 2019-01-01 to 20190101
        int eYMD = Integer.parseInt(eDate);

        Log.d("startDate ", sYMD + "");
        Log.d("endDate ", eYMD + "");

        for (int i = 0; i < valuesArray.size(); i++) {

            // Split and reverse order of date
            String[] jDateArr = valuesArray.get(i).getStartDate().split("-");
            String jDate = jDateArr[2] + jDateArr[1] + jDateArr[0];
            //Converts date string to int ex. 2019-01-01 to 20190101
            int jYMD = Integer.parseInt(jDate);

            if (jYMD <= sYMD) {

                if (eYMD <= jYMD) {

                    Log.d("Condition2 ", (eYMD + "<=" + jYMD + ""));
                    dataArray.add(valuesArray.get(i));
                }

            }
        }

        return dataArray;
    }

    /**
     * @param date (format: DD-MM-YYYY)
     * @return the values of a all activities by specified date
     */
    @Override
    public ArrayList<String> getAllActivitiesByDate(String date) {

        ArrayList<JSONData> valuesArray;
        valuesArray = DAO.getData(project_key, patient_key, 7);

        // Create string array
        ArrayList<String> dataArray = new ArrayList<>();

        // Iterate over valuesArray
        for (int i = 0; i < valuesArray.size(); i++) {

            if (valuesArray.get(i).getStartDate().equals(date)) {

                dataArray.add(Double.toString(valuesArray.get(i).getResting()));
                dataArray.add(Double.toString(valuesArray.get(i).getStanding()));
                dataArray.add(Double.toString(valuesArray.get(i).getWalking()));
                dataArray.add(Double.toString(valuesArray.get(i).getCycling()));
                dataArray.add(Double.toString(valuesArray.get(i).getExercise()));
                dataArray.add(Double.toString(valuesArray.get(i).getOtherd()));
                dataArray.add(Double.toString(valuesArray.get(i).getSteps()));

            }
        }

        return dataArray;

    }

    /**
     * @param date     (format: DD-MM-YYYY)
     * @param activity (resting|standing|walking|cycling|exercise|other|steps)
     * @return the value of a given activity by specified date
     */
    @Override
    public String getActivityByDate(String date, String activity) {

        ArrayList<JSONData> valuesArray;
        valuesArray = DAO.getData(project_key, patient_key, 7);

        String data = "";

        for (int i = 0; i < valuesArray.size(); i++) {

            if (valuesArray.get(i).getStartDate().equals(date)) {

                switch (activity) {
                    case "resting":
                        data = Double.toString(valuesArray.get(i).getResting());
                        break;
                    case "standing":
                        data = Double.toString(valuesArray.get(i).getStanding());
                        break;
                    case "walking":
                        data = Double.toString(valuesArray.get(i).getWalking());
                        break;
                    case "cycling":
                        data = Double.toString(valuesArray.get(i).getCycling());
                        break;
                    case "exercise":
                        data = Double.toString(valuesArray.get(i).getExercise());
                        break;
                    case "other":
                        data = Double.toString(valuesArray.get(i).getOtherd());
                        break;
                    case "steps":
                        data = Double.toString(valuesArray.get(i).getSteps());
                        break;
                    default:
                        data = "Invalid activity or date";
                }

            }
        }

        return data;

    }

    /**
     * Sets the "master" dates in DAO
     *
     * @param startDate
     * @param endDate
     */
    @Override
    public void setDAOCurrentDates(String startDate, String endDate) {

        userDAO.setCurrentStartDate(startDate);
        userDAO.setCurrentEndDate(endDate);
    }

    /**
     * Gets the "master" dates in DAO
     *
     * @return
     */
    @Override
    public String[] getDAOCurrentDates() {

        String[] dates = {userDAO.getCurrentStartDate(), userDAO.getCurrentEndDate()};
        return dates;
    }


    @Override
    public ArrayList<String> getIntervalDates() {
        ArrayList<JSONData> valuesArray;
        valuesArray = DAO.getData(project_key, patient_key, 7);
        ArrayList<String> dataArray = new ArrayList<>();


        boolean foundStart = false;

        // Iterate over valuesArray
        for (int i = 0; i < valuesArray.size(); i++) {
            if (valuesArray.get(i).getStartDate().equals(userDAO.getCurrentStartDate()) || foundStart) {
                foundStart = true;
                dataArray.add(valuesArray.get(i).getStartDate());

                if (valuesArray.get(i).getStartDate().equals(userDAO.getCurrentEndDate())) {
                    foundStart = false;
                }
            }

        }


        return dataArray;
    }

    /**
     * Returns a sum of a given activity
     *
     * @param activity
     * @return
     */
    @Override
    public Double getSumActivity(String activity) {

        ArrayList<JSONData> valuesArray;
        valuesArray = DAO.getData(project_key, patient_key, 7);

        double data = 0;

        for (int i = 0; i < valuesArray.size(); i++) {

            switch (activity) {
                case "resting":
                    data += valuesArray.get(i).getResting();
                    break;
                case "standing":
                    data += valuesArray.get(i).getStanding();
                    break;
                case "walking":
                    data += valuesArray.get(i).getWalking();
                    break;
                case "cycling":
                    data += valuesArray.get(i).getCycling();
                    break;
                case "exercise":
                    data += valuesArray.get(i).getExercise();
                    break;
                case "other":
                    data += valuesArray.get(i).getOtherd();
                    break;
                case "steps":
                    data += valuesArray.get(i).getSteps();
                    break;
                default:
                    data = 0;


            }
        }

        return data;

    }

}
