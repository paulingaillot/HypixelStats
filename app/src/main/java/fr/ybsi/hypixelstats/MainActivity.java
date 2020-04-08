package fr.ybsi.hypixelstats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView error;
    private boolean finish = false;
    private boolean finish2 = false;
    private String username;
    Runnable test = new Runnable() {
        @Override
        public void run() {

            try {
                URL url = null;
                HttpURLConnection con = null;
                String inputLine;
                String key;
                JsonObject jsonObject;

                key = "f0286aa9-4f44-48b8-8d04-90e9a45a4250";

                url = new URL("https://api.hypixel.net/player?key=" + key + "&name=" + username);


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


                String name = jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "");
                finish = true;
                finish2 = true;
            } catch (Exception e) {
                finish = true;
                finish2 = false;
            }
        }
    };
    private EditText tonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button = (Button) findViewById(R.id.button);
        this.error = findViewById(R.id.textView51);
        this.tonEdit = (EditText) findViewById(R.id.editText);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView  mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        tonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tonEdit.setText("");
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                error = findViewById(R.id.textView51);
                error.setText("Chargement en cours....");
                username = tonEdit.getText().toString();

                Intent Stats = new Intent(getApplicationContext(), Stats.class);
                Stats.putExtra("username", username);

                setContentView(R.layout.activity_waiting);
                new Thread(test).start();

                while (!finish) {
                    continue;
                }
                if (finish2 == true) {
                    setContentView(R.layout.activity_waiting);
                    startActivity(Stats);
                    finish();
                } else {
                    setContentView(R.layout.activity_main);
                    error = findViewById(R.id.textView51);
                    error.setText("Sorry but this username doesn't exist.");
                }


            }
        });


    }
}
