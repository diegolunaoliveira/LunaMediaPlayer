package com.diegoluna.lunamediaplayer.model;

import android.net.Uri;

/**
 * Created by Diego Luna on 17/01/2016.
 */
public class Song {

    private int id;
    private String titulo;
    private String path;
    private Uri uri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
