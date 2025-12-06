package com.example.pmdm_a1t7;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    SoundPool pool;
    Button play;
    Button pause;
    SeekBar seek;
    TextView current;
    TextView end;
    Button pool1, pool2, pool3, pool4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        player = MediaPlayer.create(this, R.raw.no_time_for_caution);
        pool = new SoundPool.Builder().setMaxStreams(10).build();

        int hover = pool.load(this, R.raw.menu_focus, 1);
        int select = pool.load(this, R.raw.menu_accept, 1);
        int alarm = pool.load(this, R.raw.klaxon1, 1);
        int portal = pool.load(this, R.raw.wpn_portal_gun_fire_blue_01, 1);

        play = findViewById(R.id.btnPlay);
        pause = findViewById(R.id.btnPause);
        seek = findViewById(R.id.seekBar);
        current = findViewById(R.id.textCurrent);
        end = findViewById(R.id.textEnd);

        pool1 = findViewById(R.id.btnPool1);
        pool2 = findViewById(R.id.btnPool2);
        pool3 = findViewById(R.id.btnPool3);
        pool4 = findViewById(R.id.btnPool4);

        end.setText(String.format("%02d:%02d",
                player.getDuration() / 1000 / 60, player.getDuration() / 1000 % 60));

        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                seek.setProgress(player.getCurrentPosition());
                current.setText(String.format("%02d:%02d",
                        player.getCurrentPosition() / 1000 / 60,
                        player.getCurrentPosition() / 1000 % 60));
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(update);

        play.setOnClickListener(v -> play());
        pause.setOnClickListener(v -> pause());

        pool1.setOnClickListener(v -> pool.play(hover, 1, 1, 1, 0, 1));
        pool2.setOnClickListener(v -> pool.play(select, 1, 1, 1, 0, 1));
        pool3.setOnClickListener(v -> pool.play(alarm, 1, 1, 1, 0, 1));
        pool4.setOnClickListener(v -> pool.play(portal, 1, 1, 1, 0, 1));

        seek.setMax(player.getDuration());
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void play() {
        if (!player.isPlaying()) player.start();
    }

    public void pause() {
        if (player.isPlaying()) player.pause();
    }
}