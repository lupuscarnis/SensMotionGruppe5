package com.example.nicolai.sensmotiongruppe5.BLL;


import com.example.nicolai.sensmotiongruppe5.Interface.IData;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class DAOHandler implements IData {

    DAO userDAO = DAO.getInstance();

    private String project_key;
    private String patient_key;

    //private ArrayList<DAO.jsonArray> valuesArray = userDAO.getData(project_key, patient_key, 7);

    public DAOHandler() {

        this.patient_key = SharedPrefs.getInstance().getString(getApplicationContext(), "patientKey", true);
        this.project_key = SharedPrefs.getInstance().getString(getApplicationContext(), "projectKey", true);
        /*this.project_key = project_key;
        this.patient_key = patient_key;*/

        setDAOCurrentDates("01-01-2019","02-01-2019");

    }

    /**
     * @param startDate in format [DD-MM-YYYY]
     * @param endDate   in format [DD-MM-YYYY]
     */
    @Override
    public ArrayList<String> setCurrentData(String startDate, String endDate) {

        ArrayList<JSONData> valuesArray;
        valuesArray = userDAO.getData(project_key, patient_key, 7);

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
        valuesArray = userDAO.getData(project_key, patient_key, 7);
        String[] dataArray = new String[valuesArray.size()];

        // Iterate over valuesArray
        for (int i = 0; i < valuesArray.size(); i++) {
            dataArray[i] = valuesArray.get(i).getStartDate();
        }

        return dataArray;
    }


    /**
     *
     * @return arraylist of all objects from json
     */
    @Override
    public ArrayList<JSONData> getAllInfoAsObjects() {

        ArrayList<JSONData> valuesArray;
        valuesArray = userDAO.getData(project_key, patient_key, 7);

        return valuesArray;
    }


    /**
     * @param date (format: DD-MM-YYYY)
     * @return the values of a all activities by specified date
     */
    @Override
    public ArrayList<String> getAllActivitiesByDate(String date) {

        ArrayList<JSONData> valuesArray;
        valuesArray = userDAO.getData(project_key, patient_key, 7);

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
        valuesArray = userDAO.getData(project_key, patient_key, 7);

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

    @Override
    public ArrayList<JSONData> getCurrentSelectDatesDataAsObject() {
        return null;
    }

    @Override
    public void setDAOCurrentDates(String startDate, String endDate) {

        userDAO.setCurrentStartDate(startDate);
        userDAO.setCurrentEndDate(endDate);
    }

    @Override
    public String[] getDAOCurrentDates() {

        String[] dates = {userDAO.getCurrentStartDate(),userDAO.setCurrentEndDate()};
        return dates;
    }


    @Override
    public ArrayList<String> getIntervalDates() {
        return null;
    }

}
