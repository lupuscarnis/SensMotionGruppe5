package com.example.nicolai.sensmotiongruppe5.Interface;

import java.util.List;

public interface IData {

    /**
     *
     * @param startData
     * @param endDate
     */
    void setCurretData(String startData, String endDate);

    /**
     *
     * @return
     */
    String[] getAllDates();

    /**
     *
     * @return
     */

    List<List<String>> getAllInfo(int dayCount);

}
