package com.example.simplenotificationbuilder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static int notifikasi = 1;

    EditText judulPesan, isiPesan;
    Button kirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        judulPesan = findViewById(R.id.edtJudulPesan);
        isiPesan = findViewById(R.id.edtIsiPesan);
        kirim = findViewById(R.id.btnSend);

        final Context context;

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                if(TextUtils.isEmpty(judulPesan.getText()) && TextUtils.isEmpty(isiPesan.getText())){
                    judulPesan.setError("Silahkan isi judul");
                    isiPesan.setError("Silahkan isi pesan");
                }
                else {
                    judulPesan.setError(null);
                    isiPesan.setError(null);
                    Toast.makeText(getApplicationContext(), "Silahkan cek notifikasi anda", Toast.LENGTH_SHORT).show();
                    showNotification(judulPesan.getText().toString(), isiPesan.getText().toString(), intent);
                    judulPesan.setText("");
                    isiPesan.setText("");
                }
            }
        });
    }

    private void showNotification(String s, String s1, Intent intent){

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this
                                        , notifikasi, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        Notification notification;

        notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(s)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(MainActivity.this.getResources(),R.mipmap.ic_launcher))
                .setContentText(s1)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) MainActivity.this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifikasi, notification);



    }
}
