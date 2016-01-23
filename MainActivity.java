package com.diegoluna.lunamediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.diegoluna.lunabooks.R;
import com.diegoluna.lunamediaplayer.model.Song;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lvSongs;

    private FloatingActionButton btPlay, btStop;
    Intent playbackServiceIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadSongList();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btPlay = (FloatingActionButton) findViewById(R.id.fabPlay);
        btPlay.hide();
        btStop = (FloatingActionButton) findViewById(R.id.fabStop);
        btPlay2 = (Button) findViewById(R.id.btPlay);

        btPlay.setOnClickListener(this);
        btStop.setOnClickListener(this);

        playbackServiceIntent = new Intent(this, PlayerServices.class);


        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Song song = (Song) lvSongs.getItemAtPosition(position);
                Uri uri = Uri.fromFile(new File(song.getPath()));
                playbackServiceIntent.putExtra("songUri", uri);
                startService(playbackServiceIntent);
            }
        });
    }

    public void onClick(View v) {
        if (v == btPlay) {
            startService(playbackServiceIntent);
        } else if (v == btStop) {
            stopService(playbackServiceIntent);
        }
    }

    private void loadSongList() {
        lvSongs = (ListView) findViewById(R.id.lvSongs);

        List<Song> songList = new ArrayList<>();
        ContentResolver cr = this.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = null;
        String sortOrder = null;
        String selectionMimeType = MediaStore.Files.FileColumns.MIME_TYPE + "=?";
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("mp3");
        String[] selectionArgsMp3 = new String[]{mimeType};

        Cursor cur = cr.query(uri, projection, selectionMimeType, selectionArgsMp3, sortOrder);
        int count = 0;

        if (cur != null) {
            count = cur.getCount();
            if (count > 0) {
                while (cur.moveToNext()) {
                    Song song = new Song();
                    song.setTitulo(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                    song.setPath(cur.getString(cur.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                    song.setId(cur.getInt(cur.getColumnIndex(MediaStore.Audio.Media._ID)));
                    songList.add(song);
                }
            }
        }
        cur.close();

        if (songList != null && !songList.isEmpty()) {
            lvSongs.setAdapter(new SongAdapter(this, songList));
        } else {
            lvSongs.setAdapter(new SongAdapter(this, songList));
            Toast.makeText(this, "Sem retorno!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        loadSongList();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if(requestCode == RESULT_OK && requestCode == 10){
//            Uri uriSound=data.getData();
//            play(this, uriSound);
//        }
//    }

//    private void play(String path) {
//
//        try {
//            Uri uri = Uri.fromFile(new File(path));
//            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), uri);
//            //mp.setDataSource(getApplicationContext(), uri);
//            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mp.start();
//        } catch (IllegalArgumentException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SecurityException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IllegalStateException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}