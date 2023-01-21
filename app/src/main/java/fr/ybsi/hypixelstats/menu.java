package fr.ybsi.hypixelstats;

import static fr.ybsi.hypixelstats.R.layout.activity_waiting;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class menu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_menu);

    AdView mAdView = findViewById(R.id.adView3);
    AdRequest adRequest = new AdRequest.Builder().build();
    mAdView.loadAd(adRequest);

    EditText tonEdit = findViewById(R.id.editText2);

    Intent intent = getIntent();
    String error1 = intent.getStringExtra("error");
    if(error1 != null) tonEdit.setHint(error1);

    Button button = findViewById(R.id.button2);
    TextView skyWars = findViewById(R.id.SkyWars);
    TextView bedWars = findViewById(R.id.BedWars);
    TextView murder = findViewById(R.id.Murder);
    TextView tnTGames = findViewById(R.id.TnTGames);
    TextView pit = findViewById(R.id.Pit);
    TextView general = findViewById(R.id.General);
    TextView skyBlock = findViewById(R.id.SkyBlock);
    ImageView friends = findViewById(R.id.imageView15);

    skyBlock.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent SkyBlock = new Intent(getApplicationContext(), Skyblock.class);
            startActivity(SkyBlock);
            finish();
          }
        });

    skyWars.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent skywars = new Intent(getApplicationContext(), SkyWars.class);
            startActivity(skywars);
            finish();
          }
        });

    bedWars.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent bedwars = new Intent(getApplicationContext(), BedWars.class);
            startActivity(bedwars);
            finish();
          }
        });

    murder.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent murder = new Intent(getApplicationContext(), Murder.class);
            startActivity(murder);
            finish();
          }
        });

    tnTGames.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent tntgames = new Intent(getApplicationContext(), TnTGames.class);
            startActivity(tntgames);
            finish();
          }
        });

    pit.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent pit = new Intent(getApplicationContext(), Pit.class);
            startActivity(pit);
            finish();
          }
        });

    general.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent Stats = new Intent(getApplicationContext(), Stats.class);
            startActivity(Stats);
            finish();
          }
        });

    button.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String username = tonEdit.getText().toString();
            new menu.UpdateTask().execute(username);
          }
        });

    friends.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent Friends = new Intent(getApplicationContext(), Friends.class);
            startActivity(Friends);
            finish();
          }
        });
  }

  public class UpdateTask extends AsyncTask<String, String, String> {

    @Override
    protected void onPreExecute() {
      setContentView(activity_waiting);
    }

    @Override
    protected void onPostExecute(String result) {
      if (result.equals("")) {
        Intent Stats = new Intent(getApplicationContext(), Stats.class);
        startActivity(Stats);
      } else {
        Intent menu = new Intent(getApplicationContext(), menu.class);
        menu.putExtra("error", result);
        startActivity(menu);
        finish();
      }
    }

    @Override
    protected String doInBackground(String... username) {
      String inputLine;
      String key;
      String errorCode="";
      boolean finish2;
      try {

        key = "6bbc29a8-31c9-4a89-a416-fc0d892c09cc";

        URL url = new URL("https://api.hypixel.net/player?key=" + key + "&name=" + username[0]);
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
        JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
        MainActivity.jsonObject = jsonObject;

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
          } catch (Exception ignored) {
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

      return errorCode;
    }
  }
}
