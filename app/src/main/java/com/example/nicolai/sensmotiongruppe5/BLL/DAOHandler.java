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
     *
     * @param startData
     * @param endDate
     */
    @Override
    public void setCurretData(String startData, String endDate) {

    }

    /**
     *
     * @return
     */
    @Override
    public String[] getAllDates() {
        return new String[0];
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
