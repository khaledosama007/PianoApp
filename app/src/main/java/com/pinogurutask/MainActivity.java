package com.pinogurutask;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int DELAY = 50;
    android.os.Handler handler = new android.os.Handler();
    EditText mNoteText;
    Button mPlayButton, mStopButton;
    MediaPlayer mMediaPlayer;
    String[] mNote;
    int mNoteNumbers[] = {R.raw.a1, R.raw.a1s, R.raw.b1, R.raw.c1, R.raw.c1s
            , R.raw.c2, R.raw.d1, R.raw.d1s, R.raw.e1, R.raw.f1, R.raw.f1s, R.raw.g1};
    int mIndex = 0;

    /**
     * calling the next note to be played
     */
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mIndex++;
            mediaPlayer.stop();
            if (mIndex == mNote.length) {
                mediaPlayer.release();
            } else if (mNote[mIndex].equals(".")) {
                int count = 1;
                while (mIndex + count < mNote.length && mNote[mIndex + count].equals(".")) {
                    count++;
                }
                mIndex += count;
                addDelay(DELAY * count);
            } else {
                playNote(mNote[mIndex]);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNoteText = (EditText) findViewById(R.id.edittext_note);
        mPlayButton = (Button) findViewById(R.id.play_button);
        mStopButton = (Button) findViewById(R.id.stop_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = mNoteText.getText().toString();
                if (note.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.no_note_error_msg, Toast.LENGTH_LONG).show();
                } else {
                    mNote = note.split(" ");
                    mIndex = 0;
                    playNote(mNote[mIndex]);
                }
            }
        });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMediaPlayer != null)
                    mMediaPlayer.stop();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer == null)
            mMediaPlayer.release();
    }

    /**
     * @param note choose and play the next note from the array
     */
    public void playNote(String note) {
        switch (note) {
            case "A1":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[0]);
                mMediaPlayer.start();
                break;
            case "A1s":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[1]);
                mMediaPlayer.start();
                break;
            case "B1":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[2]);
                mMediaPlayer.start();
                break;
            case "C1":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[3]);
                mMediaPlayer.start();
                break;
            case "C1s":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[4]);
                mMediaPlayer.start();
                break;
            case "C2":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[5]);
                mMediaPlayer.start();
                break;
            case "D1":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[6]);
                mMediaPlayer.start();
                break;
            case "D1s":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[7]);
                mMediaPlayer.start();
                break;
            case "E1":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[8]);
                mMediaPlayer.start();
                break;
            case "F1":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[9]);
                mMediaPlayer.start();
                break;
            case "F1s":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[10]);
                mMediaPlayer.start();
                break;
            case "G1":
                mMediaPlayer = MediaPlayer.create(getBaseContext(), mNoteNumbers[11]);
                mMediaPlayer.start();
                break;
            default:
                break;

        }
        mMediaPlayer.setOnCompletionListener(onCompletionListener);
    }

    /*
    adding delay based on the dots number
     */
    public void addDelay(int delayTime) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mIndex < mNote.length)
                    playNote(mNote[mIndex]);
            }
        }, delayTime);

    }
}
