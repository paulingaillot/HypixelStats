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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

public class Murder extends AppCompatActivity {

    private ImageView imgbutton;
    private ImageView head;
    private TextView username;
    private TextView money;
    private TextView kills;
    private TextView wins;
    private TextView play;
    private TextView games;
    private TextView games_solo;
    private TextView wins_solo;
    private TextView coins_solo;
    private TextView KKilss_solo;
    private TextView BKills_solo;
    private TextView Games_survivor;
    private TextView Wins_survivor;
    private TextView Lalive_Survivor;
    private TextView SurvivorKills;
    private TextView InfectedKills;
    private TextView Games_assassin;
    private TextView Wins_assassin;
    private TextView Kills_assassin;
    private TextView Coins_assassin;
    private TextView Death_assassin;
    private TextView KKills_assassin;
    private TextView Kills_solo;

    private boolean finish = false;
    private String user;

    private String username1;
    private String coins;
    private String games1;
    private String kills1;
    private String total_time_survived_seconds;
    private Bitmap imgURL;
    private String Classic_wins;
    private String Classic_kills;
    private String Classic_Bow_Kills;
    private String Infected_games;
    private String Infected_wins;
    private String Last_One_Alive;
    private String Classic_Knife_kills;
    private String Classic_Coins_Picked_Up;
    private String Infected_Survivor_kills;
    private String Classic_games;
    private String Assassin_Knife_kills;
    private String Assassin_Deaths;
    private String Assassin_games;
    private String Assassin_wins;
    private String Assassin_kills;
    private String Assassin_Coins_Picked_Up;
    private String Infected_Infected_kills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_murder);

        AdView mAdView = findViewById(R.id.adView7);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        user = intent.getStringExtra("username");


        Thread t = new Thread(background);
        t.start();

        while(t.isAlive()) {
            continue;
        }


            this.imgbutton = (ImageView) findViewById(R.id.imageView8);
            this.head = findViewById(R.id.imageView10);
            this.username = findViewById(R.id.username);
            this.money = findViewById(R.id.coins);
            this.kills = findViewById(R.id.kills);
            this.wins = findViewById(R.id.wins);
            //this.play = findViewById(R.id.play);
            this.games = findViewById(R.id.games);
            this.games_solo = findViewById(R.id.games_solo);
            this.wins_solo = findViewById(R.id.wins_solo);
            this.coins_solo = findViewById(R.id.coins_solo);
            this.Kills_solo = findViewById(R.id.kills_solo);
            this.KKilss_solo = findViewById(R.id.kkills_solo);
            this.BKills_solo = findViewById(R.id.bkills_solo);
            this.Games_survivor = findViewById(R.id.games_survivor);
            this.Wins_survivor = findViewById(R.id.wins_survivor);
            this.Lalive_Survivor = findViewById(R.id.Lalive_survivor);
            this.SurvivorKills = findViewById(R.id.Skills);
            this.InfectedKills = findViewById(R.id.Ikills);
            this.Games_assassin = findViewById(R.id.Games_assassin);
            this.Wins_assassin = findViewById(R.id.Wins_assassin);
            this.Kills_assassin = findViewById(R.id.Kills_assassin);
            this.Coins_assassin = findViewById(R.id.coins_assassin);
            this.Death_assassin = findViewById(R.id.Deaths_Assassin);
            this.KKills_assassin = findViewById(R.id.KKills_assassin);



            username.setText(username1);
            //money.setText(coins);
            games.setText(games1);
            kills.setText(kills1);
            //play.setText(total_time_survived_seconds);
            games_solo.setText(Classic_games);
            wins_solo.setText(Classic_wins);
            Kills_solo.setText(Classic_kills);
            KKilss_solo.setText(Classic_Knife_kills);
            BKills_solo.setText(Classic_Bow_Kills);
            coins_solo.setText(Classic_Coins_Picked_Up);
            Games_survivor.setText(Infected_games);
            Wins_survivor.setText(Infected_wins);
            Lalive_Survivor.setText(Last_One_Alive);
            SurvivorKills.setText(Infected_Survivor_kills);
            InfectedKills.setText(Infected_Infected_kills);
            Games_assassin.setText(Assassin_games);
            Wins_assassin.setText(Assassin_wins);
            Kills_assassin.setText(Assassin_kills);
            KKills_assassin.setText(Assassin_Knife_kills);
            Death_assassin.setText(Assassin_Deaths);
            Coins_assassin.setText(Assassin_Coins_Picked_Up);


            head.setImageBitmap(imgURL);

            imgbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent Menu = new Intent(getApplicationContext(), menu.class);
                    Menu.putExtra("username", user);
                    startActivity(Menu);
                    finish();
                }
            });
        }

    Runnable background = new Runnable() {
        @Override
        public void run() {
            try {
                JsonObject jsonObject = MainActivity.jsonObject;


                 String iURL = "https://cravatar.eu/head/"
                        + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "")
                        + "?254";
                imgURL = BitmapFactory.decodeStream((InputStream) new URL(iURL).getContent());

                 username1 = jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", "");



                try {
                    coins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("coins").toString().replace("\"", "");
                } catch (NullPointerException response2) {
                    // empty catch block
                }



                try {
                    games1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("games").toString().replace("\"", "");
                } catch (NullPointerException calendar) {
                    // empty catch block
                }



                try {
                    kills1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("kills").toString().replace("\"", "");
                } catch (NullPointerException e) {
                    kills1 = "0";
                }



                try {
                    total_time_survived_seconds = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("total_time_survived_seconds").toString()
                            .replace("\"", "");
                } catch (NullPointerException mMonth) {
                    // empty catch block
                }



                try {
                    Classic_games = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("games_MURDER_CLASSIC").toString()
                            .replace("\"", "");
                } catch (NullPointerException mDay) {
                    // empty catch block
                }



                try {
                    Classic_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("wins_MURDER_CLASSIC").toString()
                            .replace("\"", "");
                } catch (NullPointerException mHour) {
                    // empty catch block
                }



                try {
                    Classic_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("kills_MURDER_CLASSIC").toString()
                            .replace("\"", "");
                } catch (NullPointerException mMinute) {
                    // empty catch block
                }


                try {
                    Classic_Knife_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("knife_kills_MURDER_CLASSIC").toString()
                            .replace("\"", "");
                } catch (NullPointerException mSecond) {
                    // empty catch block
                }



                try {
                    Classic_Bow_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("bow_kills_MURDER_CLASSIC").toString()
                            .replace("\"", "");
                } catch (NullPointerException message) {
                    // empty catch block
                }



                try {
                    Classic_Coins_Picked_Up = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("coins_pickedup_MURDER_CLASSIC").toString()
                            .replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }



                try {
                    Infected_games = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("games_MURDER_INFECTION").toString()
                            .replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }



                try {
                    Infected_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("survivor_wins_MURDER_INFECTION").toString()
                            .replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }


                try {
                    Last_One_Alive = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("last_one_alive_MURDER_INFECTION").toString()
                            .replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }



                try {
                    Infected_Survivor_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("kills_as_survivor_MURDER_INFECTION")
                            .toString().replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }



                try {
                    Infected_Infected_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("kills_as_infected_MURDER_INFECTION")
                            .toString().replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }



                try {
                    Assassin_games = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("games_MURDER_ASSASSINS").toString()
                            .replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }


                try {
                    Assassin_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("wins_MURDER_ASSASSINS").toString()
                            .replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }


                try {
                    Assassin_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("kills_MURDER_ASSASSINS").toString()
                            .replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }



                try {
                    Assassin_Knife_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("knife_kills_MURDER_ASSASSINS").toString()
                            .replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }



                try {
                    Assassin_Deaths = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("deaths_MURDER_ASSASSINS").toString()
                            .replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }



                try {
                    Assassin_Coins_Picked_Up = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("MurderMystery").get("coins_pickedup_MURDER_ASSASSINS").toString()
                            .replace("\"", "");
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }


                finish = true;

            }catch(Exception e){
                finish = true;
            }

        }
    };

}
