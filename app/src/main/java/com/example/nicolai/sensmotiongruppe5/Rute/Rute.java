package com.example.nicolai.sensmotiongruppe5.Rute;

import android.view.View;

import java.util.ArrayList;

public class Rute {
    private float distance;
    private float walked = 0;
    private ArrayList highLights;
    private Rute_Canvas canvas;
    private ArrayList<Float> roadDistances;
    private ArrayList<Rutevector> cords;

    public Rute(View ctx, ArrayList<Rutevector> matrix) {
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
            if (remainder >= roadDistances.get(i)) {
                canvas.Draw(cords.get(i).getStartX(), cords.get(i).getStartY(), cords.get(i).getEndX(), cords.get(i).getEndY());
            }
            i--;
        }






    }

    private void calculateDistance(ArrayList<Rutevector> matrix) {
        float result = 0;
        for (Rutevector a : matrix) {

            float xstart, ystart, xend, yend;

            xstart = a.getStartX();

            ystart = a.getStartY();

            xend = a.getEndX();

            yend = a.getEndY();
            result += Math.sqrt(Math.pow((xend - xstart), 2) + Math.pow((yend - ystart), 2));
            roadDistances.add(result);

        }
        distance = result;


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

    public void drawHighLights() {

    }


}
