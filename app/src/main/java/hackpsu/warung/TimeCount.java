package hackpsu.warung;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.os.SystemClock;


public class TimeCount extends AppCompatActivity implements OnClickListener {

    private Chronometer counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_count);
        counter = (Chronometer) findViewById(R.id.chronometer);
        ((Button) findViewById(R.id.startBtn)).setOnClickListener(this);
        ((Button) findViewById(R.id.notify)).setOnClickListener(this);
        ((Button) findViewById(R.id.plot)).setOnClickListener(this);
        //alertCoffee();

        counter.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer counter) {
                long elapsedMilli = SystemClock.elapsedRealtime() - counter.getBase();
                if(elapsedMilli == 5000)
                {
                    alertCoffee();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startBtn:
                counter.setBase(SystemClock.elapsedRealtime());
                counter.start();
                break;
            case R.id.notify:
                alertCoffee();
                break;
            case R.id.plot:
                Button button1 = (Button) findViewById(R.id.plot);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertCoffee();
                        Intent graph = new Intent();
                        graph.setClass(TimeCount.this, plot.class);
                        startActivity(graph);
                    }
                });
                break;
        }
    }

    public void alertCoffee()
    {
        Notification.Builder noteBuilder =
                new Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_notification)
                        .setContentTitle("waRung Alert")
                        .setContentText("You should drink another cup of coffee!");

        Intent resultIntent = new Intent(this, plot.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addParentStack(plot.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        noteBuilder.setContentIntent(resultPendingIntent);
        NotificationManager graphNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        graphNotificationManager.notify(0, noteBuilder.build());
    }
}