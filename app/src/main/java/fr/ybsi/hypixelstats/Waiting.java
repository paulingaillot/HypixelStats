package fr.ybsi.hypixelstats;

import static java.lang.Thread.sleep;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Waiting extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_waiting);

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
