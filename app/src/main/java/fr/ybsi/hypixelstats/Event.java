package fr.ybsi.hypixelstats;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

public class Event extends AppCompatActivity {

    private TextView DarkAuction;
    private TextView Zoo;
    private TextView Halloween;
    private TextView Jerry;
    private TextView NewYear;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        AdView mAdView1 = findViewById(R.id.adView11);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);

        this.DarkAuction = findViewById(R.id.textView116);
        this.Zoo = findViewById(R.id.textView117);
        this.Halloween = findViewById(R.id.textView118);
        this.Jerry = findViewById(R.id.textView119);
        this.NewYear = findViewById(R.id.textView120);
        this.back = findViewById(R.id.imageView36);

        long actual = Calendar.getInstance().getTimeInMillis();
        long DAevent = 3300000;

        while (DAevent < actual) DAevent += 3600000;

        long dif = DAevent - actual;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dif);

        int mMinute = calendar.get(Calendar.MINUTE);
        int mSecond = calendar.get(Calendar.SECOND);

        DarkAuction.setText(mMinute + ":" + mSecond);

        // Zoo Event

        long Zevent = 30300000;

        while (Zevent < actual) Zevent += 218340000;

        dif = Zevent - actual;
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dif);

        int mHours = (calendar.get(Calendar.DAY_OF_MONTH) - 1) * 24 + calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        mSecond = calendar.get(Calendar.SECOND);

        Zoo.setText(mHours + ":" + mMinute + ":" + mSecond);

        //Spookie

        long Hevent = 212700000;
        long Nevent = 345780000;
        long NYevent = 351880000;

        while (Hevent < actual) Hevent += 436680000;
        while (Nevent < actual) Nevent += 436680000;
        while (NYevent < actual) NYevent += 436680000;

        dif = Hevent - actual;
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dif);

        mHours = (calendar.get(Calendar.DAY_OF_MONTH) - 1) * 24 + calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        mSecond = calendar.get(Calendar.SECOND);

        Halloween.setText(mHours + ":" + mMinute + ":" + mSecond);

        dif = Nevent - actual;
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dif);

        mHours = (calendar.get(Calendar.DAY_OF_MONTH) - 1) * 24 + calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        mSecond = calendar.get(Calendar.SECOND);

        Jerry.setText(mHours + ":" + mMinute + ":" + mSecond);

        dif = NYevent - actual;
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dif);


        mHours = (calendar.get(Calendar.DAY_OF_MONTH) - 1) * 24 + calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        mSecond = calendar.get(Calendar.SECOND);

        NewYear.setText(mHours + ":" + mMinute + ":" + mSecond);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}