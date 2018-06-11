package com.baizhi.dao;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumDAO {
    public void save(Album album);

    public List<Album> queryAll();

    public Album queryById(int id);
}
