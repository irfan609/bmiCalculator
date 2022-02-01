package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    android.widget.Button calcu;

    EditText cheight, cweight;
    TextView cage;
    ImageView cplusage, cminusage, cmale, cfemale;

    int intweight = 55;
    int intage = 22;
    String typeuser = "0";
    String age2 = "20";
    String textweight, textheight;
    public static final String FILE_NAME1="bmifileweight.txt";
    public static final String FILE_NAME2="bmifileheight.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        calcu=findViewById(R.id.calculate);
        cage=findViewById(R.id.currentage);
        cweight=findViewById(R.id.currentweight);
        cheight=findViewById(R.id.currentheight);
        cplusage=findViewById(R.id.ageplus);
        cminusage=findViewById(R.id.ageminus);
        cmale=findViewById(R.id.malesimpson);
        cfemale=findViewById(R.id.femalesimpson);

        FileInputStream fis1 = null;

        try {
            fis1 = openFileInput(FILE_NAME1);
            InputStreamReader isr = new InputStreamReader(fis1);
            BufferedReader br = new BufferedReader(isr);
            String txt;

            while((txt = br.readLine()) != null){
                cweight.setText(txt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis1 != null){
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileInputStream fis2 = null;

        try {
            fis2 = openFileInput(FILE_NAME2);
            InputStreamReader isr = new InputStreamReader(fis2);
            BufferedReader br = new BufferedReader(isr);
            String txt;

            while((txt = br.readLine()) != null){
                cheight.setText(txt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis2 != null){
                try {
                    fis2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        cmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.select));
                cfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonselect));
                typeuser="Male";
            }
        });
        cfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.select));
                cmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonselect));
                typeuser="Female";
            }
        });


        cplusage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage=intage+1;
                age2=String.valueOf(intage);
                cage.setText(age2);
            }
        });

        cminusage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage=intage-1;
                age2=String.valueOf(intage);
                cage.setText(age2);
            }
        });



        calcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (typeuser.equals("0"))
                {
                    Toast.makeText(getApplicationContext(),"Please Select Your Gender",Toast.LENGTH_SHORT).show();
                }
                else if (intage==0 || intage<0)
                {
                    Toast.makeText(getApplicationContext(),"Age is Incorrect",Toast.LENGTH_SHORT).show();
                }
                else if (intweight==0 || intweight<0)
                {
                    Toast.makeText(getApplicationContext(),"Weight is Incorrect",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    textweight=cweight.getText().toString();
                    textheight=cheight.getText().toString();
                    Intent intent= new Intent(MainActivity.this,result.class);
                    intent.putExtra("gender",typeuser);
                    intent.putExtra("height",textheight);
                    intent.putExtra("weight",textweight);
                    intent.putExtra("age",age2);

                    FileOutputStream fos1 = null;
                    try {
                        fos1 = openFileOutput(FILE_NAME1, MODE_PRIVATE);
                        fos1.write(textweight.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if(fos1!=null){
                            try {
                                fos1.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    FileOutputStream fos2 = null;
                    try {
                        fos2 = openFileOutput(FILE_NAME2, MODE_PRIVATE);
                        fos2.write(textheight.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if(fos2!=null){
                            try {
                                fos2.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    startActivity(intent);
                }

            }
        });

    }
}