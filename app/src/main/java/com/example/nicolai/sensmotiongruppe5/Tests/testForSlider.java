package com.example.nicolai.sensmotiongruppe5.Tests;

import com.example.nicolai.sensmotiongruppe5.BLL.DAOHandler;
import com.example.nicolai.sensmotiongruppe5.Interface.IData;

import java.util.List;

public class testForSlider implements IData {

    // Sets the default keys for the logged in patient
    DAOHandler daoHandler = new DAOHandler("k5W2uX", "6rT39u");

    public testForSlider() {

    }

    public void getInstance() {

    }

    public void setCurretData(String startData, String endDate) {

    }

    public String[] getAllDates() {

        String[] haha = {"27/11 ", "28/11 ", "29/11 ", "30/11 ", "1/12 ", "2/12 ", "3/12" ,};

        return haha;
    }

    @Override
    public List<List<String>> getAllInfo(int dayCount) {
        return null;
    }


}
