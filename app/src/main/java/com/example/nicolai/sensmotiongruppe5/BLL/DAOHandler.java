package com.example.nicolai.sensmotiongruppe5.BLL;

import com.example.nicolai.sensmotiongruppe5.Interface.IData;
import java.util.List;

public class DAOHandler implements IData {

    DAO userDAO = DAO.getInstance();

    private String project_key;
    private String patient_key;

    public DAOHandler(String project_key, String patient_key){

        this.project_key = project_key;
        this.patient_key = patient_key;

    }

    /**
     *  @param startDate
     * @param endDate
     */
    @Override
    public String[] setCurretData(String startDate, String endDate) {

        return new String[0];

    }

    /**
     * Converts a list of lists to string array and "formats" the date
     * @return all available dates from json
     */
    @Override
    public String[] getAllDates() {

        List<List<String>> valuesArray;
        valuesArray = userDAO.getData(project_key, patient_key, 7);

        // Create string array
        String[] datesArray = new String[valuesArray.size()];

        // Iterate over string array
        for (int i = 0; i < valuesArray.size(); i++) {
            // Only get the first 10 characters (for a more readable format)
            datesArray[i] = valuesArray.get(i).get(1).substring(0, 10);
        }

        return datesArray;
    }

    /**
     *
     *
     * @return
     */
    @Override
    public List<List<String>> getAllInfo(int dayCount) {

        List<List<String>> valuesArray;
        valuesArray = userDAO.getData(project_key, patient_key, dayCount);

        return valuesArray;
    }

}
