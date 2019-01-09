package com.example.nicolai.sensmotiongruppe5.Interface;

import java.util.List;

public interface IData {

    /**
     *
     * @param startDate
     * @param endDate
     * @return all available data in date range as a String array
     */
    String[] setCurretData(String startDate, String endDate);

    /**
     *
     * @return all available dates as a String array
     */
    String[] getAllDates();

    /**
     * @param dayCount
     * @return all information
     */
    List<List<String>> getAllInfo(int dayCount);

}
