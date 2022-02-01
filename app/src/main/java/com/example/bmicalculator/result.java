package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class result extends AppCompatActivity {

    android.widget.Button calcuagain;
    TextView bmino,bmicatagory,range,risk;
    Intent intent;
    ImageView thumb;
    String cbmi;
    float intbmi;
    String height;
    String weight;
    float intheight,intweight;
    RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        intent=getIntent();
        bmino=findViewById(R.id.bmi);
        bmicatagory=findViewById(R.id.catagory);
        background=findViewById(R.id.resultlayout);
        thumb=findViewById(R.id.imageview);
        range=findViewById(R.id.answerbmirange);
        risk=findViewById(R.id.answerhealthrisk);

        height=intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");
        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);

        if(intheight> 5) {
            intbmi = intweight / ((intheight / 100) * (intheight  / 100));
        }

        else
        {  intbmi = intweight/ ((intheight) * (intheight));
        }

        cbmi=Float.toString(intbmi);


        if (intbmi<=18.4)
        {
            bmicatagory.setText("Underweight");
            background.setBackgroundColor(Color.RED);
            thumb.setImageResource(R.drawable.thumbdown);
            range.setText("18.4 and Below");
            risk.setText("Malnutrition Risk");
        }
        else if (intbmi>=18.4 && intbmi<=24.9)
        {
            bmicatagory.setText("Normal Weight");
            //background.setBackgroundColor(Color.GREEN);
            thumb.setImageResource(R.drawable.thumbup);
            range.setText("18.5 - 24.9");
            risk.setText("Low Risk");
        }
        else if (intbmi>=25 && intbmi<=29.9)
        {
            bmicatagory.setText("Overweight");
            background.setBackgroundColor(Color.RED);
            thumb.setImageResource(R.drawable.thumbdown);
            range.setText("25 - 29.9");
            risk.setText("Enhanced Risk");
        }
        else if (intbmi>=30 && intbmi<=34.9)
        {
            bmicatagory.setText("Moderately Obese");
            background.setBackgroundColor(Color.RED);
            thumb.setImageResource(R.drawable.warning);
            range.setText("30 - 34.9");
            risk.setText("Medium Risk");
        }
        else if (intbmi>=35 && intbmi<=39.9)
        {
            bmicatagory.setText("Severely Obese");
            background.setBackgroundColor(Color.RED);
            thumb.setImageResource(R.drawable.warning);
            range.setText("35 - 39.9");
            risk.setText("Hight Risk");
        }
        else if (intbmi>=40)
        {
            bmicatagory.setText("Very Severely Obese");
            background.setBackgroundColor(Color.RED);
            thumb.setImageResource(R.drawable.warning);
            range.setText("40 and Above");
            risk.setText("Very Hight Risk");
        }

        bmino.setText(cbmi);





        calcuagain=findViewById(R.id.again);
        calcuagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(result.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}