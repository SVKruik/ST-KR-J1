package com.example.testapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;

public class MediaPlayerMain extends Activity {
    ListView listView1;
    ArrayAdapter<String> adapter;
    String[] array = {"media1", "media2", "media3", "media4", "media5"};
    int selectedRawId;
    String selectedMedia;
    Button buttonPlay, buttonStop;
    MediaPlayer mediaPlayer;
    SeekBar seekBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player_main);

        listView1 = findViewById(R.id.list_view_1);
        buttonPlay = findViewById(R.id.button_play);
        buttonStop = findViewById(R.id.button_stop);
        seekBar1 = findViewById(R.id.seek_bar);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, array);
        listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMedia = array[i];
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopMedia();

                selectedRawId = getResources().getIdentifier(selectedMedia, "raw", getPackageName());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), selectedRawId);
                mediaPlayer.start();

                new Thread() {
                    public void run() {
                        if (mediaPlayer == null) return;
                        seekBar1.setMax(mediaPlayer.getDuration());
                        while (mediaPlayer != null && mediaPlayer.isPlaying()) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    seekBar1.setProgress(mediaPlayer.getCurrentPosition());
                                }
                            });
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopMedia();
            }
        });
    }

    public void stopMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}