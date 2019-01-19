package com.example.nicolai.sensmotiongruppe5.Rute;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.R;

import java.util.ArrayList;


public class Rute {

    private float distance;
    private Bitmap bitmap;
    private float walked = 0;
    private ArrayList<Text_Highlight> highLights;
    private Rute_Canvas canvas;
    private ArrayList<Float> roadDistances;
    private ArrayList<Rutevector> cords;
    private Context context;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    public Rute(View ctx, ArrayList<Rutevector> matrix, ArrayList highLights, FragmentManager fragmentManager, Fragment fragment) {

        context = ctx.getRootView().getContext();
        canvas = (Rute_Canvas) ctx;
        cords = matrix;
        roadDistances = new ArrayList<>();
        this.highLights = highLights;
        this.fragmentManager = fragmentManager;
        this.fragment = fragment;
        calculateDistance(matrix);


    }



    public void draw(int[] movemnt) {
        for(Rutevector s: cords)
        {
            canvas.drawRute(s.getStartX(),s.getStartY(),s.getEndX(),s.getEndY());
        }

        for (Text_Highlight b : highLights) {

            canvas.drawHighLight(b.getX(), b.getY(), b.getRadius());

        }
        int i = 0;
        walked = calculateMovement(movemnt) + walked;
        float remainder = walked;

        while (i < roadDistances.size()) {


            if (remainder < roadDistances.get(i) && remainder > 0) {

                float ratio = remainder / roadDistances.get(i);
                float startX = cords.get(i).getStartX();
                float startY = cords.get(i).getStartY();
                float endX = cords.get(i).getEndX();
                float endY = cords.get(i).getEndY();
                float xvector = endX - startX;
                float yvector = endY - startY;
                xvector = ratio * xvector;
                yvector = ratio * yvector;
                xvector = xvector + startX;
                yvector = yvector + startY;
                for (Text_Highlight y : highLights) {
                    if (((xvector - 10) <= y.getX() && (xvector + 10) >= y.getX()) && ((yvector - 10) <= y.getY() && (yvector + 10) >= y.getY())) {

                        if (y.isRevealed() == false) {
                            y.setRevealed(true);
                            //TODO reveal the highlight........
                            Log.v("Ahahahahaha", "" + y.getName());


                            Fragment ft = fragmentManager.findFragmentById(R.id.text_fragment);
                            TextView st =  ft.getView().findViewById(R.id.textbox);
                            st.setText("");
                            st.setText("Penis " +y.getName());

                            FragmentTransaction sb = fragmentManager.beginTransaction();
                            sb.detach(ft);
                            sb.attach(ft);
                            sb.commit();

                        }
                    }
                }
                canvas.drawMan(xvector, yvector);


            }


            if (remainder > roadDistances.get(i)) {

            }
            remainder = remainder - roadDistances.get(i);
            i++;
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

            roadDistances.add(movemntToPixels(result));

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

        float pixels = movement * 40;


        return pixels;
    }



}
