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

    /**
     * @param date (format: DD-MM-YYYY)
     * @return the values of a all activities by specified date
     */
    ArrayList<String> getAllActivitiesByDate(String date);

    /**
     * @param date     (format: DD-MM-YYYY)
     * @param activity (resting|standing|walking|cycling|exercise|other|steps)
     * @return the value of a given activity by specified date
     */
    String getActivityByDate(String date, String activity);

    /**
     * Called after setCurrentDate. Returns the data in interval set in DAO
     *
     * @return Returns a list of the Data between the 2 dates.
     */
    ArrayList<JSONData> getCurrentSelectDatesDataAsObject();

    /**
     * Sets A range from wich Data Can be pulled with getCurrentSelectDatesDataAsObject to get stuff inbewtween the dates
     *
     * @param startDate
     * @param endDate
     */
    void setCurrentDate(String startDate, String endDate);

    /**
     * Returns dates in interval set in DAO
     * @return
     */
    ArrayList<String> getIntervalDates();

}
