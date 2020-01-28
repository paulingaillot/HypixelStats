package fr.ybsi.hypixelstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;

public class Pit extends AppCompatActivity {

    private ImageView imgbutton;
    private TextView username;
    private TextView lastconnexion;
    private TextView xp;
    private TextView money;
    private TextView renown;
    private TextView prestige;
    private TextView renown_upgrade;
    private ImageView head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit);

        this.imgbutton = (ImageView) findViewById(R.id.imageView8);
        this.username = (TextView) findViewById(R.id.username);
        this.lastconnexion = findViewById(R.id.lastconnexion);
        this.xp = findViewById(R.id.xp);
        this.money = findViewById(R.id.money);
        this.renown = findViewById(R.id.renown);
        this.renown_upgrade = findViewById(R.id.renown_upgrade);
        this.prestige = findViewById(R.id.prestige);
        this.head = findViewById(R.id.imageView10);
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

                url = new URL("https://api.hypixel.net/player?key=" + key + "&name="+user);



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

                String prestige1;
                try {
                    prestige1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Pit").getAsJsonObject("profile").getAsJsonArray("prestiges")
                            .get(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Pit").getAsJsonObject("profile")
                                    .getAsJsonArray("prestiges").size() - 1)
                            .getAsJsonObject().get("index").toString().replace("\"", "");
                } catch (Exception e) {
                    prestige1 = "0";
                }
                prestige.setText(prestige1);

                String xp1;
                try {
                    xp1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Pit").getAsJsonObject("profile").get("xp").toString()
                            .replace("\"", "");
                } catch (Exception e) {
                    xp1 = "0";
                }
                xp.setText(xp1);

                String renown1;
                try {
                    renown1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Pit").getAsJsonObject("profile").get("renown").toString()
                            .replace("\"", "");
                } catch (Exception e) {
                    renown1 = "0";
                }
                renown.setText(renown1);

                String money1;
                try {
                    money1 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Pit").getAsJsonObject("profile").get("cash").toString()
                            .replace("\"", "");
                } catch (Exception e) {
                    money1 = "0";
                }
                money.setText(money1);

                HashMap<String, HashMap<String, String>> renown_unlocks = new HashMap();
                try {
                    for (int i = 0; i < jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                            .getAsJsonObject("Pit").getAsJsonObject("profile")
                            .getAsJsonArray("renown_unlocks").size(); ++i) {
                        HashMap<String, String> test = new HashMap<String, String>();
                        test.put("key",
                                jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                        .getAsJsonObject("Pit").getAsJsonObject("profile")
                                        .getAsJsonArray("renown_unlocks").get(i).getAsJsonObject()
                                        .get("key").toString());
                        test.put("tier",
                                jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                        .getAsJsonObject("Pit").getAsJsonObject("profile")
                                        .getAsJsonArray("renown_unlocks").get(i).getAsJsonObject()
                                        .get("tier").toString());
                        String key2 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("Pit").getAsJsonObject("profile")
                                .getAsJsonArray("renown_unlocks").get(i).getAsJsonObject().get("key")
                                .toString();
                        renown_unlocks.put(key2, test);
                    }
                } catch (NullPointerException i) {
                    // empty catch block
                }
                String mess = "";
                try {
                    for (HashMap i : renown_unlocks.values()) {
                        mess = String.valueOf(mess) + ((String) i.get("key")).replaceAll("\"", "")
                                + " : **Tier** " + (Integer.parseInt((String) i.get("tier")) + 1) + " \n";
                    }
                } catch (NullPointerException e) {
                        mess = "vous n'avez pas encore débloqué de d'amelioration grace aux renown.";
                }
                renown_upgrade.setText(mess);

                long lastconnexion1;
                try {
                    lastconnexion1 = Long.parseLong(
                            jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("Pit")
                                    .getAsJsonObject("profile").get("last_save").toString().replace("\"", ""));
                } catch (Exception e) {
                    lastconnexion1 = 1L;
                }
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTimeInMillis(lastconnexion1);
                int mYear1 = calendar1.get(Calendar.YEAR);
                int mMonth1 = calendar1.get(Calendar.MONTH) + 1;
                int mDay1 = calendar1.get(Calendar.DAY_OF_MONTH);
                int mHour1 = calendar1.get(Calendar.HOUR_OF_DAY);
                int mMinute1 = calendar1.get(Calendar.MINUTE);
                int mSecond1 = calendar1.get(Calendar.SECOND);

                lastconnexion.setText(mDay1+"/"+mMonth1+"/"+mYear1+" "+mHour1+":"+mMinute1+":"+mSecond1);

        }catch (Exception e){

        }

    }
}
