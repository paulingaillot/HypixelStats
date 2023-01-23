package fr.ybsi.hypixelstats;

import static java.lang.Thread.sleep;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Waiting extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_waiting);
      setTitle("Waiting");

      // Obtain the FirebaseAnalytics instance.
      FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
      Bundle bundle = new Bundle();
      bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity");
      mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

    Thread thread =
        new Thread(
            new Runnable() {

              @Override
              public void run() {
                try {
                  sleep(2000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                finish();
              }
            });
    thread.start();
  }
}
