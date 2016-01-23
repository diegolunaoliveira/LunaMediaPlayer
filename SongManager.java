package com.diegoluna.lunamediaplayer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.diegoluna.lunamediaplayer.model.Song;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Luna on 17/01/2016.
 */
public class SongManager {

    private final String MEDIA_PATH = Environment.getRootDirectory().getPath() + "/";
    private String mp3Pattern = ".mp3";

    public List<Song> getPlayList() {

          List<Song> songList = new ArrayList<>();
//
//        ContentResolver cr = this.getContentResolver();
//
//        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
//        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
//        Cursor cur = cr.query(uri, null, selection, null, sortOrder);
//        int count = 0;
//
//        if(cur != null)
//        {
//            count = cur.getCount();
//            if(count > 0)
//            {
//                ArrayList<String> musicas = new ArrayList<>();
//                while(cur.moveToNext())
//                {
//                    String data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE));
//
//                    musicas.add(data);
//                }
//            }
//        }
//        cur.close();

        return songList;
    }
}
