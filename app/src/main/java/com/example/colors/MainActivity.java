package com.example.colors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView lblTargetColor = null;

    private TextView lblProposedColor = null;

    private SeekBar sbrRed = null;

    private TextView lblRedValue = null;

    private SeekBar sbrGreen = null;

    private TextView lblGreenValue = null;

    private SeekBar sbrBlue = null;
    private TextView lblBlueValue = null;
    private Button btnNew = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}