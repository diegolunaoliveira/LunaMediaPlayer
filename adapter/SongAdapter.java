package com.diegoluna.lunamediaplayer.adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.diegoluna.lunabooks.R;
import com.diegoluna.lunamediaplayer.model.Song;

import java.io.File;
import java.util.List;


/**
 * Created by Diego Luna on 10/01/2016.
 */
public class SongAdapter extends BaseAdapter {

    private List<Song> songList;
    private LayoutInflater mInflater;

    public SongAdapter(Context context, List<Song> songList) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.songList = songList;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        return songList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return songList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.song_item, parent, false);
            holder.tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitulo.setText(songList.get(position).getTitulo());
        return convertView;
    }

    private class ViewHolder {
        protected TextView tvTitulo;
    }
}
