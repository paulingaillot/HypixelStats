package fr.ybsi.hypixelstats;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

public class Skyblock extends AppCompatActivity {

    private String user;
    private JsonObject SkyblockData;
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> id = new ArrayList<>();
    private int[] tab = {0, 50, 175, 375, 675, 1175, 1925, 2925, 4425, 6425, 9925, 14925,
            22425, 32425, 47425, 67425, 97425, 147425, 222425, 322425, 522425, 822425, 1222425,
            1722425, 2322425, 3022425, 4722425, 5722425, 6822425, 8022425, 9322425, 10722425,
            12222425, 13822425, 15522425, 17322425, 19222425, 21222425, 23322425, 25522425, 27822425,
            30222425, 32722425, 35322425, 38072425, 40975425, 44072425, 47472425, 51172425, 55172425};
    private int selectid = 1;

    Runnable background = new Runnable() {

        @Override
        public void run() {

            try {
                String inputLine;
                String key = "6bbc29a8-31c9-4a89-a416-fc0d892c09cc";

                JsonObject jsonObject = MainActivity.jsonObject;

                uuid = jsonObject.getAsJsonObject("player").get("uuid").toString().replaceAll("\"", "");

                // Part 2
                Log.d("test : ", "2");
                URL url = new URL("https://api.hypixel.net/skyblock/profiles?key=" + key + "&uuid=" + uuid);


                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoOutput(true);
                urlConnection.setChunkedStreamingMode(0);

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Log.d("test : ", "3");
                SkyblockData = new JsonParser().parse(response.toString()).getAsJsonObject();

                //Part 3
                Log.d("test : ", "4");
                int length = SkyblockData.getAsJsonArray("profiles").size();
                Log.d("test : ", "5");
                for (int i = 0; i < length; i++) {
                    name.add(i, SkyblockData.getAsJsonArray("profiles").get(i).getAsJsonObject().get("cute_name").toString().replaceAll("\"", ""));
                    id.add(i, SkyblockData.getAsJsonArray("profiles").get(i).getAsJsonObject().get("profile_id").toString().replaceAll("\"", ""));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private int farmlevel = 0;
    private int mininglevel = 0;
    private int combatlevel = 0;
    private int foraginglevel = 0;
    private int runecraftinglevel = 0;
    private int fishinglevel = 0;
    private int enchantinglevel = 0;
    private int alchemylevel = 0;
    private int taminglevel = 0;
    private int carpentrylevel = 0;
    private int farmper;
    private int minper;
    private int combper;
    private int forper;
    private int runeper;
    private int fishper;
    private int enchantper;
    private int alcper;
    private int tamper;
    private int carper;
    private int bankcoins;
    private int purseCoins;
    private String critdemage = "0";
    private String lastCo = "never";
    private Button prof1;
    private Button prof2;
    Runnable bg2 = new Runnable() {

        @Override
        public void run() {

            try {
                JsonObject jsonObject = SkyblockData;


                double farm = 0;
                try {
                    farm = Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("experience_skill_farming").toString());
                } catch (Exception e) {

                }
                double mining = 0;
                try {
                    mining = Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("experience_skill_mining").toString());
                } catch (Exception e) {

                }
                double combat = 0;
                try {
                    combat = Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("experience_skill_combat").toString());
                } catch (Exception e) {

                }
                double foraging = 0;
                try {
                    foraging = Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("experience_skill_foraging").toString());
                } catch (Exception e) {

                }

                double runecraft = 0;
                try {
                    runecraft = Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("experience_skill_runecrafting").toString());
                } catch (Exception e) {

                }
                double fish = 0;
                try {
                    fish = Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("experience_skill_fishing").toString());
                } catch (Exception e) {

                }
                double enchant = 0;
                try {
                    enchant = Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("experience_skill_enchanting").toString());
                } catch (Exception e) {

                }
                double alchemy = 0;
                try {
                    alchemy = Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("experience_skill_alchemy").toString());
                } catch (Exception e) {

                }
                double taming = 0;
                try {
                    taming = Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("experience_skill_taming").toString());
                } catch (Exception e) {

                }
                double carpentry = 0;
                try {
                    carpentry = Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("experience_skill_carpentry").toString());
                } catch (Exception e) {

                }

                critdemage = "0";
                try {
                    critdemage = jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).getAsJsonObject("stats").get("highest_crit_damage").toString();
                } catch (Exception e) {

                }
                if (critdemage == null) critdemage = "0";

                purseCoins = 0;
                try {
                    purseCoins = (int) (Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("coin_purse").toString()));
                } catch (Exception e) {

                }
                bankcoins = 0;
                try {
                    bankcoins = (int) (Double.parseDouble(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("banking").get("balance").toString()));
                } catch (Exception e) {

                }

                Long lc = Calendar.getInstance().getTimeInMillis();
                try {
                    lc = Long.parseLong(jsonObject.getAsJsonArray("profiles").get(selectid-1).getAsJsonObject().getAsJsonObject("members").getAsJsonObject(uuid).get("last_save").toString());
                } catch (Exception e) {

                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(lc);
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH) + 1;
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                int mHour = calendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = calendar.get(Calendar.MINUTE);
                int mSecond = calendar.get(Calendar.SECOND);

                lastCo = mHour + ":" + mMinute + ":" + mSecond + " " + mDay + "/" + mMonth + "/" + mYear;

                farmlevel = 0;
                int val = 0;
                while (farm > val) {
                    farmlevel++;
                    val = tab[farmlevel];
                }
                if (farmlevel > 0) farmlevel--;

                farmper = (((int) (farm) - tab[farmlevel]) * 100) / (tab[farmlevel + 1] - tab[farmlevel]);

                mininglevel = 0;
                val = 0;
                while (mining > val) {
                    mininglevel++;
                    val = tab[mininglevel];
                }
                if (mininglevel > 0) mininglevel--;

                minper = (((int) (mining) - tab[mininglevel]) * 100) / (tab[mininglevel + 1] - tab[mininglevel]);

                combatlevel = 0;
                val = 0;
                while (combat > val) {
                    combatlevel++;
                    val = tab[combatlevel];
                }
                if (combatlevel > 0) combatlevel--;

                combper = (((int) (combat) - tab[combatlevel]) * 100) / (tab[combatlevel + 1] - tab[combatlevel]);

                foraginglevel = 0;
                val = 0;
                while (foraging > val) {
                    foraginglevel++;
                    val = tab[foraginglevel];
                }
                if (foraginglevel > 0) foraginglevel--;

                forper = (((int) (foraging) - tab[foraginglevel]) * 100) / (tab[foraginglevel + 1] - tab[foraginglevel]);

                runecraftinglevel = 0;
                val = 0;
                while (runecraft > val) {
                    runecraftinglevel++;
                    val = tab[runecraftinglevel];
                }
                if (runecraftinglevel > 0) runecraftinglevel--;

                runeper = (((int) (runecraft) - tab[runecraftinglevel]) * 100) / (tab[runecraftinglevel + 1] - tab[runecraftinglevel]);

                fishinglevel = 0;
                val = 0;
                while (fish > val) {
                    fishinglevel++;
                    val = tab[fishinglevel];
                }
                if (fishinglevel > 0) fishinglevel--;

                fishper = (((int) (fish) - tab[fishinglevel]) * 100) / (tab[fishinglevel + 1] - tab[fishinglevel]);

                enchantinglevel = 0;
                val = 0;
                while (enchant > val) {
                    enchantinglevel++;
                    val = tab[enchantinglevel];
                }
                if (enchantinglevel > 0) enchantinglevel--;

                enchantper = (((int) (enchant) - tab[enchantinglevel]) * 100) / (tab[enchantinglevel + 1] - tab[enchantinglevel]);

                alchemylevel = 0;
                val = 0;
                while (alchemy > val) {
                    alchemylevel++;
                    val = tab[alchemylevel];
                }
                if (alchemylevel > 0) alchemylevel--;

                alcper = (((int) (alchemy) - tab[alchemylevel]) * 100) / (tab[alchemylevel + 1] - tab[alchemylevel]);

                taminglevel = 0;
                val = 0;
                while (taming > val) {
                    taminglevel++;
                    val = tab[taminglevel];
                }
                if (taminglevel > 0) taminglevel--;

                tamper = (((int) (taming) - tab[taminglevel]) * 100) / (tab[taminglevel + 1] - tab[taminglevel]);

                carpentrylevel = 0;
                val = 0;
                while (carpentry > val) {
                    carpentrylevel++;
                    val = tab[carpentrylevel];
                }
                if (carpentrylevel > 0) carpentrylevel--;

                carper = (((int) (carpentry) - tab[carpentrylevel]) * 100) / (tab[carpentrylevel + 1] - tab[carpentrylevel]);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
    private Button prof3;
    private Button prof4;
    private Button prof5;
    private String uuid;
    private ImageView imgButton;
    private TextView farming;
    private TextView mining;
    private TextView combat;
    private TextView foraging;
    private TextView runecrafting;
    private TextView fishing;
    private TextView enchanting;
    private TextView alchemy;
    private TextView taming;
    private TextView carpentry;
    private TextView event;

    private TextView bank;
    private TextView purse;
    private TextView connexion;
    private TextView ct;

    private ProgressBar farmbar;
    private ProgressBar miningbar;
    private ProgressBar combatbar;
    private ProgressBar foragingbar;
    private ProgressBar runebar;
    private ProgressBar fishbar;
    private ProgressBar enchantbar;
    private ProgressBar alchemybar;
    private ProgressBar tamingbar;
    private ProgressBar carpentbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skyblock);
        Log.d("SkyWarsDebug", "1");
        AdView mAdView1 = findViewById(R.id.adView10);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);

        Log.d("SkyWarsDebug", "2");

        Intent intent = getIntent();
        user = intent.getStringExtra("username");
        Log.d("SkyWarsDebug", "3");
        this.farming = findViewById(R.id.textView55);
        this.mining = findViewById(R.id.textView76);
        this.combat = findViewById(R.id.textView95);
        this.foraging = findViewById(R.id.textView99);
        this.runecrafting = findViewById(R.id.textView101);
        this.fishing = findViewById(R.id.textView96);
        this.enchanting = findViewById(R.id.textView97);
        this.alchemy = findViewById(R.id.textView98);
        this.carpentry = findViewById(R.id.textView102);
        this.taming = findViewById(R.id.textView100);
        Log.d("SkyWarsDebug", "4");
        this.farmbar = findViewById(R.id.progressBar);
        this.miningbar = findViewById(R.id.progressBar2);
        this.combatbar = findViewById(R.id.progressBar3);
        this.foragingbar = findViewById(R.id.progressBar4);
        this.runebar = findViewById(R.id.progressBar9);
        this.fishbar = findViewById(R.id.progressBar5);
        this.enchantbar = findViewById(R.id.progressBar6);
        this.alchemybar = findViewById(R.id.progressBar7);
        this.tamingbar = findViewById(R.id.progressBar8);
        this.carpentbar = findViewById(R.id.progressBar10);
        Log.d("SkyWarsDebug", "2");
        this.prof1 = findViewById(R.id.prof1);
        this.prof2 = findViewById(R.id.prof2);
        this.prof3 = findViewById(R.id.prof3);
        this.prof4 = findViewById(R.id.prof4);
        this.prof5 = findViewById(R.id.prof5);
        this.imgButton = findViewById(R.id.imageView33);

        this.purse = findViewById(R.id.purse);
        this.bank = findViewById(R.id.bank);
        this.ct = findViewById(R.id.ct);
        this.connexion = findViewById(R.id.connection);
        this.event = findViewById(R.id.textView107);

        Thread test = new Thread(background);
        test.start();
        while (test.isAlive()) {
            continue;
        }

        for (int i = 0; i < name.size(); i++) {
            if (i == 0) prof1.setText(name.get(0));
            if (i == 1) prof2.setText(name.get(1));
            if (i == 2) prof3.setText(name.get(2));
            if (i == 3) prof4.setText(name.get(3));
            if (i == 4) prof5.setText(name.get(4));
        }

        //Default

        Thread bg;
        if (id.size() >= 1) {
            selectid = 1;
            bg = new Thread(bg2);
            bg.start();

            while (bg.isAlive()) {
                continue;
            }

            farming.setText("Farming " + farmlevel);
            mining.setText("Mining " + mininglevel);
            combat.setText("Combat " + combatlevel);
            foraging.setText("Foraging " + foraginglevel);
            runecrafting.setText("Runecrafting " + runecraftinglevel);
            fishing.setText("Fishing " + fishinglevel);
            enchanting.setText("Enchanting " + enchantinglevel);
            alchemy.setText("Alchemy " + alchemylevel);
            taming.setText("Taming " + taminglevel);
            carpentry.setText("Carpentry " + carpentrylevel);

            farmbar.setProgress(farmper);
            miningbar.setProgress(minper);
            combatbar.setProgress(combper);
            foragingbar.setProgress(forper);
            runebar.setProgress(runeper);
            fishbar.setProgress(fishper);
            enchantbar.setProgress(enchantper);
            alchemybar.setProgress(alcper);
            tamingbar.setProgress(tamper);
            carpentbar.setProgress(carper);

            bank.setText(bankcoins + "");
            purse.setText(purseCoins + "");
            connexion.setText(lastCo + "");
            ct.setText(critdemage + "");
        }

        //others

        prof1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread bg;
                if (id.size() >= 1) {
                    selectid = 1;
                    bg = new Thread(bg2);
                    bg.start();

                    while (bg.isAlive()) {
                        continue;
                    }

                    farming.setText("Farming " + farmlevel);
                    mining.setText("Mining " + mininglevel);
                    combat.setText("Combat " + combatlevel);
                    foraging.setText("Foraging " + foraginglevel);
                    runecrafting.setText("Runecrafting " + runecraftinglevel);
                    fishing.setText("Fishing " + fishinglevel);
                    enchanting.setText("Enchanting " + enchantinglevel);
                    alchemy.setText("Alchemy " + alchemylevel);
                    taming.setText("Taming " + taminglevel);
                    carpentry.setText("Carpentry " + carpentrylevel);

                    farmbar.setProgress(farmper);
                    miningbar.setProgress(minper);
                    combatbar.setProgress(combper);
                    foragingbar.setProgress(forper);
                    runebar.setProgress(runeper);
                    fishbar.setProgress(fishper);
                    enchantbar.setProgress(enchantper);
                    alchemybar.setProgress(alcper);
                    tamingbar.setProgress(tamper);
                    carpentbar.setProgress(carper);

                    bank.setText(bankcoins + "");
                    purse.setText(purseCoins + "");
                    connexion.setText(lastCo + "");
                    ct.setText(critdemage + "");
                }

            }
        });

        prof2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread bg;
                if (id.size() >= 2) {
                    selectid = 2;
                    bg = new Thread(bg2);
                    bg.start();

                    while (bg.isAlive()) {
                        continue;
                    }
                    farming.setText("Farming " + farmlevel);
                    mining.setText("Mining " + mininglevel);
                    combat.setText("Combat " + combatlevel);
                    foraging.setText("Foraging " + foraginglevel);
                    runecrafting.setText("Runecrafting " + runecraftinglevel);
                    fishing.setText("Fishing " + fishinglevel);
                    enchanting.setText("Enchanting " + enchantinglevel);
                    alchemy.setText("Alchemy " + alchemylevel);
                    taming.setText("Taming " + taminglevel);
                    carpentry.setText("Carpentry " + carpentrylevel);

                    farmbar.setProgress(farmper);
                    miningbar.setProgress(minper);
                    combatbar.setProgress(combper);
                    foragingbar.setProgress(forper);
                    runebar.setProgress(runeper);
                    fishbar.setProgress(fishper);
                    enchantbar.setProgress(enchantper);
                    alchemybar.setProgress(alcper);
                    tamingbar.setProgress(tamper);
                    carpentbar.setProgress(carper);

                    bank.setText(bankcoins + "");
                    purse.setText(purseCoins + "");
                    connexion.setText(lastCo + "");
                    ct.setText(critdemage + "");

                }
            }
        });

        prof3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread bg;
                if (id.size() >= 3) {
                    selectid = 3;
                    bg = new Thread(bg2);
                    bg.start();

                    while (bg.isAlive()) {
                        continue;
                    }
                    farming.setText("Farming " + farmlevel);
                    mining.setText("Mining " + mininglevel);
                    combat.setText("Combat " + combatlevel);
                    foraging.setText("Foraging " + foraginglevel);
                    runecrafting.setText("Runecrafting " + runecraftinglevel);
                    fishing.setText("Fishing " + fishinglevel);
                    enchanting.setText("Enchanting " + enchantinglevel);
                    alchemy.setText("Alchemy " + alchemylevel);
                    taming.setText("Taming " + taminglevel);
                    carpentry.setText("Carpentry " + carpentrylevel);

                    farmbar.setProgress(farmper);
                    miningbar.setProgress(minper);
                    combatbar.setProgress(combper);
                    foragingbar.setProgress(forper);
                    runebar.setProgress(runeper);
                    fishbar.setProgress(fishper);
                    enchantbar.setProgress(enchantper);
                    alchemybar.setProgress(alcper);
                    tamingbar.setProgress(tamper);
                    carpentbar.setProgress(carper);

                    bank.setText(bankcoins + "");
                    purse.setText(purseCoins + "");
                    connexion.setText(lastCo + "");
                    ct.setText(critdemage + "");

                }
            }
        });

        prof4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread bg;
                if (id.size() >= 4) {
                    selectid = 4;
                    bg = new Thread(bg2);
                    bg.start();

                    while (bg.isAlive()) {
                        continue;
                    }
                    farming.setText("Farming " + farmlevel);
                    mining.setText("Mining " + mininglevel);
                    combat.setText("Combat " + combatlevel);
                    foraging.setText("Foraging " + foraginglevel);
                    runecrafting.setText("Runecrafting " + runecraftinglevel);
                    fishing.setText("Fishing " + fishinglevel);
                    enchanting.setText("Enchanting " + enchantinglevel);
                    alchemy.setText("Alchemy " + alchemylevel);
                    taming.setText("Taming " + taminglevel);
                    carpentry.setText("Carpentry " + carpentrylevel);

                    farmbar.setProgress(farmper);
                    miningbar.setProgress(minper);
                    combatbar.setProgress(combper);
                    foragingbar.setProgress(forper);
                    runebar.setProgress(runeper);
                    fishbar.setProgress(fishper);
                    enchantbar.setProgress(enchantper);
                    alchemybar.setProgress(alcper);
                    tamingbar.setProgress(tamper);
                    carpentbar.setProgress(carper);

                    bank.setText(bankcoins + "");
                    purse.setText(purseCoins + "");
                    connexion.setText(lastCo + "");
                    ct.setText(critdemage + "");

                }
            }
        });

        prof5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread bg;
                if (id.size() >= 5) {
                    selectid = 5;
                    bg = new Thread(bg2);
                    bg.start();

                    while (bg.isAlive()) {
                        continue;
                    }
                    farming.setText("Farming " + farmlevel);
                    mining.setText("Mining " + mininglevel);
                    combat.setText("Combat " + combatlevel);
                    foraging.setText("Foraging " + foraginglevel);
                    runecrafting.setText("Runecrafting " + runecraftinglevel);
                    fishing.setText("Fishing " + fishinglevel);
                    enchanting.setText("Enchanting " + enchantinglevel);
                    alchemy.setText("Alchemy " + alchemylevel);
                    taming.setText("Taming " + taminglevel);
                    carpentry.setText("Carpentry " + carpentrylevel);

                    farmbar.setProgress(farmper);
                    miningbar.setProgress(minper);
                    combatbar.setProgress(combper);
                    foragingbar.setProgress(forper);
                    runebar.setProgress(runeper);
                    fishbar.setProgress(fishper);
                    enchantbar.setProgress(enchantper);
                    alchemybar.setProgress(alcper);
                    tamingbar.setProgress(tamper);
                    carpentbar.setProgress(carper);

                    bank.setText(bankcoins + "");
                    purse.setText(purseCoins + "");
                    connexion.setText(lastCo + "");
                    ct.setText(critdemage + "");

                }
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu = new Intent(getApplicationContext(), Event.class);
                Menu.putExtra("username", user);
                startActivity(Menu);
            }
        });

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Menu = new Intent(getApplicationContext(), menu.class);
                Menu.putExtra("username", user);
                startActivity(Menu);
                finish();
            }
        });


    }
}