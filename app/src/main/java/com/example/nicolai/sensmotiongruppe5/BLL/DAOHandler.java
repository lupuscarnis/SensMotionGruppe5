package com.example.nicolai.sensmotiongruppe5.BLL;


import com.example.nicolai.sensmotiongruppe5.Interface.IData;

import java.util.ArrayList;

public class DAOHandler implements IData {

    DAO userDAO = DAO.getInstance();

    private String project_key;
    private String patient_key;

    //private ArrayList<DAO.jsonArray> valuesArray = userDAO.getData(project_key, patient_key, 7);

    public DAOHandler(String project_key, String patient_key){

        this.project_key = project_key;
        this.patient_key = patient_key;

    }

    /**
     *  @param startDate in format [YYYY-MM-DD]
     * @param endDate in format [YYYY-MM-DD]
     */
    @Override
    public ArrayList<String> setCurrentData(String startDate, String endDate) {

        ArrayList<JSONData> valuesArray;
        valuesArray = userDAO.getData(project_key, patient_key, 7);

        ArrayList<String> dataArray = new ArrayList<String>();

        int dateSumStart = Integer.parseInt(startDate.replaceAll("-",""));
        int dateSumEnd = Integer.parseInt(endDate.replaceAll("-",""));

        // Loop to get all json objects from data json array
        // Iterate over valuesArray
        for (int i = 0; i < valuesArray.size(); i++) {

            int dateSumV = Integer.parseInt(valuesArray.get(i).getStartDate().replaceAll("-",""));

                if (dateSumV >= dateSumStart && dateSumV <= dateSumEnd) {

                    dataArray.add(Double.toString(valuesArray.get(i).getResting()));

                }

        }

        return dataArray;

    }

    /**
     * Gets all dates. Converts DAO.jsonArray to string array.
     * @return all available start_dates from json
     */
    @Override
    public String[] getAllDates() {

        ArrayList<JSONData> valuesArray;
        valuesArray = userDAO.getData(project_key, patient_key, 7);

        String[] dataArray = new String[valuesArray.size()];

        //Log.d("number of entries", valuesArray.size()+"");

        // Iterate over valuesArray
        for (int i = 0; i < valuesArray.size(); i++) {
            dataArray[i] = valuesArray.get(i).getStartDate();
        }

        return dataArray;
    }

    @Override
    public ArrayList<JSONData> getAllInfoAsObjects() {
        return null;
    }

    /**
     *
     *
     * @return
     *//*
    @Override
    public ArrayList<JSONData> getAllInfoAsObjects() {

        ArrayList<JSONData> valuesArray;
        valuesArray = userDAO.getData(project_key, patient_key, 7);

        return valuesArray;
    }*/

    /**
     *
     * @param date (format: YYYY-MM-DD)
     * @return
     *//*
    public ArrayList<Double> getActivitiesByDate(String date) {

        ArrayList<DAO.jsonArray> valuesArray = userDAO.getData(project_key, patient_key, 7);

        // Create string array
        ArrayList<Double> dataArray = new ArrayList<>();

        // Iterate over valuesArray
        for (int i = 0; i < valuesArray.size(); i++) {

            if (valuesArray.get(i).getStartDate() == date) {

                dataArray.add(valuesArray.get(i).getResting());
                dataArray.add(valuesArray.get(i).getStanding());
                dataArray.add(valuesArray.get(i).getWalking());
                dataArray.add(valuesArray.get(i).getCycling());
                dataArray.add(valuesArray.get(i).getExercise());
                dataArray.add(valuesArray.get(i).getOtherd());
                dataArray.add(valuesArray.get(i).getSteps());

            }
        }

        return dataArray;

    }*/

}
