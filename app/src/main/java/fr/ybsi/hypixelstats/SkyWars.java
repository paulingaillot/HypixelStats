package fr.ybsi.hypixelstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sky_wars);

        this.imgbutton = (ImageView) findViewById(R.id.imageView8);
        this.head = findViewById(R.id.imageView10);
        this.username = findViewById(R.id.username);
        this.money = findViewById(R.id.money);
        this.heads = findViewById(R.id.heads);
        this.souls = findViewById(R.id.souls);
        this.play = findViewById(R.id.play);
        this.wins = findViewById(R.id.wins);
        this.kills = findViewById(R.id.kills);

        Intent intent = getIntent();
        final String user = intent.getStringExtra("username");

        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Menu = new Intent(getApplicationContext(), menu.class);
                Menu.putExtra("username", user);
                startActivity(Menu);
                finish();
            }
        });

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

            String Temps_de_jeu ;
            try {
                Temps_de_jeu = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars").get("time_played").toString().replace("\"", "");
            }catch(NullPointerException e) {
                Temps_de_jeu = "0";
            }
            play.setText(Temps_de_jeu);


            String kills1 = "0";
            try {
                kills1=   jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars") .get("kills").toString().replace("\"", "");
            }catch (NullPointerException e) {

            }
            kills.setText(kills1);

            String wins1="0";
            try {
                wins1 =   jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                        .get("wins").toString().replace("\"", "");
            }catch(NullPointerException e) {

            }
            wins.setText(wins1);

            String heads1 = "0";
            try {
                heads1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                        .get("heads").toString().replace("\"", "");
            }catch(NullPointerException e) {

            }
            heads.setText(heads1);

            String souls1 = "0";

            try {
                souls1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                        .get("souls").toString().replace("\"", "");
            }catch(NullPointerException e) {

            }
            souls.setText(souls1);

            String coins = "0" ;
            try {
                coins =  jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                        .get("coins").toString().replace("\"", "");
            }catch(NullPointerException e) {

            }
            money.setText(coins);


        }catch(Exception e){

        }

    }
}
