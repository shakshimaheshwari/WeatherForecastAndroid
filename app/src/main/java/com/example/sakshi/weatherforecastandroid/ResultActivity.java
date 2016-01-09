package com.example.sakshi.weatherforecastandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ResultActivity extends AppCompatActivity {
    TextView summarytextname, temperaturecurrently, precipitationvaluecurrently, chanceraincurrently, windSpeedcurrently, dewPointcurrently;
    TextView humiditycurrently, visibilitycurrently, sunrisecurrently, sunsetcurrently, highlowTemperature;
    String value, icon_image, summary, temperature, img_url, timezone;
    Button bMoreDetails, Viewmap;
    ImageView imgsummary,fbbtn;
    ShareDialog sd1;
    CallbackManager cbm1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_result);
        sd1 = new ShareDialog(this);
        cbm1 = CallbackManager.Factory.create();
        Intent interaction = getIntent();
        Bundle b = interaction.getExtras();
        final String resultdata = (String) b.get("jsondata");
        final String cityname = (String) b.get("cityname");
        final String statename = (String) b.get("StateName");
        final String temperaturetype = (String)b.get("radiogroup");
        summarytextname = (TextView) findViewById(R.id.SummaryText);
        temperaturecurrently = (TextView) findViewById(R.id.TemperatureId);
        precipitationvaluecurrently = (TextView) findViewById(R.id.PrecipiationValue);
        chanceraincurrently = (TextView) findViewById(R.id.ChanceRainValue);
        windSpeedcurrently = (TextView) findViewById(R.id.windSpeedValue);
        dewPointcurrently = (TextView) findViewById(R.id.dewPointValue);
        visibilitycurrently = (TextView) findViewById(R.id.visibilityValue);
        humiditycurrently = (TextView) findViewById(R.id.humidityValue);
        sunrisecurrently = (TextView) findViewById(R.id.sunriseValue);
        sunsetcurrently = (TextView) findViewById(R.id.sunsetValue);
        highlowTemperature = (TextView) findViewById(R.id.HighLowTemperatureId);
        bMoreDetails = (Button) findViewById(R.id.MoreDetailsButton);
        Viewmap = (Button) findViewById(R.id.ViewMapButton);
        fbbtn = (ImageView) findViewById(R.id.facebookbtnId);
        fbbtn.setImageResource(R.mipmap.fb_icon);
        imgsummary = (ImageView)findViewById(R.id.SummaryIconView);

        try {
            JSONObject jsonObject3 = new JSONObject(resultdata);
            timezone = jsonObject3.getString("timezone");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(resultdata).getJSONObject("currently");

            String icon = jsonObject.getString("icon");
            if(icon.equals("clear-day"))
            {
                icon_image = "clear.png";
                imgsummary.setImageResource(R.mipmap.clear);
            }
            if(icon.equals("clear-night"))
            {
                icon_image = "clear_night.png";
                imgsummary.setImageResource(R.mipmap.clear_night);
            }
            if(icon.equals("rain"))
            {
                icon_image = "rain.png";
                imgsummary.setImageResource(R.mipmap.rain);
            }
            if(icon.equals("sleet"))
            {
                icon_image = "sleet.png";
                imgsummary.setImageResource(R.mipmap.sleet);
            }
            if(icon.equals("snow"))
            {
                icon_image = "snow.png";
                imgsummary.setImageResource(R.mipmap.snow);
            }

            if(icon.equals("wind"))
            {
                icon_image = "wind.png";
                imgsummary.setImageResource(R.mipmap.wind);
            }
            if(icon.equals("fog"))
            {
                icon_image = "fog.png";
                imgsummary.setImageResource(R.mipmap.fog);
            }
            if(icon.equals("cloudy"))
            {
                icon_image = "cloudy.png";
                imgsummary.setImageResource(R.mipmap.cloudy);
            }
            if(icon.equals("partly-cloudy-day"))
            {
                icon_image = "cloud_day.png";
                imgsummary.setImageResource(R.mipmap.cloud_day);
            }
            if(icon.equals("partly-cloudy-night"))
            {
                icon_image = "cloud_night.png";
                imgsummary.setImageResource(R.mipmap.cloud_night);
            }
            img_url = "http://cs-server.usc.edu:45678/hw/hw8/images/"+icon_image;
            summary = jsonObject.getString("summary");
            String summarytext = summary + " in" + " " + (String) b.get("cityname") + "," + (String) b.get("StateName");
            summarytextname.setText(summarytext);
            double temperatureDouble = jsonObject.getDouble("temperature");
            int temperatureInt = (int)temperatureDouble;
            if(temperaturetype.equals("Farenheit"))
            {
                temperature = Integer.toString(temperatureInt)+(char)0x00B0+"F";
            }
            else
            {
                temperature = Integer.toString(temperatureInt)+(char)0x00B0+"C";
            }

            temperaturecurrently.setText(temperature);
            double precipitation = jsonObject.getDouble("precipIntensity");
            if(temperaturetype.equals("Celcius"))
            {
                precipitation = precipitation/25.4;
            }
            if (precipitation >= 0 && precipitation < 0.002) {
                value = "None";
            } else if (precipitation >= 0.002 && precipitation < 0.017) {
                value = "Very light";
            } else if (precipitation >= 0.017 && precipitation < 0.1) {
                value = "Light";
            } else if (precipitation >= 0.1 && precipitation < 0.4) {
                value = "Moderate";

            } else if (precipitation >= 0.4) {
                value = "Heavy";
            }
            precipitationvaluecurrently.setText(value);
            String s;
            double chancerain = jsonObject.getDouble("precipProbability");
            int chanceraininpercentage = (int)chancerain * 100;
            if(temperaturetype.equals("Farenheit"))
            {
                s = Integer.toString(chanceraininpercentage)+"%" ;
            }
            else {
                s = Integer.toString(chanceraininpercentage)+"%";
            }
            chanceraincurrently.setText(s);

            String windspeed = jsonObject.getString("windSpeed");
            if(temperaturetype.equals("Farenheit"))
            {
                windSpeedcurrently.setText(windspeed+"mph");
            }
            else
            {
                windSpeedcurrently.setText(windspeed+"m/s");
            }


            double dewpoint = jsonObject.getDouble("dewPoint");
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMinimumFractionDigits(2);
            formatter.setMaximumFractionDigits(2);
            String dewpoint1 = formatter.format(dewpoint);
            if(temperaturetype.equals("Farenheit"))
            {
                dewPointcurrently.setText(dewpoint1+(char)0x00B0+"F");
            }
            else
            {
                dewPointcurrently.setText(dewpoint1+(char)0x00B0+"C");
            }

            double humidity = jsonObject.getDouble("humidity");
            int humidityinpercentage = (int)humidity * 100;
            String humid = Integer.toString(humidityinpercentage);
            if(temperaturetype.equals("Farenheit")) {
                humiditycurrently.setText(humid+"%");
            }
            else
            {
                humiditycurrently.setText(humid+"%");
            }

            double visibility = jsonObject.getDouble("visibility");
            NumberFormat formatter1 = NumberFormat.getNumberInstance();
            formatter1.setMinimumFractionDigits(2);
            formatter1.setMaximumFractionDigits(2);
            String visibility1 = formatter1.format(visibility);
            if(temperaturetype.equals("Farenheit"))
            {
                visibilitycurrently.setText(visibility1+"mi");
            }
            else
            {
                visibilitycurrently.setText(visibility1+"km");
            }


            JSONObject jsonObject1 = new JSONObject(resultdata).getJSONObject("daily");
            JSONArray datarray = jsonObject1.getJSONArray("data");
            JSONObject cdata = datarray.getJSONObject(0);
            long sunrise = cdata.getLong("sunriseTime");
            long sunset = cdata.getLong("sunsetTime");



            Date date= new Date(sunrise*1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            sdf.setTimeZone(TimeZone.getTimeZone(timezone));
            String sunrisetime = sdf.format(date);

            Date date1= new Date(sunset*1000L);
            SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm a");
            sdf1.setTimeZone(TimeZone.getTimeZone(timezone));
            String sunsettime = sdf1.format(date1);
            sunrisecurrently.setText(sunrisetime);
            sunsetcurrently.setText(sunsettime);

            double tempMindouble = cdata.getDouble("temperatureMin");
            int tempminint = (int)tempMindouble;
            String temperatureMin = Integer.toString(tempminint);

            double tempMaxdouble = cdata.getDouble("temperatureMax");
            int tempmaxint = (int)tempMaxdouble;
            String temperatureMax = Integer.toString(tempmaxint);
            String temperatureHighLow = "L: " + temperatureMin + " |" + "H: " + temperatureMax;
            highlowTemperature.setText(temperatureHighLow);

            bMoreDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
                    i.putExtra("Moredetailsdata", resultdata);
                    i.putExtra("cityname", cityname);
                    i.putExtra("stateName", statename);
                    i.putExtra("radiogroup",temperaturetype);
                    startActivity(i);
                }
            });

            Viewmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                    intent.putExtra("Moredetailsdata", resultdata);
                    startActivity(intent);

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }



        fbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ShareLinkContent slc = new ShareLinkContent.Builder()
                        .setContentTitle("Current weather in" + cityname + ", " + statename)
                        .setContentDescription(summary + "," + temperature)
                        .setContentUrl(Uri.parse(img_url))
                        .build();
                sd1.show(slc);
                sd1.registerCallback(cbm1, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(ResultActivity.this,"The post has been posted successfully",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(ResultActivity.this,"Post has not been posted",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });

            }
        });


    }
}
