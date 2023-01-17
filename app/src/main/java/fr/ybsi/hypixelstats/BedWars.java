package fr.ybsi.hypixelstats;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BedWars extends AppCompatActivity {

    private ImageView imgButton;
    private ImageView head;
    private TextView username;
    private TextView wins;
    private TextView deaths;
    private TextView kills;
    private TextView bed;
    private TextView play;
    private TextView money;
    private TextView level;
    private TextView losses;

    private boolean finish = false;
    private String user;

    private String username1 = "a";
    private String kill= "a";
    private String death= "a";
    private String coins= "a";
    private Bitmap imgURL;
    private String wins2= "a";
    private String parties_jouées= "a";
    private String level1= "a";
    private String beds_broken_bedwars= "a";
    private String losse= "a";
    Runnable background = new Runnable() {

        @Override
        public void run() {
            try {
                JsonObject jsonObject = MainActivity.jsonObject.deepCopy();

                Log.d("BesWardDebug", "1");

                 String url1 = "https://cravatar.eu/head/"
                        + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "")
                        + "?254";
                 imgURL = BitmapFactory.decodeStream((InputStream) new URL(url1).getContent());


                 username1 = jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", "");


                Log.d("BesWardDebug", "2");

                try {
                    coins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Bedwars").get("coins").toString().replace("\"", "");
                } catch (NullPointerException e) {
                    coins = "0";
                }


                try {
                    level1 = jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                            .get("bedwars_level").toString().replace("\"", "");
                } catch (NullPointerException e) {
                    level1 = "0";
                }
                Log.d("BesWardDebug", "3");

                try {
                    kill = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Bedwars").get("kills_bedwars").toString().replace("\"", "");
                } catch (NullPointerException e) {
                    kill = "0";
                }


                try {
                    death = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Bedwars").get("deaths_bedwars").toString().replace("\"", "");
                } catch (NullPointerException e) {
                    death = "0";
                }


                try {
                    parties_jouées = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Bedwars").get("games_played_bedwars_1").toString()
                            .replace("\"", "");
                } catch (NullPointerException e) {
                    parties_jouées = "0";
                }

                Log.d("BesWardDebug", "4");
                try {
                    beds_broken_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Bedwars").get("beds_broken_bedwars").toString().replace("\"", "");
                } catch (NullPointerException e) {
                    beds_broken_bedwars = "0";
                }



                try {
                    wins2 = jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                            .get("bedwars_wins").toString().replace("\"", "");
                } catch (NullPointerException e) {
                    wins2 = "0";
                }

                Log.d("BesWardDebug", "5");

                try {
                    losse = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Bedwars").get("losses_bedwars").toString().replace("\"", "");
                } catch (NullPointerException e) {
                    losse = "0";
                }
                Log.d("BesWardDebug", "6");

                finish = true;

            } catch (Exception e) {
                finish = true;
            }
        }
    };

    public BedWars() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_wars);

        AdView mAdView = findViewById(R.id.adView5);
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

        this.imgButton = findViewById(R.id.imageView14);
        this.username = findViewById(R.id.username);
        this.head = findViewById(R.id.imageView10);
        this.wins = findViewById(R.id.wins);
        this.losses = findViewById(R.id.losses);
        this.kills = findViewById(R.id.kills);
        this.deaths = findViewById(R.id.deaths);
        this.play = findViewById(R.id.play);
        this.bed = findViewById(R.id.bed);
        this.money = findViewById(R.id.money);
        this.level = findViewById(R.id.level);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Menu = new Intent(getApplicationContext(), menu.class);
                Menu.putExtra("username", user);
                startActivity(Menu);
                finish();
            }
        });

            username.setText(username1);
            money.setText(coins);
            level.setText(level1);
            kills.setText(kill);
            deaths.setText(death);
            play.setText(parties_jouées);
            bed.setText(beds_broken_bedwars);
            wins.setText(wins2);
            losses.setText(losse);

            try {

                head.setImageBitmap(imgURL);
            }catch(Exception e){

            }


        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Menu = new Intent(getApplicationContext(), menu.class);
                Menu.putExtra("username", user);
                startActivity(Menu);
                finish();
            }
        });

    }

}
