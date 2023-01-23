package fr.ybsi.hypixelstats;

import static fr.ybsi.hypixelstats.MainActivity.jsonObject;
import static fr.ybsi.hypixelstats.R.layout.activity_waiting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Leaderboard extends AppCompatActivity {
  private static LinearLayout sv;
  private static LinearLayout rv;

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

  public class UpdateTask extends AsyncTask<String, String, String> {

    private int i =0;

    @Override
    protected void onProgressUpdate(String... values) {
      if (i<20) {
        i++;
        byte[] encodeByte = Base64.decode(values[2], Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

        LinearLayout linear = new LinearLayout(getApplicationContext());

        linear.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                String username1 = values[1];
                new Search().execute(username1);
              }
            });

        TextView tf = new TextView(getApplicationContext());

        tf.setText(values[0] + " - " + values[1]);
        tf.setTextColor(Color.parseColor("black"));

        ImageView imgf = new ImageView(getApplicationContext());
        imgf.setImageBitmap(bitmap);

        linear.addView(imgf);
        linear.addView(tf);

        sv.addView(linear);
      }else {
        byte[] encodeByte = Base64.decode(values[2], Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

        TextView tf = new TextView(getApplicationContext());

        tf.setText(values[0] + " - " + values[1]);
        tf.setTextColor(Color.parseColor("black"));

        ImageView imgf = new ImageView(getApplicationContext());
        imgf.setImageBitmap(bitmap);

        rv.addView(imgf);
        rv.addView(tf);
      }
    }

    @Override
    protected String doInBackground(String... doc) {

      for (int i = 0; i < 20; i++) {
        String user = doc[i];

        String iURL = "https://cravatar.eu/head/" + user + "";
        Bitmap imgURL = null;
        String temp = "";
        try {
          imgURL = BitmapFactory.decodeStream((InputStream) new URL(iURL).getContent());

          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          imgURL.compress(Bitmap.CompressFormat.PNG, 100, baos);
          byte[] b = baos.toByteArray();
          temp = Base64.encodeToString(b, Base64.DEFAULT);
        } catch (Exception e) {
          e.printStackTrace();
        }
        publishProgress((i + 1) + "", user, temp);
      }

      String iURL = "https://cravatar.eu/head/" + username + "";
      Bitmap imgURL = null;
      String temp = "";
      try {
        imgURL = BitmapFactory.decodeStream((InputStream) new URL(iURL).getContent());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imgURL.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        temp = Base64.encodeToString(b, Base64.DEFAULT);
      } catch (Exception e) {
        e.printStackTrace();
      }

      publishProgress(doc[20], username, temp);

      return null;
    }
  }

  private String username;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_leaderboard);
    setTitle("Leaderboard");

    AdView mAdView = findViewById(R.id.adView5);
    AdRequest adRequest = new AdRequest.Builder().build();
    mAdView.loadAd(adRequest);

    // Obtain the FirebaseAnalytics instance.
    FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    Bundle bundle = new Bundle();
    bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity");
    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

    username =
        jsonObject.getAsJsonObject("player").get("playername").toString().replaceAll("\"", "");
    sv = (LinearLayout) findViewById(R.id.linearLayoutFriend);
    rv = (LinearLayout) findViewById(R.id.linearLayout2);

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Create a reference to the cities collection
    CollectionReference profils = db.collection("profils");

    // Create a query against the collection.
    Query query = profils.orderBy("topXp", Query.Direction.ASCENDING).limit(20);
    query
        .get()
        .addOnCompleteListener(
            task -> {
              String[] us = new String[21];
              List<DocumentSnapshot> result = task.getResult().getDocuments();
              for (int i = 0; i < 20; i++) {
                    us[i] = (String) result.get(i).get("username");
              }
              profils
                  .whereEqualTo("username", username)
                  .get()
                  .addOnCompleteListener(
                      task1 -> {
                        us[20] = "" + (Long) (task1.getResult().getDocuments().get(0).get("topXp"));
                        new UpdateTask().execute(us);
                      });
            });

    ImageView imgButton = findViewById(R.id.imageView14);
    imgButton.setOnClickListener(
            view -> {

              Intent Menu = new Intent(getApplicationContext(), menu.class);
              startActivity(Menu);
              finish();
            });
  }
}
