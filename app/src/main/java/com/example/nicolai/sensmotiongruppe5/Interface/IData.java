package com.example.nicolai.sensmotiongruppe5.Interface;

import com.example.nicolai.sensmotiongruppe5.BLL.JSONData;

import java.util.ArrayList;

public interface IData {

    /**
     *
     * @param startDate
     * @param endDate
     * @return all available data in date range as a String array
     */
    ArrayList<String> setCurrentData(String startDate, String endDate);

    /**
     *
     * @return all available dates as a String array
     */
    String[] getAllDates();

    /**
     *
     * @return all information
     */
    ArrayList<JSONData> getAllInfoAsObjects();

}
