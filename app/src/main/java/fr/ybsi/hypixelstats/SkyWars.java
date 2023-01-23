package fr.ybsi.hypixelstats;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

public class SkyWars extends AppCompatActivity {

    private ImageView imgbutton;
    private ImageView head;
    private TextView username;
    private TextView money;
    private TextView heads;
    private TextView souls;
    private TextView play;
    private TextView kills;
    private TextView wins;
    private static String user;

    private static boolean finish = false;
    private static String username1;
    private static Bitmap imgURL;
    private static String Temps_de_jeu;
    private static String kills1;
    private static String wins1;
    private static String heads1;
    private static String souls1;
    private static String coins;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sky_wars);
        setTitle("Skywars");

        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

        AdView mAdView = findViewById(R.id.adView6);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        user = intent.getStringExtra("username");
        finish = false;
        Thread t = new Thread(background);
        t.start();


        while(t.isAlive()) {
            continue;
        }

        this.imgbutton = (ImageView) findViewById(R.id.imageView8);
        this.head = findViewById(R.id.imageView10);
        this.username = findViewById(R.id.username);
        this.money = findViewById(R.id.money);
        this.heads = findViewById(R.id.heads);
        this.souls = findViewById(R.id.souls);
        this.play = findViewById(R.id.play);
        this.wins = findViewById(R.id.wins);
        this.kills = findViewById(R.id.kills);


        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Menu = new Intent(getApplicationContext(), menu.class);
                Menu.putExtra("username", user);
                startActivity(Menu);
                finish();
            }
        });



            username.setText(username1+"");
            money.setText(coins);
            heads.setText(heads1);
            souls.setText(souls1);
            play.setText(Temps_de_jeu);
            wins.setText(wins1);
            kills.setText(kills1);


                head.setImageBitmap(imgURL);





    }

    Runnable background = new Runnable() {

        @Override
        public void run() {

                JsonObject jsonObject = MainActivity.jsonObject;

            try {
                String iURL = "https://cravatar.eu/head/"
                        + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "")
                        + "?254";
                imgURL = BitmapFactory.decodeStream((InputStream) new URL(iURL).getContent());

                username1 = jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", "");

                Temps_de_jeu = "0";
                try {
                    Temps_de_jeu = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars").get("time_played").toString().replace("\"", "");
                } catch (NullPointerException e) {
                    Temps_de_jeu = "0";
                }


                kills1 = "0";
                try {
                    kills1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars").get("kills").toString().replace("\"", "");
                } catch (NullPointerException e) {

                }

                wins1 = "0";
                try {
                    wins1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                            .get("wins").toString().replace("\"", "");
                } catch (NullPointerException e) {

                }

                heads1 = "0";
                try {
                    heads1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                            .get("heads").toString().replace("\"", "");
                } catch (NullPointerException e) {

                }

                souls1 = "0";

                try {
                    souls1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                            .get("souls").toString().replace("\"", "");
                } catch (NullPointerException e) {

                }

                coins = "0";
                try {
                    coins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                            .get("coins").toString().replace("\"", "");
                } catch (NullPointerException e) {

                }

                finish = true;
            }catch (Exception e) {

            }

        }
    };

}


