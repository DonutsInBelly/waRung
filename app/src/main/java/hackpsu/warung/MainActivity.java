package hackpsu.warung;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.interaxon.libmuse.Accelerometer;
import com.interaxon.libmuse.AnnotationData;
import com.interaxon.libmuse.ConnectionState;
import com.interaxon.libmuse.Eeg;
import com.interaxon.libmuse.LibMuseVersion;
import com.interaxon.libmuse.MessageType;
import com.interaxon.libmuse.Muse;
import com.interaxon.libmuse.MuseArtifactPacket;
import com.interaxon.libmuse.MuseConfiguration;
import com.interaxon.libmuse.MuseConnectionListener;
import com.interaxon.libmuse.MuseConnectionPacket;
import com.interaxon.libmuse.MuseDataListener;
import com.interaxon.libmuse.MuseDataPacket;
import com.interaxon.libmuse.MuseDataPacketType;
import com.interaxon.libmuse.MuseFileFactory;
import com.interaxon.libmuse.MuseFileReader;
import com.interaxon.libmuse.MuseFileWriter;
import com.interaxon.libmuse.MuseManager;
import com.interaxon.libmuse.MusePreset;
import com.interaxon.libmuse.MuseVersion;

public class MainActivity extends AppCompatActivity {

    private static Button nextBtn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickButtonListener();

        /*Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertCoffee();
                Intent graph = new Intent();
                graph.setClass(MainActivity.this, plot.class);
                startActivity(graph);
            }
        });*/
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


    public void onClickButtonListener(){
        nextBtn1 = (Button)findViewById(R.id.nextBtn1);
        nextBtn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(MainActivity.this, UserQuestions.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
