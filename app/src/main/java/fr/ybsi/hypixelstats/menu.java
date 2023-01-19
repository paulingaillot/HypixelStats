package fr.ybsi.hypixelstats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class menu extends AppCompatActivity {

    private Button button;
    private TextView SkyWars;
    private TextView BedWars;
    private TextView TnTGames;
    private TextView Pit;
    private TextView Murder;
    private TextView General;
    private TextView SkyBlock;

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

        this.button = findViewById(R.id.button2);
        this.SkyWars = findViewById(R.id.SkyWars);
        this.BedWars = findViewById(R.id.BedWars);
        this.Murder = findViewById(R.id.Murder);
        this.TnTGames = findViewById(R.id.TnTGames);
        this.Pit = findViewById(R.id.Pit);
        this.General = findViewById(R.id.General);
        this.SkyBlock = findViewById(R.id.SkyBlock);

        SkyBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SkyBlock = new Intent(getApplicationContext(), Skyblock.class);
                SkyBlock.putExtra("username", user);
                startActivity(SkyBlock);
                finish();

            }
        });

        SkyWars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent skywars = new Intent(getApplicationContext(), SkyWars.class);
                skywars.putExtra("username", user);
                startActivity(skywars);
                finish();

            }
        });

        BedWars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bedwars = new Intent(getApplicationContext(), BedWars.class);
                bedwars.putExtra("username", user);
                startActivity(bedwars);
                finish();

            }
        });

        Murder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent murder = new Intent(getApplicationContext(), Murder.class);
                murder.putExtra("username", user);
                startActivity(murder);
                finish();

            }
        });

        TnTGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tntgames = new Intent(getApplicationContext(), TnTGames.class);
                tntgames.putExtra("username", user);
                startActivity(tntgames);
                finish();

            }
        });

        Pit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pit = new Intent(getApplicationContext(), Pit.class);
                pit.putExtra("username", user);
                startActivity(pit);
                finish();

            }
        });

        General.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Stats = new Intent(getApplicationContext(), Stats.class);
                Stats.putExtra("username", user);
                startActivity(Stats);
                finish();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText tonEdit = findViewById(R.id.editText2);

                String username = tonEdit.getText().toString();

                MainActivity.username = username;
                Thread val = new Thread(MainActivity.test);

                val.start();
                while (val.isAlive()) {
                    continue;
                }

                if (MainActivity.finish2 == true) {
                    Intent Stats = new Intent(getApplicationContext(), Stats.class);
                    Stats.putExtra("username", username);
                    startActivity(Stats);
                    finish();
                } else {
                    if (MainActivity.errorCode != "") {
                        tonEdit.setHint("" + MainActivity.errorCode);
                    } else {
                        tonEdit.setHint("Sorry but this username doesn't exist.");
                    }
                }



            }
        });

    }
}
