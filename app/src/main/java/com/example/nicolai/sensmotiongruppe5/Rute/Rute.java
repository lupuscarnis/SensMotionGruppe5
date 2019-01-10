package com.example.nicolai.sensmotiongruppe5.Rute;

import android.view.View;

import java.util.ArrayList;

public class Rute {
    private float distance;
    private float walked = 0;
    private ArrayList highLights;
    private Rute_Canvas canvas;
    private ArrayList<Float> roadDistances;
    private ArrayList<ArrayList<Float>> cords;

    public Rute(View ctx, ArrayList<ArrayList<Float>> matrix) {
        canvas = (Rute_Canvas) ctx;
        cords = matrix;
        roadDistances = new ArrayList<>();
        calculateDistance(matrix);


    }


    public void drawRute(int[] movemnt) {
        int i = roadDistances.size() - 1;
        walked = calculateMovement(movemnt) + walked;
        float remainder = walked;

        while (i >= 0 && roadDistances.get(i) <= remainder) {


            remainder = remainder - roadDistances.get(i);

            i--;
        }
        for (int d = 0; d < roadDistances.size(); d++) {

            canvas.Draw(cords.get(d).get(0), cords.get(d).get(1), cords.get(d).get(2), cords.get(d).get(3));


        }


    }

    private void calculateDistance(ArrayList<ArrayList<Float>> matrix) {
        float resault = 0;
        for (ArrayList a : matrix) {

            float xstart, ystart, xend, yend;
            //X Start
            xstart = (float) a.get(0);
            //Y Start
            ystart = (float) a.get(1);
            //X End
            xend = (float) a.get(2);
            //Y End
            yend = (float) a.get(3);
            resault += Math.sqrt(Math.pow((xend - xstart), 2) + Math.pow((yend - ystart), 2));
            roadDistances.add(resault);

        }
        distance = resault;


    }

    private float calculateMovement(int values[]) {
        float i = 0;

/*
Calculateing the the ammount of meters traversed since we last checked

 */
        //walking
        i += (values[0] * 83);
        //running
        i += (values[1] * 183);
        //bikeing
        i += (values[2] * 416);
        // sitting beinga active
        i += +(values[3] * 30);


        return i;
    }

    private float movemntToPixels(float movement) {

        float pixels = movement / 200;


        return pixels;
    }


}
