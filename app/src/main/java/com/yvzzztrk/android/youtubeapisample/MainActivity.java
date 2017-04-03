package com.yvzzztrk.android.youtubeapisample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String API_KEY = "REPLACE_WITH_YOUR_API_KEY";

    YouTubePlayerView youTubeView;

    YouTubePlayer player;
    EditText videoKeyEditText;
    Button loadVideoButton;
    TextView errorTextView;

    MyPlayerStateChangeListener myPlayerStateChangeListener = new MyPlayerStateChangeListener();
    MyPlaybackEventListener myPlaybackEventListener = new MyPlaybackEventListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtubePlayerView);
        youTubeView.initialize(API_KEY, this);

        videoKeyEditText = (EditText) findViewById(R.id.videoKeyEditText);
        loadVideoButton = (Button) findViewById(R.id.loadVideoButton);
        errorTextView = (TextView) findViewById(R.id.errorTextView);

        loadVideoButton.setEnabled(false);
        loadVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!videoKeyEditText.getText().toString().equals("")) {
                    player.cueVideo(videoKeyEditText.getText().toString());
                }
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            this.player = youTubePlayer;
            this.player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
            this.player.setPlayerStateChangeListener(myPlayerStateChangeListener);
            this.player.setPlaybackEventListener(myPlaybackEventListener);
        }
        loadVideoButton.setEnabled(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
        }

        @Override
        public void onLoaded(String s) {
            player.play();
        }

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onVideoStarted() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            Log.d("VideoActivity",errorReason.name());
            errorTextView.setText(errorReason.name());
        }
    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        @Override
        public void onPlaying() {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onStopped() {
        }

        @Override
        public void onBuffering(boolean b) {
        }

        @Override
        public void onSeekTo(int i) {
        }
    }
}
