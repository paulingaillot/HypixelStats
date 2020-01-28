package fr.ybsi.hypixelstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TnTGames extends AppCompatActivity {

    private ImageView imgbutton;
    private ImageView head;
    private TextView username;
    private TextView money;
    private TextView wins;
    private TextView wins_tntrun;
    private TextView deaths_tntrun;
    private TextView dj_tntrun;
    private TextView spp_tntrun;
    private TextView slp_tntrun;
    private TextView wins_pvprun;
    private TextView kills_pvprun;
    private TextView deaths_pvprun;
    private TextView dj_pvprun;
    private TextView survived_pvprun;
    private TextView wins_tnttag;
    private TextView death_tnttag;
    private TextView tags_tnttags;
    private TextView wins_bow;
    private TextView kills_bow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tn_tgames);

        this.imgbutton = (ImageView) findViewById(R.id.imageView8);
        this.head = findViewById(R.id.imageView10);
        this.username= findViewById(R.id.username);
        this.money = findViewById(R.id.money);
        this.wins = findViewById(R.id.wins);
        this.wins_tntrun = findViewById(R.id.wins_tntrun);
        this.deaths_tntrun = findViewById(R.id.death_tntrun);
        this.dj_tntrun = findViewById(R.id.db_tntrun);
        this.spp_tntrun = findViewById(R.id.spp_tntrun);
        this.slp_tntrun = findViewById(R.id.slp_tntrun);
        this.wins_pvprun = findViewById(R.id.win_pvprun);
        this.kills_pvprun = findViewById(R.id.kills_pvprun);
        this.deaths_pvprun = findViewById(R.id.deaths_pvprun);
        this.dj_pvprun = findViewById(R.id.db_pvprun);
        this.survived_pvprun = findViewById(R.id.survived_pvprun);
        this.wins_tnttag = findViewById(R.id.wins_tnttag);
        this.death_tnttag = findViewById(R.id.death_tnttag);
        this.tags_tnttags = findViewById(R.id.tags_tnttags);
        this.wins_bow = findViewById(R.id.wins_bow);
        this.kills_bow = findViewById(R.id.kills_bow);


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

            String money1 = "0";
            try {
                money1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("coins").toString().replace("\"", "");
            } catch (NullPointerException level) {
                // empty catch block
            }
            money.setText(money1);

            String wins1 = "0";
            try {
                wins1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("TNTGames")
                        .get("wins").toString().replace("\"", "");
            } catch (NullPointerException kills) {
                // empty catch block
            }
            wins.setText(wins1);

            String TnTRun_wins = "0";
            try {
                TnTRun_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("wins_tntrun").toString().replace("\"", "");
            } catch (NullPointerException deaths) {
                // empty catch block
            }
            wins_tntrun.setText(TnTRun_wins);

            String TnTRun_Deaths = "0";
            try {
                TnTRun_Deaths = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("deaths_tntrun").toString().replace("\"", "");
            } catch (NullPointerException parties_jouées) {
                // empty catch block
            }
            deaths_tntrun.setText(TnTRun_Deaths);

            int TnTRunDoubleJump = 0;
            try {
                TnTRunDoubleJump = Integer.parseInt(jsonObject.getAsJsonObject("player")
                        .getAsJsonObject("stats").getAsJsonObject("TNTGames")
                        .get("new_tntrun_double_jumps_legacy").toString().replace("\"", "")) + 1;
            } catch (NullPointerException beds_broken_bedwars) {
            } catch (NumberFormatException beds_broken_bedwars) {
                // empty catch block
            }
            dj_tntrun.setText(TnTRunDoubleJump);

            String TnTRun_SpeedPotion = "0";
            try {
                TnTRun_SpeedPotion = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("new_tntrun_speed_potions").toString()
                        .replace("\"", "");
            } catch (NullPointerException wins2) {
                // empty catch block
            }
            spp_tntrun.setText(TnTRun_SpeedPotion);

            String TnTRun_SlowPotion = "0";
            try {
                TnTRun_SlowPotion = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("new_tntrun_slowness_potions").toString()
                        .replace("\"", "");
            } catch (NullPointerException losses) {
                // empty catch block
            }
            slp_tntrun.setText(TnTRun_SlowPotion);

            String PvPRun_wins = "0";
            try {
                PvPRun_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("wins_pvprun").toString().replace("\"", "");
            } catch (NullPointerException arondi) {
                // empty catch block
            }
            wins_pvprun.setText(PvPRun_wins);

            String PvPRun_kills = "0";
            try {
                PvPRun_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("kills_pvprun").toString().replace("\"", "");
            } catch (NullPointerException t1_Kills) {
                // empty catch block
            }
            kills_pvprun.setText(PvPRun_kills);

            String PvPRun_Deaths = "0";
            try {
                PvPRun_Deaths = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("deaths_pvprun").toString().replace("\"", "");
            } catch (NullPointerException t1_Death) {
                // empty catch block
            }
            deaths_pvprun.setText(PvPRun_Deaths);

            String PvPRun_Record = "0";
            try {
                PvPRun_Record = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("record_pvprun").toString().replace("\"", "");
            } catch (NullPointerException Solo_Final_Kills) {
                // empty catch block
            }
            survived_pvprun.setText(PvPRun_Record);

            String PvPRun_DoubleJump = "0";
            try {
                PvPRun_DoubleJump = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("new_pvprun_double_jumps").toString()
                        .replace("\"", "");
            } catch (NullPointerException solo_beds_broken_bedwars) {
                // empty catch block
            }
            dj_pvprun.setText(PvPRun_DoubleJump);

            String BowSpleef_Deaths = "0";
            try {
                BowSpleef_Deaths = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("deaths_bowspleef").toString().replace("\"", "");
            } catch (NullPointerException solo_wins_bedwars) {
                // empty catch block
            }
            death_tnttag.setText(BowSpleef_Deaths);



            String BowSpleef_wins = "0";
            try {
                BowSpleef_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("wins_bowspleef").toString().replace("\"", "");
            } catch (NullPointerException solo_kills_bedwars) {
                // empty catch block
            }
            wins_tnttag.setText(BowSpleef_wins);

            String BowSpleef_tag = "0";
            try {
                BowSpleef_tag = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("tags_bowspleef").toString().replace("\"", "");
            } catch (NullPointerException solo_death_bedwars) {
                // empty catch block
            }
            tags_tnttags.setText(BowSpleef_tag);

            String TnTag_Kills = "0";
            try {
                TnTag_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("kills_tntag").toString().replace("\"", "");
            } catch (NullPointerException solo_losses_bedwars) {
                // empty catch block
            }
            kills_bow.setText(TnTag_Kills);

            String TnTag_Wins = "0";
            try {
                TnTag_Wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                        .getAsJsonObject("TNTGames").get("kills_tntag").toString().replace("\"", "");
            } catch (NullPointerException t1_kd) {
                // empty catch block
            }
            wins_bow.setText(TnTag_Wins);



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
