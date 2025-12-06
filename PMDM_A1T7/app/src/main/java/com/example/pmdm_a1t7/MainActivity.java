package com.example.pmdm_a1t7;

import android.media.MediaPlayer;
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
    Button play;
    Button pause;
    SeekBar seek;
    TextView current;
    TextView end;

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

        play = findViewById(R.id.btnPlay);
        pause = findViewById(R.id.btnPause);
        seek = findViewById(R.id.seekBar);
        current = findViewById(R.id.textCurrent);
        end = findViewById(R.id.textEnd);

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