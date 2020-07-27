package fr.ybsi.hypixelstats;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Skyblock extends AppCompatActivity {

    private String user;
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> id = new ArrayList<>();
    private int[] tab = {0, 50, 175, 375, 675, 1175, 1925, 2925, 4425, 6425, 9925, 14925,
            22425, 32425, 47425, 67425, 97425, 147425, 222425, 322425, 522425, 822425, 1222425,
            1722425, 2322425, 3022425, 4722425, 5722425, 6822425, 8022425, 9322425, 10722425,
            12222425, 13822425, 15522425, 17322425, 19222425, 21222425, 23322425, 25522425, 27822425,
            30222425, 32722425, 35322425, 38072425, 40975425, 44072425, 47472425, 51172425, 55172425};
    private int selectid = 1;
    Runnable bg2 = new Runnable() {

        @Override
        public void run() {

            try {
                //farming = findViewById(R.id.textView55);

                String key = "f0286aa9-4f44-48b8-8d04-90e9a45a4250";
                Log.d("ID", "" + id.get(selectid - 1));
                URL url = new URL("https://api.hypixel.net/skyblock/profile?key=" + key + "&profile=" + id.get(selectid - 1));

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoOutput(true);
                urlConnection.setChunkedStreamingMode(0);

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuffer response = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Log.d("test : ", "3");
                JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();

                jsonObject.getAsJsonObject("profile").get("profile_id").toString();

                String farm = jsonObject.getAsJsonObject("profile").getAsJsonObject("members").getAsJsonObject("d140036beb824390a01be4995da5f6a5").get("experience_skill_combat").toString();
                Log.d("Farm", farm);

                /*int i=0;
                int val = 0;
                while(farm>val){
                    i++;
                    val = tab[i];
                }*/


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
    private Button prof1;
    private Button prof2;
    private Button prof3;
    private Button prof4;
    private Button prof5;
    private String uuid;
    Runnable background = new Runnable() {

        @Override
        public void run() {

            try {
                //setContentView(R.layout.activity_waiting);

                Log.d("User", "username : " + user);

                URL url = null;
                HttpURLConnection con = null;
                String inputLine;
                String key;
                JsonObject jsonObject;

                key = "f0286aa9-4f44-48b8-8d04-90e9a45a4250";
                Log.d("test : ", "0");
                // Part 1

                url = new URL("https://api.hypixel.net/player?key=" + key + "&name=" + user);
                Log.d("test : ", "1");

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

                uuid = jsonObject.getAsJsonObject("player").get("uuid").toString();

                // Part 2
                Log.d("test : ", "2");
                url = new URL("https://api.hypixel.net/skyblock/profiles?key=" + key + "&uuid=" + uuid.replaceAll("\"", ""));


                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoOutput(true);
                urlConnection.setChunkedStreamingMode(0);

                in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Log.d("test : ", "3");
                jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();

                //Part 3
                Log.d("test : ", "4");
                int length = jsonObject.getAsJsonArray("profiles").size();
                Log.d("test : ", "5");
                for (int i = 0; i < length; i++) {
                    name.add(i, jsonObject.getAsJsonArray("profiles").get(i).getAsJsonObject().get("cute_name").toString());
                    id.add(i, jsonObject.getAsJsonArray("profiles").get(i).getAsJsonObject().get("profile_id").toString().replaceAll("\"", ""));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private TextView farming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skyblock);

        Intent intent = getIntent();
        user = intent.getStringExtra("username");

        this.prof1 = findViewById(R.id.prof1);
        this.prof2 = findViewById(R.id.prof2);
        this.prof3 = findViewById(R.id.prof3);
        this.prof4 = findViewById(R.id.prof4);
        this.prof5 = findViewById(R.id.prof5);

        Thread test = new Thread(background);
        test.start();
        while (test.isAlive()) {
            continue;
        }

        for (int i = 0; i < name.size(); i++) {
            if (i == 0) prof1.setText(name.get(0));
            if (i == 1) prof2.setText(name.get(1));
            if (i == 2) prof3.setText(name.get(2));
            if (i == 3) prof4.setText(name.get(3));
            if (i == 4) prof5.setText(name.get(4));
        }

        prof1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id.size() >= 1) {
                    selectid = 1;
                    new Thread(bg2).start();
                }

            }
        });

        prof2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.size() >= 2) {
                    selectid = 2;
                    new Thread(bg2).start();
                }
            }
        });

        prof3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.size() >= 3) {
                    selectid = 3;
                    new Thread(bg2).start();
                }
            }
        });

        prof4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.size() >= 4) {
                    selectid = 4;
                    new Thread(bg2).start();
                }
            }
        });

        prof5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.size() >= 5) {
                    selectid = 5;
                    new Thread(bg2).start();
                }
            }
        });


    }
}