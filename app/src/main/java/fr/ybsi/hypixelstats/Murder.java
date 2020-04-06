package fr.ybsi.hypixelstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.telecom.CallScreeningService;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, "ca-app-pub-6251821844352758~2907624350");

        setContentView(R.layout.activity_murder);

        this.imgbutton = (ImageView) findViewById(R.id.imageView8);
        this.head = findViewById(R.id.imageView10);
        this.username = findViewById(R.id.username);
        this.money = findViewById(R.id.money);
        this.kills = findViewById(R.id.kills);
        this.wins = findViewById(R.id.wins);
        this.play = findViewById(R.id.play);
        this.games = findViewById(R.id.games);
        this.games_solo = findViewById(R.id.games_solo);
        this.wins_solo = findViewById(R.id.wins_solo);
        this.coins_solo = findViewById(R.id.coins_solo);
        this.Kills_solo = findViewById(R.id.kills_solo);
        this.KKilss_solo = findViewById(R.id.kkills_solo);
        this.BKills_solo = findViewById(R.id.bkills_solo);
        this.Games_survivor = findViewById(R.id.games_survivor);
        this.Wins_survivor = findViewById(R.id.wins_survivor);
        this.Lalive_Survivor = findViewById( R.id.Lalive_survivor);
        this.SurvivorKills = findViewById(R.id.Skills);
        this.InfectedKills = findViewById(R.id.Ikills);
        this.Games_assassin = findViewById(R.id.Games_assassin);
        this.Wins_assassin = findViewById(R.id.Wins_assassin);
        this.Kills_assassin =findViewById(R.id.Kills_assassin);
        this.Coins_assassin = findViewById(R.id.coins_assassin);
        this.Death_assassin = findViewById(R.id.Deaths_Assassin);
        this.KKills_assassin = findViewById(R.id.KKills_assassin);

        Intent intent = getIntent();
        final String user = intent.getStringExtra("username");


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        URL url = null;
        HttpURLConnection con = null;
        String inputLine;
        String key;
        JsonObject jsonObject;

        key = "f0286aa9-4f44-48b8-8d04-90e9a45a4250";
        try {

            url = new URL("https://api.hypixel.net/player?key=" + key + "&name=" + user);


            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();


            String imgURL = "https://cravatar.eu/head/"
                    + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "")
                    + "?254";
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imgURL).getContent());
            head.setImageBitmap(bitmap);

            String username1 = jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", "");
            username.setText(username1);

            String coins = "0";
            try {
                coins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("coins").toString().replace("\"", "");
            } catch (NullPointerException response2) {
                // empty catch block
            }
            money.setText(coins);

            String games1 = "0";
            try {
                games1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("games").toString().replace("\"", "");
            } catch (NullPointerException calendar) {
                // empty catch block
            }
            games.setText(games1);

            String kills1 = "0";
            try {
                kills1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("kills").toString().replace("\"", "");
            } catch (NullPointerException e) {
                kills1 = "0";
            }
            kills.setText(kills1);

            String total_time_survived_seconds = "0";
            try {
                total_time_survived_seconds = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("total_time_survived_seconds").toString()
                        .replace("\"", "");
            } catch (NullPointerException mMonth) {
                // empty catch block
            }
            play.setText(total_time_survived_seconds);

            String Classic_games = "0";
            try {
                Classic_games = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("games_MURDER_CLASSIC").toString()
                        .replace("\"", "");
            } catch (NullPointerException mDay) {
                // empty catch block
            }
            games_solo.setText(Classic_games);

            String Classic_wins = "0";
            try {
                Classic_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("wins_MURDER_CLASSIC").toString()
                        .replace("\"", "");
            } catch (NullPointerException mHour) {
                // empty catch block
            }
            wins_solo.setText(Classic_wins);

            String Classic_kills = "0";
            try {
                Classic_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("kills_MURDER_CLASSIC").toString()
                        .replace("\"", "");
            } catch (NullPointerException mMinute) {
                // empty catch block
            }
            Kills_solo.setText(Classic_kills);

            String Classic_Knife_kills = "0";
            try {
                Classic_Knife_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("knife_kills_MURDER_CLASSIC").toString()
                        .replace("\"", "");
            } catch (NullPointerException mSecond) {
                // empty catch block
            }
            KKilss_solo.setText(Classic_Knife_kills);

            String Classic_Bow_Kills = "0";
            try {
                Classic_Bow_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("bow_kills_MURDER_CLASSIC").toString()
                        .replace("\"", "");
            } catch (NullPointerException message) {
                // empty catch block
            }
            BKills_solo.setText(Classic_Bow_Kills);

            String Classic_Coins_Picked_Up = "0";
            try {
                Classic_Coins_Picked_Up = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("coins_pickedup_MURDER_CLASSIC").toString()
                        .replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            coins_solo.setText(Classic_Coins_Picked_Up);

            String Infected_games = "0";
            try {
                Infected_games = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("games_MURDER_INFECTION").toString()
                        .replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            Games_survivor.setText(Infected_games);

            String Infected_wins = "0";
            try {
                Infected_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("survivor_wins_MURDER_INFECTION").toString()
                        .replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            Wins_survivor.setText(Infected_wins);

            String Last_One_Alive = "0";
            try {
                Last_One_Alive = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("last_one_alive_MURDER_INFECTION").toString()
                        .replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            Lalive_Survivor.setText(Last_One_Alive);

            String Infected_Survivor_kills = "0";
            try {
                Infected_Survivor_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("kills_as_survivor_MURDER_INFECTION")
                        .toString().replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            SurvivorKills.setText(Infected_Survivor_kills);

            String Infected_Infected_kills = "0";
            try {
                Infected_Infected_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("kills_as_infected_MURDER_INFECTION")
                        .toString().replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            InfectedKills.setText(Infected_Infected_kills);

            String Assassin_games = "0";
            try {
                Assassin_games = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("games_MURDER_ASSASSINS").toString()
                        .replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            Games_assassin.setText(Assassin_games);

            String Assassin_wins = "0";
            try {
                Assassin_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("wins_MURDER_ASSASSINS").toString()
                        .replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            Wins_assassin.setText(Assassin_wins);

            String Assassin_kills = "0";
            try {
                Assassin_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("kills_MURDER_ASSASSINS").toString()
                        .replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            Kills_assassin.setText(Assassin_kills);

            String Assassin_Knife_kills = "0";
            try {
                Assassin_Knife_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("knife_kills_MURDER_ASSASSINS").toString()
                        .replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            KKills_assassin.setText(Assassin_Knife_kills);

            String Assassin_Deaths = "0";
            try {
                Assassin_Deaths = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("deaths_MURDER_ASSASSINS").toString()
                        .replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            Death_assassin.setText(Assassin_Deaths);

            String Assassin_Coins_Picked_Up = "0";
            try {
                Assassin_Coins_Picked_Up = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("MurderMystery").get("coins_pickedup_MURDER_ASSASSINS").toString()
                        .replace("\"", "");
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            Coins_assassin.setText(Assassin_Coins_Picked_Up);


        }catch(Exception e){

        }


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
}
