package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
//分页展示轮播图
    public List<Banner> selAll(int begin,int end);
//添加轮播图
    public void regiser(Banner banner);
//删除轮播图

    public boolean delBanner(int id);
//更改轮播图
    public boolean updateBanner(Banner banner);
    public Banner selById(int id);

}
