package com.example.nicolai.sensmotiongruppe5.Tests;

import com.example.nicolai.sensmotiongruppe5.BLL.DAOHandler;
import com.example.nicolai.sensmotiongruppe5.BLL.JSONData;
import com.example.nicolai.sensmotiongruppe5.Interface.IData;

import java.util.ArrayList;

public class testForSlider implements IData {

    // Sets the default keys for the logged in patient
    DAOHandler daoHandler = new DAOHandler();

    public testForSlider() {

    }

    public void getInstance() {

    }

    public ArrayList<String> setCurrentData(String startData, String endDate) {
        return null;
    }

    public String[] getAllDates() {

        String[] haha = {"27/11 ", "28/11 ", "29/11 ", "30/11 ", "1/12 ", "2/12 ", "3/12" ,};

        return haha;
    }

    @Override
    public ArrayList<JSONData> getAllInfoAsObjects() {
        return null;
    }

    @Override
    public ArrayList<String> getAllActivitiesByDate(String date) {
        return null;
    }

    @Override
    public String getActivityByDate(String date, String activity) {
        return null;
    }


}
