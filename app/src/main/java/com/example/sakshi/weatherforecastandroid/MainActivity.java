package com.example.sakshi.weatherforecastandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Pair;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    EditText streetaddress,cityname;
    String StreetAddress,cityName,statename,FCTemp;
    Button bSearchButton;
    Button bResetButton;
    Button bAbout;
    TextView tErrorLabel;
    Spinner sStateName;
    RadioButton rFarenheit;
    RadioButton rCelcius;
    ImageView imgview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        streetaddress = (EditText)findViewById(R.id.streetAddressText);
        cityname = (EditText)findViewById(R.id.cityNameText);
        bSearchButton = (Button)findViewById(R.id.SearchButton);
        bResetButton = (Button)findViewById(R.id.ClearButton);
        bAbout = (Button)findViewById(R.id.AboutButton);
        tErrorLabel = (TextView)findViewById(R.id.ErrorText);
        sStateName = (Spinner)findViewById(R.id.stateName);
        rFarenheit = (RadioButton)findViewById(R.id.RadioFarenheit);
        rCelcius = (RadioButton)findViewById(R.id.RadioCelcius);
        imgview = (ImageView)findViewById(R.id.imageView);


        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addCategory((Intent.CATEGORY_BROWSABLE));
                i.setData(Uri.parse("http://forecast.io"));
                startActivity(i);
            }
        });
        bAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(i);
            }
        });

        rFarenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rCelcius.setChecked(false);
            }
        });

        rCelcius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rFarenheit.setChecked(false);
            }
        });

        // code for the clear button
        bResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                streetaddress.setText("");
                cityname.setText("");
                sStateName.setSelection(0);
                rFarenheit.setChecked(true);
                rCelcius.setChecked(false);

            }
        });

        bSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StreetAddress = streetaddress.getText().toString();
                cityName = cityname.getText().toString();
                statename = sStateName.getSelectedItem().toString();
                if(rFarenheit.isChecked())
                {
                    FCTemp = "Farenheit";
                }
                else
                {
                    FCTemp = "Celcius";
                }

                if(!validateStreetAddress(StreetAddress))
                {
                    tErrorLabel.setText("Please enter a Street");
                    streetaddress.requestFocus();
                }

                else if(!validateCityName(cityName))
                {
                    tErrorLabel.setText("Please enter a city");
                    cityname.requestFocus();
                }

                else if(!validateStateName(statename))
                {
                    tErrorLabel.setText("Please select a state");
                    sStateName.requestFocus();
                }
                else
                {
                    tErrorLabel.setText("");
                    Toast.makeText(MainActivity.this,"Validation Successful!!!!!",Toast.LENGTH_LONG).show();
                    new Networking().execute("http://forecast-prediction.elasticbeanstalk.com/");

                }

            }
        });
    }

    private boolean validateStateName(String statename) {
        if(statename.equals("Select"))
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    private boolean validateCityName(String cityName) {
        if(cityName.length() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    protected boolean validateStreetAddress(String streetAddress) {
        if(streetAddress.length() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public class Networking extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params)
        {
            String response ="";
            HttpURLConnection httpURLConnection = null;
            try
            {
                final String baseurl = params[0];
                Uri builtUri = Uri.parse(baseurl)
                        .buildUpon()
                        .appendQueryParameter("streetAdd", StreetAddress)
                        .appendQueryParameter("cityname",cityName)
                        .appendQueryParameter("StateName",statename)
                        .appendQueryParameter("radiogroup",FCTemp)
                        .build();

                URL url = new URL(builtUri.toString());
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                httpURLConnection.setRequestMethod("GET");
                /*OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.flush();
                writer.close();
                os.close();*/
                int responseCode = httpURLConnection.getResponseCode();

                if(responseCode == httpURLConnection.HTTP_OK)
                {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while((line= br.readLine())!=null)
                    {
                        response+=line;
                    }

                }
                else
                {
                     response ="";
                }

                return response;
            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally {
                if(httpURLConnection!=null)
                {
                    httpURLConnection.disconnect();
                }
            }
            return null;
        }

        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            Intent i = new Intent(getApplicationContext(),ResultActivity.class);
            i.putExtra("jsondata",result);
            i.putExtra("cityname",cityName);
            i.putExtra("StateName",statename);
            i.putExtra("radiogroup",FCTemp);
            startActivity(i);

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
