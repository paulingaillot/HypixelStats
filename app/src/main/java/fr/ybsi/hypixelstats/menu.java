package fr.ybsi.hypixelstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

public class menu extends AppCompatActivity {

    private Button button;
    private TextView SkyWars;
    private TextView BedWars;
    private TextView TnTGames;
    private TextView Pit;
    private TextView Murder;
    private TextView General;

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        AdView mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        user = intent.getStringExtra("username");

        this.button = (Button) findViewById(R.id.button2);
        this.SkyWars = (TextView) findViewById(R.id.SkyWars);
        this.BedWars = (TextView) findViewById(R.id.BedWars);
        this.Murder = (TextView) findViewById(R.id.Murder);
        this.TnTGames = (TextView) findViewById(R.id.TnTGames);
        this.Pit = (TextView) findViewById(R.id.Pit);
        this.General = (TextView) findViewById(R.id.General);

        SkyWars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent skywars = new Intent(getApplicationContext(), SkyWars.class);
                skywars.putExtra("username", user);
                setContentView(R.layout.activity_waiting);
                startActivity(skywars);
                finish();

            }
        });

        BedWars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bedwars = new Intent(getApplicationContext(), BedWars.class);
                bedwars.putExtra("username", user);
                setContentView(R.layout.activity_waiting);
                startActivity(bedwars);
                finish();

            }
        });

        Murder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent murder = new Intent(getApplicationContext(), Murder.class);
                murder.putExtra("username", user);
                setContentView(R.layout.activity_waiting);
                startActivity(murder);
                finish();

            }
        });

        TnTGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tntgames = new Intent(getApplicationContext(), TnTGames.class);
                tntgames.putExtra("username", user);
                setContentView(R.layout.activity_waiting);
                startActivity(tntgames);
                finish();

            }
        });

        Pit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pit = new Intent(getApplicationContext(), Pit.class);
                pit.putExtra("username", user);
                setContentView(R.layout.activity_waiting);
                startActivity(pit);
                finish();

            }
        });

        General.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Stats = new Intent(getApplicationContext(), Stats.class);
                Stats.putExtra("username", user);
                setContentView(R.layout.activity_waiting);
                startActivity(Stats);
                finish();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText tonEdit = (EditText)findViewById(R.id.editText2);

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
