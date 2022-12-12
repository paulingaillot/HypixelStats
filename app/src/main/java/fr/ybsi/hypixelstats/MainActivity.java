package fr.ybsi.hypixelstats;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static fr.ybsi.hypixelstats.R.layout.activity_waiting;

public class MainActivity extends AppCompatActivity {

    private TextView error;
    private boolean finish2 = false;
    private String username;
    private EditText tonEdit;
    private int i = 0;
    private String errorCode;
    private boolean val = false;

    public static JsonObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        this.error = findViewById(R.id.textView51);
        this.tonEdit = findViewById(R.id.editText);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        tonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tonEdit.setText("");
            }
        });

    button.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            username = tonEdit.getText().toString();

            Thread val = new Thread(test);

            val.start();
            while (val.isAlive()) {
              continue;
            }

            if (finish2 == true) {
              Intent Stats = new Intent(getApplicationContext(), Stats.class);
              Stats.putExtra("username", username);
              System.out.println("TESTING DEBUG  : " + username);

              startActivity(Stats);
              finish();
            } else {
              if (errorCode != "") {
                  error.setText("" + errorCode);
              } else {
                error.setText("Sorry but this username doesn't exist.");
              }
            }
          }
        });
    }

  Runnable test =
      new Runnable() {
        @Override
        public void run() {
          try {

            String inputLine;
            String key;

            key = "6bbc29a8-31c9-4a89-a416-fc0d892c09cc";

            URL url = new URL("https://api.hypixel.net/player?key=" + key + "&name=" + username);
              Log.d("Debug Sucess", "test0");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            BufferedReader in =
                new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
              response.append(inputLine);
            }
            in.close();

            Log.d("Debug Sucess", "test1");
            jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();

            String success = jsonObject.get("success").getAsString();
            Log.d("Debug Sucess", success);


            if (success.equals("false")) {
                errorCode = jsonObject.get("cause").getAsString();
                Log.d("Debug player", errorCode);
                finish2 = false;

            } else {

            String player = null;
            try {
              player = jsonObject.getAsJsonObject("player").get("uuid").toString();
              Log.d("Player DEBUG", player.toString());
            } catch (Exception e) {
            }

            if (player == null) {
              finish2 = false; // Le joueur n'existe pas
              errorCode = "This player doesn't exist";
            } else { // Le joueur est trouvé
              finish2 = true;
            }
            }

          } catch (IOException e) {
              errorCode = "Retry in few minutes";
              e.printStackTrace();
              Log.d("Error DEbug", e.getMessage());
            finish2 = false;
          }
        }
      };
}
