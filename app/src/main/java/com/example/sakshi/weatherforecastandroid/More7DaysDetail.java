package com.example.sakshi.weatherforecastandroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class More7DaysDetail extends AppCompatActivity {
     TextView Heading,DayDate0,MinMaxTemp0;
     ImageView Summary0, Summary1,Summary2,Summary3, Summary4, Summary5, Summary6;
     TextView DayDate1,MinMaxTemp1;
     TextView DayDate2,MinMaxTemp2;
     TextView DayDate3,MinMaxTemp3;
     TextView DayDate4,MinMaxTemp4;
     TextView DayDate5,MinMaxTemp5;
     TextView DayDate6,MinMaxTemp6;
     Button bNext24hrs, bNext7Days;
    String timezone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more7_days_detail);
        Intent interaction = getIntent();
        Bundle b = interaction.getExtras();
        final String sevendaysdata = (String)b.get("Moredetailsdata");
        final String city = (String)b.get("cityname");
        final String statename = (String)b.get("stateName");
        final String temperaturetype = (String)b.get("radiogroup");
        Heading = (TextView)findViewById(R.id.MoreDetails7daysTextId);
        String text = "More Details for "+city+", "+statename;
        Heading.setText(text);
        DayDate0 = (TextView)findViewById(R.id.Day0Id);
        Summary0 = (ImageView)findViewById(R.id.Summary0Id);
        MinMaxTemp0 = (TextView)findViewById(R.id.MinMaxTemp0Id);
        DayDate1 = (TextView)findViewById(R.id.Day1Id);
        Summary1 = (ImageView)findViewById(R.id.Summary1Id);
        MinMaxTemp1 = (TextView)findViewById(R.id.MinMaxTemp1Id);
        DayDate2 = (TextView)findViewById(R.id.Day2Id);
        Summary2 = (ImageView)findViewById(R.id.Summary2Id);
        MinMaxTemp2 = (TextView)findViewById(R.id.MinMaxTemp2Id);
        DayDate3 = (TextView)findViewById(R.id.Day3Id);
        Summary3 = (ImageView)findViewById(R.id.Summary3Id);
        MinMaxTemp3 = (TextView)findViewById(R.id.MinMaxTemp3Id);
        DayDate4 = (TextView)findViewById(R.id.Day4Id);
        Summary4 = (ImageView)findViewById(R.id.Summary4Id);
        MinMaxTemp4 = (TextView)findViewById(R.id.MinMaxTemp4Id);
        DayDate5 = (TextView)findViewById(R.id.Day5Id);
        Summary5 = (ImageView)findViewById(R.id.Summary5Id);
        MinMaxTemp5 = (TextView)findViewById(R.id.MinMaxTemp5Id);
        DayDate6 = (TextView)findViewById(R.id.Day6Id);
        Summary6 = (ImageView)findViewById(R.id.Summary6Id);
        MinMaxTemp6 = (TextView)findViewById(R.id.MinMaxTemp6Id);
        bNext24hrs = (Button) findViewById(R.id.Next24hrsWeekId);
        bNext7Days = (Button)findViewById(R.id.Next7daysWeekId);
        bNext7Days.setBackgroundColor(Color.BLUE);
        try {
            JSONObject jsonObject2 = new JSONObject(sevendaysdata);
            timezone = jsonObject2.getString("timezone");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject1 = null;
        try {
            long time, time1, time2, time3, time4, time5, time6;
            String Summary,temperaturemin,temperaturemax,temperatureminmax;
            String Summary11,temperaturemin1,temperaturemax1,temperatureminmax1;
            String Summary22,temperaturemin2,temperaturemax2,temperatureminmax2;
            String Summary33,temperaturemin3,temperaturemax3,temperatureminmax3;
            String Summary44,temperaturemin4,temperaturemax4,temperatureminmax4;
            String Summary55,temperaturemin5,temperaturemax5,temperatureminmax5;
            String Summary66,temperaturemin6,temperaturemax6,temperatureminmax6;
            jsonObject1 = new JSONObject(sevendaysdata).getJSONObject("daily");
            JSONArray datarray = jsonObject1.getJSONArray("data");
                JSONObject cdata = datarray.getJSONObject(1);
                time = cdata.getLong("time");
                Date date= new Date(time*1000L);
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd");
                sdf.setTimeZone(TimeZone.getTimeZone(timezone));
                sdf1.setTimeZone(TimeZone.getTimeZone(timezone));
                String weekday0 = sdf.format(date);
                String datemonth0 = sdf1.format(date);
                String combined = weekday0 + ","+ datemonth0;
                Summary = cdata.getString("icon");
                if(Summary.equals("clear-day"))
                {
                    Summary0.setImageResource(R.mipmap.clear);
                }
                if(Summary.equals("clear-night"))
                {
                    Summary0.setImageResource(R.mipmap.clear_night);
                }
                if(Summary.equals("rain"))
                {
                    Summary0.setImageResource(R.mipmap.rain);
                }
                if(Summary.equals("sleet"))
                {
                    Summary0.setImageResource(R.mipmap.sleet);
                }
                if(Summary.equals("snow"))
                {
                    Summary0.setImageResource(R.mipmap.snow);
                }

                if(Summary.equals("wind"))
                {
                    Summary0.setImageResource(R.mipmap.wind);
                }
                if(Summary.equals("fog"))
                {
                    Summary0.setImageResource(R.mipmap.fog);
                }
                if(Summary.equals("cloudy"))
                {
                    Summary0.setImageResource(R.mipmap.cloudy);
                }
                if(Summary.equals("partly-cloudy-day"))
                {
                    Summary0.setImageResource(R.mipmap.cloud_day);
                }
                if(Summary.equals("partly-cloudy-night"))
                {
                    Summary0.setImageResource(R.mipmap.cloud_night);
                }
                double tempmin = cdata.getDouble("temperatureMin");
                int tempminint =(int)tempmin;
                temperaturemin = Integer.toString(tempminint);
                double tempmax = cdata.getDouble("temperatureMax");
                int tempmaxint = (int)tempmax;
                temperaturemax = Integer.toString(tempmaxint);
                if(temperaturetype.equals("Farenheit"))
                {
                    temperatureminmax = "Min: "+temperaturemin +(char)0x00B0+"F"+" |" + "Max: "+temperaturemax+(char)0x00B0+"F";
                }
               else
                {
                    temperatureminmax = "Min: "+temperaturemin +(char)0x00B0+"C"+" |" + "Max: "+temperaturemax+(char)0x00B0+"C";
                }

            DayDate0.setText(combined);
            MinMaxTemp0.setText(temperatureminmax);
            JSONObject cdata1 = datarray.getJSONObject(2);
            time1 = cdata1.getLong("time");
            Date date1= new Date(time1*1000L);
            SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
            SimpleDateFormat sdf3 = new SimpleDateFormat("MMM dd");
            sdf2.setTimeZone(TimeZone.getTimeZone(timezone));
            sdf3.setTimeZone(TimeZone.getTimeZone(timezone));
            String weekday1 = sdf2.format(date1);
            String datemonth1 = sdf3.format(date1);
            String combined1 = weekday1 + ","+ datemonth1;
            Summary11 = cdata1.getString("icon");
            if(Summary11.equals("clear-day"))
            {
                Summary1.setImageResource(R.mipmap.clear);
            }
            if(Summary11.equals("clear-night"))
            {
                Summary1.setImageResource(R.mipmap.clear_night);
            }
            if(Summary11.equals("rain"))
            {
                Summary1.setImageResource(R.mipmap.rain);
            }
            if(Summary11.equals("sleet"))
            {
                Summary1.setImageResource(R.mipmap.sleet);
            }
            if(Summary11.equals("snow"))
            {
                Summary1.setImageResource(R.mipmap.snow);
            }

            if(Summary11.equals("wind"))
            {
                Summary1.setImageResource(R.mipmap.wind);
            }
            if(Summary11.equals("fog"))
            {
                Summary1.setImageResource(R.mipmap.fog);
            }
            if(Summary11.equals("cloudy"))
            {
                Summary1.setImageResource(R.mipmap.cloudy);
            }
            if(Summary11.equals("partly-cloudy-day"))
            {
                Summary1.setImageResource(R.mipmap.cloud_day);
            }
            if(Summary11.equals("partly-cloudy-night"))
            {
                Summary1.setImageResource(R.mipmap.cloud_night);
            }
            double tempmin1 = cdata1.getDouble("temperatureMin");
            int tempminint1 =(int)tempmin1;
            temperaturemin1 = Integer.toString(tempminint1);
            double tempmax1 = cdata1.getDouble("temperatureMax");
            int tempmaxint1 = (int)tempmax1;
            temperaturemax1 = Integer.toString(tempmaxint1);
            if(temperaturetype.equals("Farenheit"))
            {
                temperatureminmax1 = "Min: "+temperaturemin1 +(char)0x00B0+"F"+" |" + "Max: "+temperaturemax1+(char)0x00B0+"F";
            }
            else
            {
                temperatureminmax1 = "Min: "+temperaturemin1 +(char)0x00B0+"C"+" |" + "Max: "+temperaturemax1+(char)0x00B0+"C";
            }
            DayDate1.setText(combined1);
            MinMaxTemp1.setText(temperatureminmax1);
            JSONObject cdata2 = datarray.getJSONObject(3);
            time2 = cdata2.getLong("time");
            Date date2= new Date(time2*1000L);
            SimpleDateFormat sdf4 = new SimpleDateFormat("EEEE");
            SimpleDateFormat sdf5 = new SimpleDateFormat("MMM dd");
            sdf4.setTimeZone(TimeZone.getTimeZone(timezone));
            sdf5.setTimeZone(TimeZone.getTimeZone(timezone));
            String weekday2 = sdf4.format(date2);
            String datemonth2 = sdf5.format(date2);
            String combined2 = weekday2 + ","+ datemonth2;
            Summary22 = cdata2.getString("icon");
            if(Summary22.equals("clear-day"))
            {
                Summary2.setImageResource(R.mipmap.clear);
            }
            if(Summary22.equals("clear-night"))
            {
                Summary2.setImageResource(R.mipmap.clear_night);
            }
            if(Summary22.equals("rain"))
            {
                Summary2.setImageResource(R.mipmap.rain);
            }
            if(Summary22.equals("sleet"))
            {
                Summary2.setImageResource(R.mipmap.sleet);
            }
            if(Summary22.equals("snow"))
            {
                Summary2.setImageResource(R.mipmap.snow);
            }

            if(Summary22.equals("wind"))
            {
                Summary2.setImageResource(R.mipmap.wind);
            }
            if(Summary22.equals("fog"))
            {
                Summary2.setImageResource(R.mipmap.fog);
            }
            if(Summary22.equals("cloudy"))
            {
                Summary2.setImageResource(R.mipmap.cloudy);
            }
            if(Summary22.equals("partly-cloudy-day"))
            {
                Summary2.setImageResource(R.mipmap.cloud_day);
            }
            if(Summary22.equals("partly-cloudy-night"))
            {
                Summary2.setImageResource(R.mipmap.cloud_night);
            }
            double tempmin2 = cdata2.getDouble("temperatureMin");
            int tempminint2 =(int)tempmin2;
            temperaturemin2 = Integer.toString(tempminint2);
            double tempmax2 = cdata2.getDouble("temperatureMax");
            int tempmaxint2 = (int)tempmax2;
            temperaturemax2 = Integer.toString(tempmaxint2);
            if(temperaturetype.equals("Farenheit"))
            {
                temperatureminmax2 = "Min: "+temperaturemin2 +(char)0x00B0+"F"+" |" + "Max: "+temperaturemax2+(char)0x00B0+"F";
            }
            else
            {
                temperatureminmax2 = "Min: "+temperaturemin2 +(char)0x00B0+"C"+" |" + "Max: "+temperaturemax2+(char)0x00B0+"C";
            }
            DayDate2.setText(combined2);
            MinMaxTemp2.setText(temperatureminmax2);
            JSONObject cdata3 = datarray.getJSONObject(4);
            time3 = cdata3.getLong("time");
            Date date3= new Date(time3*1000L);
            SimpleDateFormat sdf6 = new SimpleDateFormat("EEEE");
            SimpleDateFormat sdf7 = new SimpleDateFormat("MMM dd");
            sdf6.setTimeZone(TimeZone.getTimeZone(timezone));
            sdf7.setTimeZone(TimeZone.getTimeZone(timezone));
            String weekday3 = sdf6.format(date3);
            String datemonth3 = sdf7.format(date3);
            String combined3 = weekday3 + ","+ datemonth3;
            Summary33 = cdata3.getString("icon");
            if(Summary33.equals("clear-day"))
            {
                Summary3.setImageResource(R.mipmap.clear);
            }
            if(Summary33.equals("clear-night"))
            {
                Summary3.setImageResource(R.mipmap.clear_night);
            }
            if(Summary33.equals("rain"))
            {
                Summary3.setImageResource(R.mipmap.rain);
            }
            if(Summary33.equals("sleet"))
            {
                Summary3.setImageResource(R.mipmap.sleet);
            }
            if(Summary33.equals("snow"))
            {
                Summary3.setImageResource(R.mipmap.snow);
            }

            if(Summary33.equals("wind"))
            {
                Summary3.setImageResource(R.mipmap.wind);
            }
            if(Summary33.equals("fog"))
            {
                Summary3.setImageResource(R.mipmap.fog);
            }
            if(Summary33.equals("cloudy"))
            {
                Summary3.setImageResource(R.mipmap.cloudy);
            }
            if(Summary33.equals("partly-cloudy-day"))
            {
                Summary3.setImageResource(R.mipmap.cloud_day);
            }
            if(Summary33.equals("partly-cloudy-night"))
            {
                Summary3.setImageResource(R.mipmap.cloud_night);
            }
            double tempmin3 = cdata3.getDouble("temperatureMin");
            int tempminint3 =(int)tempmin3;
            temperaturemin3 = Integer.toString(tempminint3);
            double tempmax3 = cdata3.getDouble("temperatureMax");
            int tempmaxint3 = (int)tempmax3;
            temperaturemax3 = Integer.toString(tempmaxint3);
            if(temperaturetype.equals("Farenheit"))
            {
                temperatureminmax3 = "Min: "+temperaturemin3 +(char)0x00B0+"F"+" |" + "Max: "+temperaturemax3+(char)0x00B0+"F";
            }
            else
            {
                temperatureminmax3 = "Min: "+temperaturemin3 +(char)0x00B0+"C"+" |" + "Max: "+temperaturemax3+(char)0x00B0+"C";
            }
            DayDate3.setText(combined3);
            MinMaxTemp3.setText(temperatureminmax3);
            JSONObject cdata4 = datarray.getJSONObject(5);
            time4 = cdata4.getLong("time");
            Date date4= new Date(time4*1000L);
            SimpleDateFormat sdf8 = new SimpleDateFormat("EEEE");
            SimpleDateFormat sdf9 = new SimpleDateFormat("MMM dd");
            sdf8.setTimeZone(TimeZone.getTimeZone(timezone));
            sdf9.setTimeZone(TimeZone.getTimeZone(timezone));
            String weekday4 = sdf8.format(date4);
            String datemonth4 = sdf9.format(date4);
            String combined4 = weekday4 + ","+ datemonth4;
            Summary44 = cdata4.getString("icon");
            if(Summary44.equals("clear-day"))
            {
                Summary4.setImageResource(R.mipmap.clear);
            }
            if(Summary44.equals("clear-night"))
            {
                Summary4.setImageResource(R.mipmap.clear_night);
            }
            if(Summary44.equals("rain"))
            {
                Summary4.setImageResource(R.mipmap.rain);
            }
            if(Summary44.equals("sleet"))
            {
                Summary4.setImageResource(R.mipmap.sleet);
            }
            if(Summary44.equals("snow"))
            {
                Summary4.setImageResource(R.mipmap.snow);
            }

            if(Summary44.equals("wind"))
            {
                Summary4.setImageResource(R.mipmap.wind);
            }
            if(Summary44.equals("fog"))
            {
                Summary4.setImageResource(R.mipmap.fog);
            }
            if(Summary44.equals("cloudy"))
            {
                Summary4.setImageResource(R.mipmap.cloudy);
            }
            if(Summary44.equals("partly-cloudy-day"))
            {
                Summary4.setImageResource(R.mipmap.cloud_day);
            }
            if(Summary44.equals("partly-cloudy-night"))
            {
                Summary4.setImageResource(R.mipmap.cloud_night);
            }
            double tempmin4 = cdata4.getDouble("temperatureMin");
            int tempminint4 =(int)tempmin4;
            temperaturemin4 = Integer.toString(tempminint4);
            double tempmax4 = cdata4.getDouble("temperatureMax");
            int tempmaxint4 = (int)tempmax4;
            temperaturemax4 = Integer.toString(tempmaxint4);
            if(temperaturetype.equals("Farenheit"))
            {
                temperatureminmax4 = "Min: "+temperaturemin4 +(char)0x00B0+"F"+" |" + "Max: "+temperaturemax4+(char)0x00B0+"F";
            }
            else
            {
                temperatureminmax4 = "Min: "+temperaturemin4 +(char)0x00B0+"C"+" |" + "Max: "+temperaturemax4+(char)0x00B0+"C";
            }
            DayDate4.setText(combined4);
            MinMaxTemp4.setText(temperatureminmax4);
            JSONObject cdata5 = datarray.getJSONObject(6);
            time5 = cdata5.getLong("time");
            Date date5= new Date(time5*1000L);
            SimpleDateFormat sdf10 = new SimpleDateFormat("EEEE");
            SimpleDateFormat sdf11 = new SimpleDateFormat("MMM dd");
            sdf10.setTimeZone(TimeZone.getTimeZone(timezone));
            sdf11.setTimeZone(TimeZone.getTimeZone(timezone));
            String weekday5 = sdf10.format(date5);
            String datemonth5 = sdf11.format(date5);
            String combined5 = weekday5 + ","+ datemonth5;
            Summary55 = cdata5.getString("icon");
            if(Summary55.equals("clear-day"))
            {
                Summary5.setImageResource(R.mipmap.clear);
            }
            if(Summary55.equals("clear-night"))
            {
                Summary5.setImageResource(R.mipmap.clear_night);
            }
            if(Summary55.equals("rain"))
            {
                Summary5.setImageResource(R.mipmap.rain);
            }
            if(Summary55.equals("sleet"))
            {
                Summary5.setImageResource(R.mipmap.sleet);
            }
            if(Summary55.equals("snow"))
            {
                Summary5.setImageResource(R.mipmap.snow);
            }

            if(Summary55.equals("wind"))
            {
                Summary5.setImageResource(R.mipmap.wind);
            }
            if(Summary55.equals("fog"))
            {
                Summary5.setImageResource(R.mipmap.fog);
            }
            if(Summary55.equals("cloudy"))
            {
                Summary5.setImageResource(R.mipmap.cloudy);
            }
            if(Summary55.equals("partly-cloudy-day"))
            {
                Summary5.setImageResource(R.mipmap.cloud_day);
            }
            if(Summary55.equals("partly-cloudy-night"))
            {
                Summary5.setImageResource(R.mipmap.cloud_night);
            }
            double tempmin5 = cdata5.getDouble("temperatureMin");
            int tempminint5 =(int)tempmin5;
            temperaturemin5 = Integer.toString(tempminint5);
            double tempmax5 = cdata5.getDouble("temperatureMax");
            int tempmaxint5 = (int)tempmax5;
            temperaturemax5 = Integer.toString(tempmaxint5);
            if(temperaturetype.equals("Farenheit"))
            {
                temperatureminmax5 = "Min: "+temperaturemin5 +(char)0x00B0+"F"+" |" + "Max: "+temperaturemax5+(char)0x00B0+"F";
            }
            else
            {
                temperatureminmax5 = "Min: "+temperaturemin5 +(char)0x00B0+"C"+" |" + "Max: "+temperaturemax5+(char)0x00B0+"C";
            }
            DayDate5.setText(combined5);
            MinMaxTemp5.setText(temperatureminmax5);
            JSONObject cdata6 = datarray.getJSONObject(7);
            time6 = cdata6.getLong("time");
            Date date6= new Date(time6*1000L);
            SimpleDateFormat sdf12 = new SimpleDateFormat("EEEE");
            SimpleDateFormat sdf13 = new SimpleDateFormat("MMM dd");
            sdf12.setTimeZone(TimeZone.getTimeZone(timezone));
            sdf13.setTimeZone(TimeZone.getTimeZone(timezone));
            String weekday6 = sdf12.format(date6);
            String datemonth6 = sdf13.format(date6);
            String combined6 = weekday6 + ","+ datemonth6;
            Summary66 = cdata6.getString("icon");
            if(Summary66.equals("clear-day"))
            {
                Summary6.setImageResource(R.mipmap.clear);
            }
            if(Summary66.equals("clear-night"))
            {
                Summary6.setImageResource(R.mipmap.clear_night);
            }
            if(Summary66.equals("rain"))
            {
                Summary6.setImageResource(R.mipmap.rain);
            }
            if(Summary66.equals("sleet"))
            {
                Summary6.setImageResource(R.mipmap.sleet);
            }
            if(Summary66.equals("snow"))
            {
                Summary6.setImageResource(R.mipmap.snow);
            }

            if(Summary66.equals("wind"))
            {
                Summary6.setImageResource(R.mipmap.wind);
            }
            if(Summary66.equals("fog"))
            {
                Summary6.setImageResource(R.mipmap.fog);
            }
            if(Summary66.equals("cloudy"))
            {
                Summary6.setImageResource(R.mipmap.cloudy);
            }
            if(Summary66.equals("partly-cloudy-day"))
            {
                Summary6.setImageResource(R.mipmap.cloud_day);
            }
            if(Summary66.equals("partly-cloudy-night"))
            {
                Summary6.setImageResource(R.mipmap.cloud_night);
            }
            double tempmin6 = cdata6.getDouble("temperatureMin");
            int tempminint6 =(int)tempmin6;
            temperaturemin6 = Integer.toString(tempminint6);
            double tempmax6 = cdata6.getDouble("temperatureMax");
            int tempmaxint6 = (int)tempmax6;
            temperaturemax6 = Integer.toString(tempmaxint6);
            if(temperaturetype.equals("Farenheit"))
            {
                temperatureminmax6 = "Min: "+temperaturemin6 +(char)0x00B0+"F"+" |" + "Max: "+temperaturemax6+(char)0x00B0+"F";
            }
            else
            {
                temperatureminmax6 = "Min: "+temperaturemin6 +(char)0x00B0+"C"+" |" + "Max: "+temperaturemax6+(char)0x00B0+"C";
            }
            DayDate6.setText(combined6);
            MinMaxTemp6.setText(temperatureminmax6);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        bNext24hrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bNext24hrs.setBackgroundColor(Color.BLUE);
                bNext7Days.setBackgroundColor(Color.WHITE);
                Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
                i.putExtra("Moredetailsdata", sevendaysdata);
                i.putExtra("cityname", city);
                i.putExtra("stateName", statename);
                i.putExtra("radiogroup",temperaturetype);
                startActivity(i);
            }
        });
    }
}
