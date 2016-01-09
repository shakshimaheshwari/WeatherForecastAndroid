package com.example.sakshi.weatherforecastandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hamweather.aeris.model.Summary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DetailsActivity extends AppCompatActivity {
    TextView TextHeading;
    Button bMore7Days, b28hoursMore, b24hrs;
    TextView TextTemperature;
    String Temperaturetype, timezone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent interaction = getIntent();
        Bundle b = interaction.getExtras();
        final String moredetailsresult = (String)b.get("Moredetailsdata");
        final String city = (String)b.get("cityname");
        final String statename = (String)b.get("stateName");
        final String temperaturetype = (String)b.get("radiogroup");

        try {
            JSONObject jsonObject3 = new JSONObject(moredetailsresult);
            timezone = jsonObject3.getString("timezone");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextHeading = (TextView)findViewById(R.id.MoreDetailsTextId);
        String text = "More Details for "+city+", "+statename;
        TextHeading.setText(text);
        TextTemperature = (TextView)findViewById(R.id.TemperatureId);
        if(temperaturetype.equals("Farenheit"))
        {
            Temperaturetype = "Temp"+"("+(char)0x00B0+"F)";
        }
        else
        {
            Temperaturetype = "Temp"+"("+(char)0x00B0+"C)";
        }
        TextTemperature.setText(Temperaturetype);
        b24hrs = (Button)findViewById(R.id.Next24hrsId);
        b24hrs.setBackgroundColor(Color.BLUE);
        bMore7Days = (Button)findViewById(R.id.Next7daysId);
        bMore7Days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b24hrs.setBackgroundColor(Color.WHITE);
                bMore7Days.setBackgroundColor(Color.BLUE);

                Intent i = new Intent(getApplicationContext(), More7DaysDetail.class);
                i.putExtra("Moredetailsdata", moredetailsresult);
                i.putExtra("cityname", city);
                i.putExtra("stateName", statename);
                i.putExtra("radiogroup",temperaturetype);
                startActivity(i);

            }
        });
        init(moredetailsresult);
    }
    public void init(final String moredetailsresult)
    {
        final TableLayout l1 = (TableLayout)findViewById(R.id.DynamicTableRow);
        TextView timeId, tempId;
        ImageView imgId;

        for(int i=0;i<24;i++)
        {

            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            if(i%2 ==0)
            {
                row.setBackgroundColor(Color.rgb(169,169,169));
            }
            else
            {
                row.setBackgroundColor(Color.WHITE);
            }
            JSONObject jsonObject1 = null;
            long time;
            String Summary;
            String temperature = "";
            String timecounter = "";
            try {

                JSONObject jsonObject = new JSONObject(moredetailsresult);
                String timezone = jsonObject.getString("timezone");
                jsonObject1 = new JSONObject(moredetailsresult).getJSONObject("hourly");
                JSONArray datarray = jsonObject1.getJSONArray("data");

                JSONObject cdata = datarray.getJSONObject(i);
                imgId = new ImageView(this);
                timeId = new TextView(this);
                tempId = new TextView(this);

                time = cdata.getLong("time");
                Date date= new Date(time*1000L);
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                sdf.setTimeZone(TimeZone.getTimeZone(timezone));
                timecounter = sdf.format(date);
                timeId.setText(timecounter);
                timeId.setTypeface(Typeface.DEFAULT_BOLD);
                timeId.setGravity(0x003);
                row.addView(timeId);
                Summary = cdata.getString("icon");
                int width =250;
                int height = 200;
                TableRow.LayoutParams parms = new TableRow.LayoutParams(width,height);
                parms.setMargins(100,0,0,0);
                imgId.setLayoutParams(parms);

                if(Summary.equals("clear-day"))
                {
                    imgId.setImageResource(R.mipmap.clear);
                }
                if(Summary.equals("clear-night"))
                {

                    imgId.setImageResource(R.mipmap.clear_night);
                }
                if(Summary.equals("rain"))
                {
                    imgId.setImageResource(R.mipmap.rain);
                }
                if(Summary.equals("sleet"))
                {
                    imgId.setImageResource(R.mipmap.sleet);
                }
                if(Summary.equals("snow"))
                {
                    imgId.setImageResource(R.mipmap.snow);
                }

                if(Summary.equals("wind"))
                {
                    imgId.setImageResource(R.mipmap.wind);
                }
                if(Summary.equals("fog"))
                {
                    imgId.setImageResource(R.mipmap.fog);
                }
                if(Summary.equals("cloudy"))
                {
                    imgId.setImageResource(R.mipmap.cloudy);
                }
                if(Summary.equals("partly-cloudy-day"))
                {
                    imgId.setImageResource(R.mipmap.cloud_day);
                }
                if(Summary.equals("partly-cloudy-night"))
                {

                    imgId.setImageResource(R.mipmap.cloud_night);
                }

                row.addView(imgId);
                double temperaturedouble = cdata.getDouble("temperature");
                int temperatureint = (int)temperaturedouble;
                temperature = Integer.toString(temperatureint);
                tempId.setText(temperature);
                TableRow.LayoutParams layout = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
                layout.setMargins(300,0,0,0);
                tempId.setLayoutParams(layout);
                row.addView(tempId);
                l1.addView(row,i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        AddButton(moredetailsresult);
    }

    public void AddButton(final String moredetailsdata)
    {
        final TableLayout l1 = (TableLayout)findViewById(R.id.DynamicTableRow);
        final TableRow rowbutton = new TableRow(this);
        Button button = new Button(this);
        TableRow.LayoutParams layoutbutton =new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
        rowbutton.setGravity(Gravity.CENTER);
        button.setLayoutParams(layoutbutton);
        button.setText("+");
        rowbutton.addView(button);
        l1.addView(rowbutton,24);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.removeView(rowbutton);
                init1(moredetailsdata);
            }
        });
    }

    public void init1(String moredetailsresult)
    {
        final TableLayout l1 = (TableLayout)findViewById(R.id.DynamicTableRow);
        TextView timeId, tempId;
        ImageView imgId;

        for(int i=0;i<48;i++)
        {

            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            if(i%2 ==0)
            {
                row.setBackgroundColor(Color.rgb(169,169,169));
            }
            else
            {
                row.setBackgroundColor(Color.WHITE);
            }
            JSONObject jsonObject1 = null;
            long time;
            String Summary;
            String temperature = "";
            String timecounter = "";
            try {

                JSONObject jsonObject = new JSONObject(moredetailsresult);
                String timezone = jsonObject.getString("timezone");
                jsonObject1 = new JSONObject(moredetailsresult).getJSONObject("hourly");
                JSONArray datarray = jsonObject1.getJSONArray("data");

                JSONObject cdata = datarray.getJSONObject(i);
                imgId = new ImageView(this);
                timeId = new TextView(this);
                tempId = new TextView(this);

                time = cdata.getLong("time");
                Date date= new Date(time*1000L);
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                sdf.setTimeZone(TimeZone.getTimeZone(timezone));
                timecounter = sdf.format(date);
                timeId.setText(timecounter);
                timeId.setTypeface(Typeface.DEFAULT_BOLD);
                timeId.setGravity(0x003);
                row.addView(timeId);
                Summary = cdata.getString("icon");
                int width =250;
                int height = 200;
                TableRow.LayoutParams parms = new TableRow.LayoutParams(width,height);
                parms.setMargins(100,0,0,0);
                imgId.setLayoutParams(parms);

                if(Summary.equals("clear-day"))
                {
                    imgId.setImageResource(R.mipmap.clear);
                }
                if(Summary.equals("clear-night"))
                {

                    imgId.setImageResource(R.mipmap.clear_night);
                }
                if(Summary.equals("rain"))
                {
                    imgId.setImageResource(R.mipmap.rain);
                }
                if(Summary.equals("sleet"))
                {
                    imgId.setImageResource(R.mipmap.sleet);
                }
                if(Summary.equals("snow"))
                {
                    imgId.setImageResource(R.mipmap.snow);
                }

                if(Summary.equals("wind"))
                {
                    imgId.setImageResource(R.mipmap.wind);
                }
                if(Summary.equals("fog"))
                {
                    imgId.setImageResource(R.mipmap.fog);
                }
                if(Summary.equals("cloudy"))
                {
                    imgId.setImageResource(R.mipmap.cloudy);
                }
                if(Summary.equals("partly-cloudy-day"))
                {
                    imgId.setImageResource(R.mipmap.cloud_day);
                }
                if(Summary.equals("partly-cloudy-night"))
                {

                    imgId.setImageResource(R.mipmap.cloud_night);
                }

                row.addView(imgId);
                double temperaturedouble = cdata.getDouble("temperature");
                int temperatureint = (int)temperaturedouble;
                temperature = Integer.toString(temperatureint);
                tempId.setText(temperature);
                TableRow.LayoutParams layout = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
                layout.setMargins(300,0,0,0);
                tempId.setLayoutParams(layout);
                row.addView(tempId);
                l1.addView(row,i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}

