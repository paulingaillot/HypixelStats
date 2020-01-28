package fr.ybsi.hypixelstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button = (Button) findViewById(R.id.button);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);adView.setAdUnitId("ca-app-pub-6251821844352758/2468640651");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText tonEdit = (EditText)findViewById(R.id.editText);

                String username = tonEdit.getText().toString();

                Intent Stats = new Intent(getApplicationContext(), Stats.class);
                Stats.putExtra("username", username);
                setContentView(R.layout.activity_waiting);
                startActivity(Stats);
                finish();

            }
        });

    }
}
