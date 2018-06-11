package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    //展示所有
    public List<Album> selAll();
    //添加专辑
    public void insert(Album album);
    //查询单个专辑及章节
    public Album selById(int id);


}
