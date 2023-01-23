package fr.ybsi.hypixelstats;

import static fr.ybsi.hypixelstats.MainActivity.jsonObject;
import static fr.ybsi.hypixelstats.R.layout.activity_waiting;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Friends extends AppCompatActivity {

  private static String uuid;
  private static Bitmap imgURL;

  private static LinearLayout sv;

  public class UpdateTask extends AsyncTask<Context, String, String> {

    @Override
    protected void onProgressUpdate(String... values) {

      byte[] encodeByte = Base64.decode(values[1], Base64.DEFAULT);
      Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

      LinearLayout linear = new LinearLayout(getApplicationContext());

      linear.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String username = values[0];

              new Search().execute(username);
            }
          });

      TextView tf = new TextView(getApplicationContext());

      tf.setText(values[0]);
      tf.setTextColor(Color.parseColor("black"));

      ImageView imgf = new ImageView(getApplicationContext());
      imgf.setImageBitmap(imgURL);

      linear.addView(imgf);
      linear.addView(tf);

      sv.addView(linear);
    }

    @Override
    protected String doInBackground(Context... urls) {

      try {
        String inputLine;
        String key;

        key = "6bbc29a8-31c9-4a89-a416-fc0d892c09cc";

        URL url = new URL("https://api.hypixel.net/friends?key=" + key + "&uuid=" + uuid);
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
        JsonObject friendList = new JsonParser().parse(response.toString()).getAsJsonObject();

        JsonArray friends = friendList.getAsJsonArray("records");

        for (JsonElement friend : friends) {
          String uuid_friend =
              friend.getAsJsonObject().get("uuidSender").toString().replaceAll("\"", "");

          JsonObject dataFriend;
          if (!uuid.equals(uuid_friend)) {

            String iURL = "https://cravatar.eu/head/" + uuid_friend + "";
            try {
              imgURL = BitmapFactory.decodeStream((InputStream) new URL(iURL).getContent());
            } catch (IOException e) {
              throw new RuntimeException(e);
            }

            key = "6bbc29a8-31c9-4a89-a416-fc0d892c09cc";

            url =
                new URL(
                    "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid_friend);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
              response.append(inputLine);
            }
            in.close();

            Log.d("Debug Sucess", "test1");
            dataFriend = new JsonParser().parse(response.toString()).getAsJsonObject();

            Log.d("test ", dataFriend.toString());
            String username_friend =
                dataFriend.getAsJsonObject().get("name").toString().replaceAll("\"", "");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imgURL.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            String temp = Base64.encodeToString(b, Base64.DEFAULT);

            publishProgress(username_friend, temp);
          } else {
            uuid_friend =
                friend.getAsJsonObject().get("uuidReceiver").toString().replaceAll("\"", "");

            String iURL = "https://cravatar.eu/head/" + uuid_friend + "";
            try {
              imgURL = BitmapFactory.decodeStream((InputStream) new URL(iURL).getContent());
            } catch (IOException e) {
              throw new RuntimeException(e);
            }

            key = "6bbc29a8-31c9-4a89-a416-fc0d892c09cc";

            url =
                new URL(
                    "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid_friend);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
              response.append(inputLine);
            }
            in.close();

            Log.d("Debug Sucess", "test1");
            try {
              dataFriend = new JsonParser().parse(response.toString()).getAsJsonObject();

              Log.d("test ", dataFriend.toString());
              String username_friend =
                  dataFriend.getAsJsonObject().get("name").toString().replaceAll("\"", "");

              ByteArrayOutputStream baos = new ByteArrayOutputStream();
              imgURL.compress(Bitmap.CompressFormat.PNG, 100, baos);
              byte[] b = baos.toByteArray();
              String temp = Base64.encodeToString(b, Base64.DEFAULT);

              publishProgress(username_friend, temp);
            } catch (Exception e) {

            }
          }
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }
  }

  public class Search extends AsyncTask<String, String, String> {

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
        Intent Friends = new Intent(getApplicationContext(), Friends.class);
        Friends.putExtra("error", result);
        startActivity(Friends);
        finish();
      }
    }

    @Override
    protected String doInBackground(String... username) {
      String inputLine;
      String key;
      String errorCode = "";
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

      return errorCode;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_friends);
    setTitle("Friends");

    AdView mAdView = findViewById(R.id.adView5);
    AdRequest adRequest = new AdRequest.Builder().build();
    mAdView.loadAd(adRequest);

    // Obtain the FirebaseAnalytics instance.
    FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    Bundle bundle = new Bundle();
    bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity");
    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

    uuid = jsonObject.getAsJsonObject("player").get("uuid").toString().replaceAll("\"", "");
    sv = (LinearLayout) findViewById(R.id.linearLayoutFriend);

    new UpdateTask().execute();
    ImageView imgButton = findViewById(R.id.imageView14);
    imgButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            Intent Menu = new Intent(getApplicationContext(), menu.class);
            startActivity(Menu);
            finish();
          }
        });
  }
}
