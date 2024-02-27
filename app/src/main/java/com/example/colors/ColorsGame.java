package com.example.colors;

import android.graphics.Color;

import java.util.Random;

public class ColorsGame {
    private  int targetBackColor = 0;
    private  int targetTextColor = 0;
    private int proposedBackColor = 0;
    private int proposedTextColor = 0;

    private OnChangeTargetColorListener onChangeTargetColorListener;
    private OnChangeProposedColorListener onChangeProposedColorListener;



    public interface  OnChangeTargetColorListener{
        public abstract void onchange(int backColor, int textColor);
    }

    public interface  OnChangeProposedColorListener{
        public abstract void onchange(int backColor, int textColor);

    }

    public OnChangeTargetColorListener getOnChangeTargetColorListener(){
        return  onChangeTargetColorListener;
    }

    public void setOnChangeTargetColorListener(OnChangeTargetColorListener newDelegate){
        this.onChangeTargetColorListener = newDelegate;
    }

    public OnChangeProposedColorListener getOnChangeProposedColorListener(){
        return  onChangeProposedColorListener;
    }

    public void setOnChangeProposedColorListener(OnChangeProposedColorListener newDelegate){
        this.onChangeProposedColorListener = newDelegate;
    }

    //Return a random color
    public static int randomColor(){
        Random rand = new Random();

        int redVal = rand.nextInt(256);
        int greenVal = rand.nextInt(256);
        int blueVal = rand.nextInt(256);
        int color = Color.rgb(redVal,greenVal, blueVal);
        return  color;
    }

    public static int getSuggestedTextColor(int BackColor){

        int redVal = Color.red(BackColor);
        int greenVal = Color.green(BackColor);
        int blueVal = Color.blue(BackColor);
        int grayVal = (int)(redVal * 0.20 + greenVal * 0.75 + blueVal * 0.05);
        int textColor = Color.BLACK;

        if (255 - grayVal > grayVal){
            textColor = Color.WHITE;
        }
        return  textColor;
    }

    public int getProposedBackColor(){
        return  proposedBackColor;
    }

    public void setProposedBackColor(int newBackColor){
        proposedBackColor = newBackColor;
        proposedTextColor = getSuggestedTextColor(proposedBackColor);
        if (getOnChangeProposedColorListener() != null){
            getOnChangeProposedColorListener().onchange(proposedBackColor, proposedTextColor);
        }
    }

    public int getProposedTextColor(){
        return  proposedTextColor;
    }

    //Return the distance between two colors
    public static double distance( int color1, int color2){
        int redVal1 = Color.red(color1);
        int greenVal1 = Color.green(color1);
        int blueVal1 = Color.blue(color1);

        int redVal2 = Color.red(color2);
        int greenVal2 = Color.green(color2);
        int blueVal2 = Color.blue(color2);

        //partial results
        int resRedVal = (int) Math.pow((redVal1 -redVal2),2);
        int resGreenVal = (int) Math.pow((greenVal1 -greenVal2),2);
        int resBlueVal = (int) Math.pow((blueVal1 -blueVal2),2);

        //calculate the distance between two colors(points 30)
        double distance = Math.sqrt(resRedVal + resGreenVal + resBlueVal);

        return  distance;

    }

    public int getScore(){
        double distance = distance(targetBackColor, proposedBackColor);
        int score = (int) (100- Math.min(distance, 100));
        return score;
    }

    public int getTargetBackColor(){
        return  targetBackColor;
    }


    private void setTargetBackColor(int newBacolor){

        targetBackColor = newBacolor;
        targetTextColor = getSuggestedTextColor(targetBackColor);

        do{
            proposedBackColor = randomColor();

        }while(getScore() > 0);

        if(getOnChangeTargetColorListener() != null){
            getOnChangeTargetColorListener().onchange(targetBackColor, targetTextColor);
        }

        if(getOnChangeProposedColorListener() != null){
            getOnChangeProposedColorListener().onchange(proposedBackColor, proposedTextColor);
        }

    }


    public int getTargetTextColor(){
        return targetTextColor;
    }

    public int getProposedColor(){
        return  proposedBackColor;
    }

    public void restarGame(){
        setTargetBackColor(randomColor());
    }


    public ColorsGame(){
        restarGame();
    }
















}
