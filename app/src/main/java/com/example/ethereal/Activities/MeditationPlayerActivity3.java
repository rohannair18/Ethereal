package com.example.ethereal.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ethereal.HelperClasses.ActivitiesMediAdapter;
import com.example.ethereal.R;

import CustomClasses.SeekArc;

public class MeditationPlayerActivity3 extends AppCompatActivity {

    private ImageView playpause;
    private TextView currenttime, totaltime, mediplayertitle, mediplayerdesc;
    private SeekArc seekArc;
    private MediaPlayer mediaPlayer;
    ActivitiesMediAdapter activitiesMediAdapter = new ActivitiesMediAdapter();
    private Handler handler = new Handler();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_player1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.tangerine));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            playpause = findViewById(R.id.playpause);
            currenttime = findViewById(R.id.currenttime);
            totaltime = findViewById(R.id.totaltime);
            seekArc = findViewById(R.id.seekArc);
            mediaPlayer = new MediaPlayer();
            seekArc.setMax(100);
            mediplayertitle = findViewById(R.id.mediplayertitle);
            mediplayerdesc = findViewById(R.id.mediplayerdesc);
            Intent intent = getIntent();
            String Title = intent.getExtras().getString("Title");
            String Description = intent.getExtras().getString("Description");
//            String Url1 = intent.getExtras().getString("URL1");
//            try {
//                mediaPlayer.setDataSource(Url1);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String Url2 = intent.getExtras().getString("URL2");
//            try {
//                mediaPlayer.setDataSource(Url2);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            activitiesMediAdapter.preparemediaplayer1(mediaPlayer, totaltime);
//            activitiesMediAdapter.preparemediaplayer2(mediaPlayer, totaltime);
            mediplayertitle.setText(Title);
            mediplayerdesc.setText(Description);
            preparemediaplayer();

        }

        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    playpause.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    playpause.setImageResource(R.drawable.pause);
                    updateseekbar();
                }
            }
        });
//        activitiesMediAdapter.preparemediaplayer1(mediaPlayer, totaltime);
//        activitiesMediAdapter.preparemediaplayer2(mediaPlayer, totaltime);
        seekArc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekArc seekArc = (SeekArc) v;
                int playposition = (mediaPlayer.getDuration() / 100) * seekArc.getProgress();
                mediaPlayer.seekTo(playposition);
                currenttime.setText(millisecondstotimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                seekArc.setProgress(0);
                playpause.setImageResource(R.drawable.play);
                currenttime.setText(R.string.currenttime);
                totaltime.setText(R.string.totaltime);
                mediaPlayer.reset();
//                activitiesMediAdapter.preparemediaplayer1(mediaPlayer, totaltime);
//                activitiesMediAdapter.preparemediaplayer2(mediaPlayer, totaltime);
                preparemediaplayer();
            }
        });


    }

    private void preparemediaplayer() {
        try {
            mediaPlayer.setDataSource("https://etherealmeditationbucket.s3.ap-south-1.amazonaws.com/Meditations/Relaxation+Meditation.mp3");
            mediaPlayer.prepare();
            totaltime.setText(millisecondstotimer(mediaPlayer.getDuration()));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //    private void preparemediaplayer(){
//        try {
//            mediaPlayer.setDataSource(".mp3");
//            mediaPlayer.prepare();
//            totaltime.setText(millisecondstotimer(mediaPlayer.getDuration()));
//        }catch (Exception e){
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateseekbar();
            long current = mediaPlayer.getCurrentPosition();
            currenttime.setText(millisecondstotimer(current));
        }
    };

    private void updateseekbar() {
        if (mediaPlayer.isPlaying()) {
            seekArc.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }

    private String millisecondstotimer(long milliseconds) {
        String timerString = "";
        String secondString;

        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            timerString = hours + ":";
        }
        if (seconds < 10) {
            secondString = "0" + seconds;
        } else {
            secondString = "" + seconds;
        }
        timerString = timerString + minutes + ":" + secondString;
        return timerString;

    }
}