package fr.ybsi.hypixelstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class Stats extends AppCompatActivity {


    private static TextView uuid1;
    private static String user;
    private ImageView imgButton;
    private static TextView username1;
    private static TextView lastLogin1;
    private static TextView firstLogin1;
    private static TextView karma1;
    private static TextView xp1;
    private static TextView level1;
    private static TextView ap1;
    private static ImageView img;
    private static TextView coins;

    private static boolean finish = false;
    private static int level;
    private static int totalCoins;
    private static String Network_EXP ="";
    private static String achievementPoints;
    private static long lastLogin;
    private static long firstLogin;
    private static String username;
    private static String imgURL;
    private static String Karma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Intent intent = getIntent();
        user = intent.getStringExtra("username");
        this.imgButton = (ImageView) findViewById(R.id.imgbutton);



       this.uuid1 = (TextView) findViewById(R.id.uuid);
        this.username1 = (TextView) findViewById(R.id.username);
        this.lastLogin1 = (TextView) findViewById(R.id.lastLogin);
        this.firstLogin1 = (TextView) findViewById(R.id.firstLogin);
        this.karma1 = (TextView) findViewById(R.id.karma);
        this.ap1 = (TextView) findViewById(R.id.ap);
        this.xp1 = (TextView) findViewById(R.id.xp);
        this.level1 = (TextView) findViewById(R.id.level);
        this.img = (ImageView) findViewById(R.id.head);
        this.coins = (TextView) findViewById(R.id.textView11);

      //  AdView adView = new AdView(this);
       // adView.setAdSize(AdSize.BANNER);adView.setAdUnitId("ca-app-pub-6251821844352758/2468640651");

        new Thread(background).start();


        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Menu = new Intent(getApplicationContext(), menu.class);
                Menu.putExtra("username", user);
                startActivity(Menu);
                finish();
            }
        });


        while(!finish) {
            continue;
        }
        if(finish) {
            level1.setText(level+"");
            karma1.setText(Karma+"");
            coins.setText(totalCoins+"");
            xp1.setText(Network_EXP+"");
            ap1.setText(achievementPoints+"");
            username1.setText(username+"");


            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTimeInMillis(lastLogin);
            int mYear1 = calendar1.get(Calendar.YEAR);
            int mMonth1 = calendar1.get(Calendar.MONTH) + 1;
            int mDay1 = calendar1.get(Calendar.DAY_OF_MONTH);
            int mHour1 = calendar1.get(Calendar.HOUR_OF_DAY);
            int mMinute1 = calendar1.get(Calendar.MINUTE);
            int mSecond1 = calendar1.get(Calendar.SECOND);
             lastLogin1.setText(mDay1+"/"+mMonth1+"/"+mYear1+" "+mHour1+":"+mMinute1+":"+mSecond1);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(firstLogin);
            int mYear2 = calendar2.get(Calendar.YEAR);
            int mMonth2 = calendar2.get(Calendar.MONTH) + 1;
            int mDay2 = calendar2.get(Calendar.DAY_OF_MONTH);
            int mHour2 = calendar2.get(Calendar.HOUR_OF_DAY);
            int mMinute2 = calendar2.get(Calendar.MINUTE);
            int mSecond2 = calendar2.get(Calendar.SECOND);
             firstLogin1.setText(mDay2+"/"+mMonth2+"/"+mYear2+" "+mHour2+":"+mMinute2+":"+mSecond2);

             try {
                 Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imgURL).getContent());
                 img.setImageBitmap(bitmap);
             }catch(Exception e){

             }

        }

       /* imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Menu = new Intent(getApplicationContext(), menu.class);
                Menu.putExtra("username", user);
                startActivity(Menu);
                finish();
            }
        });*/

    }

    // Le runnable qui va exécuter la tâche de fond
    Runnable background = new Runnable() {

        @Override
        public void run() {



            try {
                //setContentView(R.layout.activity_waiting);

                Log.d("User", "username : "+user);

                URL url = null;
                HttpURLConnection con = null;
                String inputLine;
                String key;
                JsonObject jsonObject;

                key = "f0286aa9-4f44-48b8-8d04-90e9a45a4250";

                url = new URL("https://api.hypixel.net/player?key=" + key + "&name="+user);


                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    urlConnection.setDoOutput(true);
                    urlConnection.setChunkedStreamingMode(0);

                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();



                    imgURL = "https://cravatar.eu/head/"
                           + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "")
                           + "?254";
                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imgURL).getContent());
                    //img.setImageBitmap(bitmap);

                    username = jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", "");
                    //username1.setText(username);

                    try {
                        Network_EXP = jsonObject.getAsJsonObject("player").get("networkExp").toString();
                    } catch (NumberFormatException e) {
                        Network_EXP = "0";
                    }

                    Network_EXP = Network_EXP.replace(".0", "");
                    Network_EXP = Network_EXP.replace(".", "");
                    Network_EXP = Network_EXP.replace("E7", "");
                    Network_EXP = Network_EXP.replace("E8", "");


                   // xp1.setText(Network_EXP);
                    Log.d("EXP : ", ""+Network_EXP);
                    int EXP2 = Integer.parseInt(Network_EXP);
                    int EXP22 = 10000;
                    int EXP_last = 0;
                    int EXP3 = 0;
                    level = 1;
                    while (EXP3 < EXP2) {
                        EXP3 = EXP_last + (EXP22 += 2500);
                        EXP_last = EXP3;
                        ++level;
                        Log.d("Level : ", ""+level);
                    }
                   // level1.setText(""+level);
                    try {
                        Karma = jsonObject.getAsJsonObject("player").get("karma").toString();
                    } catch (Exception e) {
                        Karma = "0";
                    }
                   // karma1.setText(Karma);
                    try {
                        achievementPoints = jsonObject.getAsJsonObject("player").get("achievementPoints")
                                .toString();
                    } catch (Exception e) {
                        achievementPoints = "0";
                    }
                   // ap1.setText(achievementPoints);
                    try {
                        lastLogin = Long
                                .parseLong(jsonObject.getAsJsonObject("player").get("lastLogin").toString());
                    } catch (Exception e) {
                        lastLogin = 0L;
                    }
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTimeInMillis(lastLogin);
                    int mYear1 = calendar1.get(Calendar.YEAR);
                    int mMonth1 = calendar1.get(Calendar.MONTH) + 1;
                    int mDay1 = calendar1.get(Calendar.DAY_OF_MONTH);
                    int mHour1 = calendar1.get(Calendar.HOUR_OF_DAY);
                    int mMinute1 = calendar1.get(Calendar.MINUTE);
                    int mSecond1 = calendar1.get(Calendar.SECOND);
                   // lastLogin1.setText(mDay1+"/"+mMonth1+"/"+mYear1+" "+mHour1+":"+mMinute1+":"+mSecond1);
                    try {
                        firstLogin = Long
                                .parseLong(jsonObject.getAsJsonObject("player").get("firstLogin").toString());
                    } catch (Exception e) {
                        firstLogin = 0L;
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(firstLogin);
                    int mYear = calendar.get(Calendar.YEAR);
                    int mMonth = calendar.get(Calendar.MONTH) + 1;
                    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                    int mHour = calendar.get(Calendar.HOUR_OF_DAY);
                    int mMinute = calendar.get(Calendar.MINUTE);
                    int mSecond = calendar.get(Calendar.SECOND);
                    int coins1;
                   // firstLogin1.setText(mDay+"/"+mMonth+"/"+mYear+" "+mHour+":"+mMinute+":"+mSecond);
                    try {
                        coins1 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("SkyWars").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins1 = 0;
                    }
                    int coins2;
                    try {
                        coins2 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("Paintball").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins2 = 0;
                    }
                    int coins3;
                    try {
                        coins3 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("Quake").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins3 = 0;
                    }
                    int coins4;
                    try {
                        coins4 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("Battleground").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins4 = 0;
                    }
                    int coins5;
                    try {
                        coins5 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("GingerbreadPart").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins5 = 0;
                    }
                    int coins6;
                    try {
                        coins6 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("Bedwars").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins6 = 0;
                    }
                    int coins7;
                    try {
                        coins7 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("Arcade").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins7 = 0;
                    }
                    int coins8;
                    try {
                        coins8 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("UHC").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins8 = 0;
                    }
                    int coins9;
                    try {
                        coins9 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TrueCombat").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins9 = 0;
                    }
                    int coins10;
                    try {
                        coins10 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins10 = 0;
                    }
                    int coins11;
                    try {
                        coins11 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("Walls").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins11 = 0;
                    }
                    int coins12;
                    try {
                        coins12 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("VampireZ").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins12 = 0;
                    }
                    int coins13;
                    try {
                        coins13 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("Arena").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins13 = 0;
                    }
                    int coins14;
                    try {
                        coins14 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("Walls3").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins14 = 0;
                    }
                    int coins15;
                    try {
                        coins15 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("HungerGames").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins15 = 0;
                    }
                    int coins16;
                    try {
                        coins16 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("SuperSmash").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins16 = 0;
                    }
                    int coins17;
                    try {
                        coins17 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("SkyClash").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins17 = 0;
                    }
                    int coins18;
                    try {
                        coins18 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("MCGO").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins18 = 0;
                    }
                    int coins19;
                    try {
                        coins19 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("SpeedUHC").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins19 = 0;
                    }
                    int coins20;
                    try {
                        coins20 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("MurderMystery").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins20 = 0;
                    }
                    int coins21;
                    try {
                        coins21 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("BuildBattle").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins21 = 0;
                    }
                    int coins22;
                    try {
                        coins22 = Integer
                                .parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .getAsJsonObject("blitz_coins").get("coins").toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins22 = 0;
                    }
                    int coins23;
                    try {
                        coins23 = Integer.parseInt(jsonObject.getAsJsonObject("player")
                                .getAsJsonObject("achievements").getAsJsonObject("warlords_coins").get("coins")
                                .toString().replace("\"", ""));
                    } catch (Exception e) {
                        coins23 = 0;
                    }
                    totalCoins = coins1 + coins2 + coins3 + coins4 + coins5 + coins6 + coins7 + coins8 + coins9
                            + coins10 + coins11 + coins12 + coins13 + coins14 + coins15 + coins16 + coins17
                            + coins18 + coins19 + coins20 + coins21 + coins22 + coins23;


                  //  setContentView(R.layout.activity_stats);
                        finish = true;

                } catch (Exception e) {
                  //  setContentView(R.layout.activity_stats);
                    Log.d("Error", "test : "+e.getLocalizedMessage());
                }




            } catch (Exception e) {
              //  setContentView(R.layout.activity_stats);
                Log.d("Error", "test : "+e.getLocalizedMessage());
            }



        }
    };


}
